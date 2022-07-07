package com.projiectfinal.model;

public class pieChart {
    private String name;
    private int value;

    public pieChart() {
    }

    public pieChart(String sex, int value) {
        this.name = sex;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String sex) {
        this.name = sex;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }


    @Override
    public String toString() {
        return "{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
