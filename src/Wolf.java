import java.util.ArrayList;
import java.util.Random;

public class Wolf extends Animal {
    public Wolf(Organism organism, Position position, World world) {
        super(organism, position, world);
    }

    @Override
    public void initParams() {
        this.setPower(8);
        this.setInitiative(4);
        this.setLiveLength(15);
        this.setPowerToReproduce(13);
        this.setSign(ConsoleColors.WHITE_BACKGROUND + ConsoleColors.BLACK_BOLD + "[W]" + ConsoleColors.RESET);
    }

    @Override
    public ArrayList<Action> move() {
        ArrayList<Action> result = new ArrayList<>();
        ArrayList<Position> pomPositions = getNeighboringPositions();
        Position newPosition;

        if (pomPositions.size() > 0) {
            Random ran = new Random();
            ActionEnum ae = new ActionEnum();
            newPosition = pomPositions.get(ran.nextInt(pomPositions.size()));
            result.add(new Action(ae.A_MOVE, newPosition, 0, this));
            this.setLastPosition(getPosition());
            Organism metOrganism = this.getWorld().getOrganismFromPosition(newPosition);
            if (metOrganism != null && metOrganism.getClass() != Plant.class) {
                result.addAll(metOrganism.consequences(this));
            }
        }
        return result;
    }

    @Override
    public ArrayList<Position> getNeighboringPositions() {
        return this.getWorld().filterPositionsWithOtherSpecies(
                this.getWorld().getNeighboringPositions(this.getPosition()), Wolf.class);
    }

    @Override
    public Organism clone() {
        return new Wolf(new Wolf(null,null,null), null, null);
    }
}
