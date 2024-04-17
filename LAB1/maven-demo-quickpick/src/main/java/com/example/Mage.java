package com.example;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Mage implements Comparable <Mage> {
 private String name;
 private int level;
 private double power;
 private Set<Mage> apprentices;
 
 public Mage(int level, String name, double power) {
        this.level = level;
        this.name = name;
        this.power = power;
        this.apprentices = new HashSet<>();
    }
 public int getLevel() {
        return level;
 }

 public String getName() {
        return name;
 }

 public double getPower() {
        return power;
 }

 public Set<Mage> getApprentices() {
        return apprentices;
 }

 public void addApprentice(Mage apprentice) {
        apprentices.add(apprentice);
 }

 public static void printSet(Set<Mage> set, String prefix) {
    for (Mage mage : set) {
        System.out.println(prefix + mage.toString());
        if (!mage.getApprentices().isEmpty()) {
            printSet(mage.getApprentices(), "-" + prefix);
        }
    }
}

 @Override
 public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Mage other = (Mage) obj;
        if (level != other.level) return false;
        if (Double.compare(other.power, power) != 0) return false;
        return name.equals(other.name);
     }
 @Override
 public int hashCode() {
     final int prime = 31;
     int result = 1;
     result = prime * result + ((name == null) ? 0 : name.hashCode());
     long temp;
     temp = Double.doubleToLongBits(power);
     result = prime * result + (int) (temp ^ (temp >>> 32))+level;
     return result;
    }
 
 @Override
 public int compareTo(Mage otherMage) {
    int nameComparison = this.name.compareTo(otherMage.name);
    if (nameComparison != 0) {
        return nameComparison;
    }
    if (this.level != otherMage.level) {
        return Integer.compare(this.level, otherMage.level);
    }
    return Double.compare(this.power, otherMage.power);
 }
    
  @Override
    public String toString() {
        return "Mage{" +
                "level=" + level +
                ", name='" + name + '\'' +
                ", power=" + power +
                '}';
    }

    public static Map<Mage, Integer> generateDescendantStatistics(Set<Mage> set, boolean sort) {
        Map<Mage, Integer> descendantStats;
        if (sort) {
            descendantStats = new TreeMap<>();
        } else {
            descendantStats = new HashMap<>();
        }
        for (Mage mage : set) {
            int descendantsCount = countDescendants(mage);
            descendantStats.put(mage, descendantsCount);
        }
        return descendantStats;
    }

    // recoursively counting descendants
    private static int countDescendants(Mage mage) {
        int count = 0;
        for (Mage apprentice : mage.getApprentices()) {
            count++;
            count += countDescendants(apprentice);
        }
        return count;
    }

    public static void printDescendantStatistics(Map<Mage, Integer> descendantStats) {
        for (Map.Entry<Mage, Integer> entry : descendantStats.entrySet()) {
            System.out.println("Mage: " + entry.getKey().getName() + ", Descendants: " + entry.getValue());
        }
    }
}
