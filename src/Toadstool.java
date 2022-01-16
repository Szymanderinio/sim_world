import java.util.ArrayList;

public class Toadstool extends Plant{
    public Toadstool(Organism organism, Position position, World world) {
        super(organism, position, world);
    }

    @Override
    public void initParams() {
        this.setPower(0);
        this.setInitiative(0);
        this.setLiveLength(10);
        this.setPowerToReproduce(8);
        this.setSign(ConsoleColors.RED_BOLD + "[T]" + ConsoleColors.RESET);
    }

    @Override
    public ArrayList<Action> consequences(Organism attackingOrganism) {
        ArrayList<Action> result = new ArrayList<>();
        ActionEnum ae = new ActionEnum();

        if (this.getPower() > attackingOrganism.getPower()) {
            result.add(new Action(ae.A_REMOVE, new Position(-1,-1), 0, attackingOrganism));
        }
        else {
            result.add(new Action(ae.A_REMOVE, new Position(-1,-1), 0, this));
            result.add(new Action(ae.A_REMOVE, new Position(-1,-1), 0, attackingOrganism));

        }
        return result;
    }

    @Override
    public Organism clone() {
        return new Toadstool(new Toadstool(null, null, null), null, null);
    }
}
