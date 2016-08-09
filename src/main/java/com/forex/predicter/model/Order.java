package com.forex.predicter.model;

import java.time.LocalDateTime;

public class Order
{
    protected LocalDateTime enterTradeTime;
    protected Double enterTradePrice;
    protected Frequency tradingFrequency;
    protected Position position;
    protected Double stopLoss;
    protected Double takeProfit;

    public Order(Position position) {
        this.position = position;
    }

    public LocalDateTime getEnterTradeTime() {
        return enterTradeTime;
    }

    public void setEnterTradeTime(LocalDateTime enterTradeTime) {
        this.enterTradeTime = enterTradeTime;
    }

    public Double getEnterTradePrice() {
        return enterTradePrice;
    }

    public void setEnterTradePrice(Double enterTradePrice) {
        this.enterTradePrice = enterTradePrice;
    }

    public Frequency getTradingFrequency() {
        return tradingFrequency;
    }

    public void setTradingFrequency(Frequency tradingFrequency) {
        this.tradingFrequency = tradingFrequency;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public Double getStopLoss() {
        return stopLoss;
    }

    public void setStopLoss(Double stopLoss) {
        this.stopLoss = stopLoss;
    }

    public Double getTakeProfit() {
        return takeProfit;
    }

    public void setTakeProfit(Double takeProfit) {
        this.takeProfit = takeProfit;
    }
}
