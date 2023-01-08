package com.myisu_1.isu.models.Phone;

public class From_whereTo_where {

    String from_where;
    String model;
    String to_where;

    public From_whereTo_where(String from_where, String model, String to_where) {
        this.from_where = from_where;
        this.model = model;
        this.to_where = to_where;
    }

    public String getFrom_where() {
        return from_where;
    }

    public void setFrom_where(String from_where) {
        this.from_where = from_where;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTo_where() {
        return to_where;
    }

    public void setTo_where(String to_where) {
        this.to_where = to_where;
    }
}
