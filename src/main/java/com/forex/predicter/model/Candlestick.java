package com.forex.predicter.model;

import com.forex.predicter.repository.CustomCandelstickRepository;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Candlestick
{
    @Id
    private String id;
    private CurrencyPair currencyPair;
    private Frequency frequency;
    private LocalDateTime time;
    private Double open;
    private Double high;
    private Double low;
    private Double close;
    private Double volume;

    public Candlestick() {}

    public Candlestick(CurrencyPair currencyPair, Frequency frequency, LocalDateTime time, Double open, Double high, Double low, Double close, Double volume) {
        this.currencyPair = currencyPair;
        this.frequency = frequency;
        this.time = time;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
    }

    public Candlestick(CurrencyPair currencyPair, Frequency frequency, Object[] row) {
        this.currencyPair = currencyPair;
        this.frequency = frequency;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        this.time = LocalDateTime.parse(row[0].toString().substring(0, row[0].toString().length()-7), formatter);
        this.open = Double.parseDouble(row[1].toString());
        this.high = Double.parseDouble(row[2].toString());
        this.low = Double.parseDouble(row[3].toString());
        this.close = Double.parseDouble(row[4].toString());
        this.volume = Double.parseDouble(row[5].toString());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CurrencyPair getCurrencyPair() {
        return currencyPair;
    }

    public void setCurrencyPair(CurrencyPair currencyPair) {
        this.currencyPair = currencyPair;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getClose() {
        return close;
    }

    public void setClose(Double close) {
        this.close = close;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return String.format(
                "Candlestick[id=%s, currencyPair='%s', frequency='%s', time='%s', open='%s', high='%s', low='%s', close='%s',volume='%s']",
                id, currencyPair, frequency, time, open, high, low, close, volume);
    }
}

