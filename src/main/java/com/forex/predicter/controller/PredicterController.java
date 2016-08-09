package com.forex.predicter.controller;

import com.forex.predicter.logic.Predicter;
import com.forex.predicter.model.*;
import com.forex.predicter.repository.CandlestickRepository;
import com.forex.predicter.service.OrderPredictionChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PredicterController
{
    @Autowired
    private Predicter predicter;

    @Autowired
    private OrderPredictionChecker orderPredictionChecker;

    @Autowired
    private CandlestickRepository candlestickRepository;

    @RequestMapping(value = "/predict", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<OrderOutcome> predict() {
        List<Order> orders = new ArrayList<>();
        List<Candlestick> values = candlestickRepository.getByTime(LocalDateTime.of(2015,7,6,12,30), LocalDateTime.of(2015,7,7,12,30), Frequency.m15);
        values.forEach(candlestick -> orders.add(predicter.predict(candlestick)));
        List<Order> trades = orders.stream().filter(order -> order.getPosition() != Position.NO_ACTION).collect(Collectors.toList());

        List<OrderOutcome> outcomes = new ArrayList<>();
        trades.forEach(trade -> outcomes.add(orderPredictionChecker.checkOrder(trade) ));
        return outcomes;
    }

}
