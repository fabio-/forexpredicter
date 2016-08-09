package com.forex.predicter.service;

import com.forex.predicter.model.Order;
import com.forex.predicter.model.OrderOutcome;
import com.forex.predicter.repository.CandlestickRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderPredictionChecker
{
    @Autowired
    private CandlestickRepository candlestickRepository;

    public OrderOutcome checkOrder(Order order)
    {
        return candlestickRepository.getPredictionOutcome(order);
    }
}
