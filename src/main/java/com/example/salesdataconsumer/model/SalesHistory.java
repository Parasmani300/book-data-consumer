package com.example.salesdataconsumer.model;

import java.sql.Timestamp;

public class SalesHistory {
    String uuid;
    Sales sales;
    Timestamp timestamp;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Sales getSales() {
        return sales;
    }

    public void setSales(Sales sales) {
        this.sales = sales;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "SalesHistory{" +
                "uuid='" + uuid + '\'' +
                ", sales=" + sales +
                ", timestamp=" + timestamp +
                '}';
    }
}
