package com.example;

import java.util.ArrayList;
import java.util.List;
//import javafx.util.Pair;

public class Output {
    private final List<Number> results = new ArrayList<>();
    //private boolean isPrimary=false;

    public synchronized void addResult(Long number,List<Long>dzielniki) {
        Number number_in = new Number(number,dzielniki);
        results.add(number_in);
    }

    public synchronized List<Number> getResults() {
            return new ArrayList<>(results);
        }
}
