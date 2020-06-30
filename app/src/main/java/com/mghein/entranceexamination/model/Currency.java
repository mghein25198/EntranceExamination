package com.mghein.entranceexamination.model;

public class Currency {
    String unit,amount;

    public Currency(String unit, String amount) {
        this.unit = unit;
        this.amount = amount;
    }

    public Currency() {
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "unit='" + unit + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}
