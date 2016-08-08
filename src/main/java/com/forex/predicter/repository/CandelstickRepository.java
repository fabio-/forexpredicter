package com.forex.predicter.repository;

import com.forex.predicter.model.Candlestick;
import com.forex.predicter.model.Frequency;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;

public interface CandelstickRepository extends MongoRepository<Candlestick, String>, CustomCandelstickRepository {

    public Candlestick getByTime(LocalDateTime time);

    public Candlestick getByTime(LocalDateTime time, Frequency frequency);

}