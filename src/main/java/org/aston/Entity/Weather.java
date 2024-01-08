package org.aston.Entity;

import java.time.LocalDateTime;

public class Weather {

    private String name;
    private String temp_c;
    private String localDateTime;

    public Weather(String name, String temp_c, String localDateTime) {
        this.name = name;
        this.temp_c = temp_c;
        this.localDateTime = localDateTime;
    }

    public Weather() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTemp_c() {
        return temp_c;
    }

    public void setTemp_c(String temp_c) {
        this.temp_c = temp_c;
    }

    public String getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(String localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "name='" + name + '\'' +
                ", temp_c='" + temp_c + '\'' +
                ", localDateTime=" + localDateTime +
                '}';
    }
}
