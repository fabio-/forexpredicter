package com.forex.predicter.repository;

import com.forex.predicter.model.Candlestick;
import com.forex.predicter.model.Frequency;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public interface CandlestickRepository extends MongoRepository<Candlestick, String>, CustomCandlestickRepository {

    Candlestick getByTime(LocalDateTime time);

    Candlestick getByTime(LocalDateTime time, Frequency frequency);

}