import java.util.Comparator;

public class OrganismInitiativeDesc implements Comparator<Organism> {
    @Override
    public int compare(Organism o1, Organism o2) {
        Integer o11 = o1.getInitiative();
        Integer o22 = o2.getInitiative();
        return o22.compareTo(o11);
    }
}
