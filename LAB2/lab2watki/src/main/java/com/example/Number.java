package com.example;

import java.util.ArrayList;
import java.util.List;

public class Number {
    private Long number;
    private boolean isPrimary;
    private List<Long> dzielniki=new ArrayList<>();

    public Number(Long number, List<Long>dzielniki) {
        this.number = number;
        this.dzielniki = dzielniki;
    }

    public Long getNumber() {
        return number;
    }

    public boolean isPrimary() {
        return isPrimary;
    }
    public List<Long> getDzielniki() {
        return dzielniki;
    }
    public void addDzielnik(Long dzielnik) {
        dzielniki.add(dzielnik);
    }

    @Override
    public String toString() {
        StringBuilder wyniki = new StringBuilder();
        if (dzielniki != null) {
            for (Long dzielnik : dzielniki) {
                wyniki.append(dzielnik).append(", ");
            }
            if (wyniki.length() > 0) {
                wyniki.delete(wyniki.length() - 2, wyniki.length());//deleting trach coma spacebar
            }
        }
        return "Liczba podana: " + number + " dzielniki: " + wyniki.toString();
    }
}
