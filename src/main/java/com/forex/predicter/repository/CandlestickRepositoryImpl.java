package com.forex.predicter.repository;

import com.forex.predicter.model.*;
import com.forex.predicter.model.db.CandlestickDB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Component
public class CandlestickRepositoryImpl implements CustomCandlestickRepository
{
    private static final Logger logger = LoggerFactory.getLogger(CandlestickRepositoryImpl.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Candlestick> getByTime(LocalDateTime from, LocalDateTime to, Frequency frequency)
    {
        return mongoTemplate.find(new Query().addCriteria(new Criteria()
                .andOperator(
                        where("time").gte(from),
                        where("time").lte(to),
                        where("frequency").is(frequency)
                )
        ),Candlestick.class);
    }

    @Override
    public OrderOutcome getPredictionOutcome(Order order)
    {
        OrderOutcome outcome = new OrderOutcome(order);

        Candlestick tp = mongoTemplate.findOne(new Query().with(new Sort(Sort.Direction.ASC, CandlestickDB.time)).addCriteria(new Criteria().andOperator(
                where(CandlestickDB.frequency).is(order.getTradingFrequency()),
                where(CandlestickDB.time).gte(order.getEnterTradeTime()),
                where(CandlestickDB.high).gte(order.getTakeProfit())
        )), Candlestick.class);

        Candlestick sl = mongoTemplate.findOne(new Query().with(new Sort(Sort.Direction.ASC, CandlestickDB.time)).addCriteria(new Criteria().andOperator(
                where(CandlestickDB.frequency).is(order.getTradingFrequency()),
                where(CandlestickDB.time).gte(order.getEnterTradeTime()),
                where(CandlestickDB.low).lte(order.getStopLoss())
        )), Candlestick.class);

        if (sl == null && tp == null) {
            return null;
        }

        if (sl == null || tp.getTime().isBefore(sl.getTime())) {
            outcome.setOutcome(order.getPosition() == Position.LONG ? OrderOutcome.Outcome.PROFIT : OrderOutcome.Outcome.LOSS);
            outcome.setExitTradeTime(tp.getTime());
            outcome.setExitTradePrice(tp.getClose());
            outcome.setPipsDifference(Math.abs(tp.getClose() - order.getEnterTradePrice()));
            return outcome;
        }

        if (sl.getTime().isBefore(tp.getTime())) {
            outcome.setOutcome(order.getPosition() == Position.SHORT ? OrderOutcome.Outcome.PROFIT : OrderOutcome.Outcome.LOSS);
            outcome.setExitTradeTime(sl.getTime());
            outcome.setExitTradePrice(sl.getClose());
            outcome.setPipsDifference(Math.abs(sl.getClose() - order.getEnterTradePrice()));
        }
        return outcome;
    }
}
