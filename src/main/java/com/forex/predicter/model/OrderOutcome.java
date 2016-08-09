package com.forex.predicter.model;

import java.time.LocalDateTime;

public class OrderOutcome extends Order {

    private LocalDateTime exitTradeTime;

    private Double exitTradePrice;

    private Outcome outcome;

    private Double pipsDifference;

    public OrderOutcome(Order order)
    {
        super(order.getPosition());
        this.enterTradeTime = order.enterTradeTime;
        this.enterTradePrice = order.enterTradePrice;
        this.tradingFrequency = order.tradingFrequency;
        this.position = order.position;
        this.stopLoss = order.stopLoss;
        this.takeProfit = order.takeProfit;
    }

    public OrderOutcome(Position position) {
        super(position);
    }

    public LocalDateTime getExitTradeTime() {
        return exitTradeTime;
    }

    public void setExitTradeTime(LocalDateTime exitTradeTime) {
        this.exitTradeTime = exitTradeTime;
    }

    public Outcome getOutcome() {
        return outcome;
    }

    public Double getExitTradePrice() {
        return exitTradePrice;
    }

    public void setExitTradePrice(Double exitTradePrice) {
        this.exitTradePrice = exitTradePrice;
    }

    public void setOutcome(Outcome outcome) {
        this.outcome = outcome;
    }

    public Double getPipsDifference() {
        return pipsDifference;
    }

    public void setPipsDifference(Double pipsDifference) {
        this.pipsDifference = pipsDifference;
    }

    public enum Outcome
    {
        PROFIT,
        LOSS
    }
}
