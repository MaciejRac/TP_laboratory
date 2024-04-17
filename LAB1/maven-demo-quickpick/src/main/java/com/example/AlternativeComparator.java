package com.example;

import java.util.Comparator;

public class AlternativeComparator implements Comparator<Mage> {
    @Override
    public int compare(Mage mage1, Mage mage2) {
        int nameComparison = mage1.getName().compareTo(mage2.getName());
        if (mage1.getLevel() != mage2.getLevel()) {
            return Integer.compare(mage1.getLevel(), mage2.getLevel());
        }
        if (nameComparison != 0) {
            return nameComparison;
        }
        return Double.compare(mage1.getPower(), mage2.getPower());
    }
}
