
public class Action {
    private int action;
    private Position position;
    private int value;
    private Organism organism;

    public Action(int action, Position position, int value, Organism organism) {
        this.action = action;
        this.position = position;
        this.value = value;
        this.organism = organism;
    }

    public int getAction() {
        return action;
    }

    public Position getPosition() {
        return position;
    }

    public int getValue() {
        return value;
    }

    public Organism getOrganism() {
        return organism;
    }

    @Override
    public String toString() {
        String className = organism.getClass().toString();
        ActionEnum ae = new ActionEnum();
        String[] choice = new String[4];
        choice[ae.A_ADD] = className + ": add at: " + position.toString();
        choice[ae.A_INCREASEPOWER] = className + " increase power: " + value;
        choice[ae.A_MOVE] = className + " move from: " + organism.getPosition().toString()
                + " to: " + position.toString();
        choice[ae.A_REMOVE] = className + " remove from: " + organism.getPosition().toString();

        return choice[action];
    }
}
