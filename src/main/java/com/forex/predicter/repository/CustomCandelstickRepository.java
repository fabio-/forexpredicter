package com.forex.predicter.repository;

import com.forex.predicter.model.Candlestick;
import com.forex.predicter.model.Frequency;

import java.time.LocalDateTime;
import java.util.List;

public interface CustomCandelstickRepository
{
    public List<Candlestick> getByTime(LocalDateTime from, LocalDateTime to, Frequency frequency);

    public Candlestick get(LocalDateTime from, Frequency frequency, Double stopLoss, Double takeProfit);
}
