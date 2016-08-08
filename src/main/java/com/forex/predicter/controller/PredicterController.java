package com.forex.predicter.controller;

import com.forex.predicter.logic.Predicter;
import com.forex.predicter.model.Candlestick;
import com.forex.predicter.model.Frequency;
import com.forex.predicter.model.Order;
import com.forex.predicter.model.Position;
import com.forex.predicter.repository.CandelstickRepository;
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
    private CandelstickRepository candelstickRepository;

    @RequestMapping(value = "/predict", produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Order> predict() {
        List<Order> orders = new ArrayList<>();
        List<Candlestick> values = candelstickRepository.getByTime(LocalDateTime.of(2016,7,6,12,30), LocalDateTime.now(), Frequency.m15);
        values.forEach(candlestick -> orders.add(predicter.predict(candlestick)));
        return orders.stream().filter(order -> order.getPosition() != Position.NO_ACTION).collect(Collectors.toList());
    }

}
