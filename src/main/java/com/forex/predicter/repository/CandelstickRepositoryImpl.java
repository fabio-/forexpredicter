package com.forex.predicter.repository;

import com.forex.predicter.model.Candlestick;
import com.forex.predicter.model.Frequency;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;

public class CandelstickRepositoryImpl implements CustomCandelstickRepository
{
    private static final Logger logger = LoggerFactory.getLogger(CandelstickRepositoryImpl.class);

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
    public Candlestick get(LocalDateTime from, Frequency frequency, Double stopLoss, Double takeProfit)
    {
        return null;
    }
}
