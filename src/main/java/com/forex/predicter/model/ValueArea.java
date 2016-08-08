package com.forex.predicter.model;

public class ValueArea
{
    private Candlestick min;
    private Candlestick max;

    public ValueArea(Candlestick min, Candlestick max) {
        this.min = min;
        this.max = max;
    }

    public Candlestick getMin() {
        return min;
    }

    public void setMin(Candlestick min) {
        this.min = min;
    }

    public Candlestick getMax() {
        return max;
    }

    public void setMax(Candlestick max) {
        this.max = max;
    }
}
