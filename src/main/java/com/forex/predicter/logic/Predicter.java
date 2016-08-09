package com.forex.predicter.logic;

import com.forex.predicter.model.*;
import com.forex.predicter.repository.CandlestickRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class Predicter
{
    @Autowired
    private CandlestickRepository repository;

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(DataSetImporter.class);

    private final Frequency tradingFrequency = Frequency.m15;

    public OrderOutcome predict(Candlestick candlestick)
    {
        OrderOutcome result = new OrderOutcome(Position.NO_ACTION);
        result.setTradingFrequency(tradingFrequency);
        ValueArea valueArea = calculateValueArea(candlestick);

        if (!isInValueArea(candlestick, valueArea)) {
            return result;
        }

        List<Candlestick> previous = repository.getByTime(candlestick.getTime().minusHours(1), candlestick.getTime(), Frequency.m15);

        for (Candlestick value : previous) {
            if (!isInValueArea(value, valueArea)){
                return result;
            }
        }

        Double candlestickLevel = candlestick.getClose() - valueArea.getMin().getClose();
        Double percent100 = valueArea.getMax().getClose() - valueArea.getMin().getClose();

        Double howCloseToBorderLines = candlestickLevel/percent100;

        Double maxGain = valueArea.getMax().getClose() - candlestick.getClose();
        Double maxLoss = valueArea.getMin().getClose() - candlestick.getClose();
        Double stopLoss=0d;
        Double takeProfit=0d;

        if (howCloseToBorderLines < 0.2) {
            result.setPosition(Position.LONG);
            stopLoss = candlestick.getClose() - maxGain*0.4;
            takeProfit = valueArea.getMax().getClose();
        } else if (howCloseToBorderLines > 0.8) {
            result.setPosition(Position.SHORT);
            stopLoss = valueArea.getMin().getClose();
            takeProfit = candlestick.getClose() - maxLoss*0.4;
        }

        result.setEnterTradeTime(candlestick.getTime());
        result.setEnterTradePrice(candlestick.getClose());

        result.setTakeProfit(takeProfit);
        result.setStopLoss(stopLoss);

        return result;
    }

    private boolean isInValueArea(Candlestick candlestick, ValueArea valueArea)
    {
        return (candlestick.getHigh() >= valueArea.getMin().getClose() && candlestick.getHigh() < valueArea.getMax().getClose() ||
                candlestick.getLow() <= valueArea.getMax().getClose()) && candlestick.getLow() > valueArea.getMin().getClose();
    }

    public ValueArea calculateValueArea(Candlestick candlestick)
    {
        List<Candlestick> values = repository.getByTime(candlestick.getTime().minusDays(1), candlestick.getTime(), Frequency.HOURLY);

        Double sum = values.stream().mapToDouble(Candlestick::getVolume).sum();

        Double dailyVolume70 = sum * 0.7;

        List<Candlestick> volumeSorted = values.stream().sorted((v1, v2) -> v2.getVolume()
                                               .compareTo(v1.getVolume()))
                                               .collect(Collectors.toList());

        List<Candlestick> volume70values = new ArrayList<>();

        Double volume70Percent = 0d;
        for (Candlestick value : volumeSorted) {
            volume70Percent += value.getVolume();
            if (volume70Percent > dailyVolume70) {
                if (volume70values.size() == 0) {
                    volume70values.add(value);
                }
                break;
            }
            volume70values.add(value);
        }

        Candlestick minPrice = Collections.min(volume70values, Comparator.comparing(Candlestick::getClose));
        Candlestick maxPrice = Collections.max(volume70values, Comparator.comparing(Candlestick::getClose));

        return new ValueArea(minPrice, maxPrice);
    }

}
