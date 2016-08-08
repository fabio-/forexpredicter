package com.forex.predicter.service;

import com.forex.predicter.model.Order;
import com.forex.predicter.repository.CandelstickRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderPredictionChecker
{
    @Autowired
    private CandelstickRepository candelstickRepository;

    public boolean checkOrder(Order order)
    {
           return true;
    }
}
