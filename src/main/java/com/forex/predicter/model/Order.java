package com.forex.predicter.model;

import java.time.LocalDateTime;

public class Order
{
    private LocalDateTime enterTradeTime;

    private LocalDateTime exitTradeTime;

    private Double pipsDifference;

    private Position position;

    private Double stopLoss;

    private Double takeProfit;

    public Order(LocalDateTime enterTradeTime, Position position, Double stopLoss, Double takeProfit) {
        this.enterTradeTime = enterTradeTime;
        this.position = position;
        this.stopLoss = stopLoss;
        this.takeProfit = takeProfit;
    }

    public Order(Position position) {
        this.position = position;
    }

    public Order() {
        this.position = Position.NO_ACTION;
    }

    public LocalDateTime getEnterTradeTime() {
        return enterTradeTime;
    }

    public void setEnterTradeTime(LocalDateTime enterTradeTime) {
        this.enterTradeTime = enterTradeTime;
    }

    public LocalDateTime getExitTradeTime() {
        return exitTradeTime;
    }

    public void setExitTradeTime(LocalDateTime exitTradeTime) {
        this.exitTradeTime = exitTradeTime;
    }

    public Double getPipsDifference() {
        return pipsDifference;
    }

    public void setPipsDifference(Double pipsDifference) {
        this.pipsDifference = pipsDifference;
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
