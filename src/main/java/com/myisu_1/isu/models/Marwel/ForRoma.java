package com.myisu_1.isu.models.Marwel;

public class ForRoma {
    String phone;
    String quantity;
    String amount;
    String things;
    String rubles;

    public ForRoma() {
    }

    public ForRoma(String phone, String quantity, String amount, String things, String rubles) {
        this.phone = phone;
        this.quantity = quantity;
        this.amount = amount;
        this.things = things;
        this.rubles = rubles;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getThings() {
        return things;
    }

    public void setThings(String things) {
        this.things = things;
    }

    public String getRubles() {
        return rubles;
    }

    public void setRubles(String rubles) {
        this.rubles = rubles;
    }
}
