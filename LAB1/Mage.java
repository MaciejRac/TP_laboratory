import java.util.Set;

public class Mage implements Comparable <Mage> {
 private String name;
 private int level;
 private double power;
 private Set<Mage> apprentices;
 
 public void equals(double amount) {
        
    }
public void hashCode(double amount) {
        
    }
public int compareTo(Mage otherMage) {
    return Integer.compare(this.level, otherMage.level);
}
}
