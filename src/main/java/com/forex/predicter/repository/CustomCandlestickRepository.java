package com.forex.predicter.repository;

import com.forex.predicter.model.Candlestick;
import com.forex.predicter.model.Frequency;
import com.forex.predicter.model.Order;
import com.forex.predicter.model.OrderOutcome;

import java.time.LocalDateTime;
import java.util.List;

public interface CustomCandlestickRepository
{
    public List<Candlestick> getByTime(LocalDateTime from, LocalDateTime to, Frequency frequency);

    public OrderOutcome getPredictionOutcome(Order order);
}
