import java.util.ArrayList;
import java.util.Random;

public class Alien extends Organism {
    Position lastPosition;
    Position vanish = new Position(-2,-2);

    public Alien(Organism organism, Position position, World world) {
        super(organism, position, world);
    }

    @Override
    public ArrayList<Action> move() {
        ArrayList<Action> result = new ArrayList<>();
        Position newPosition;

        if (this.getWorld().getTurn() % 2 == 0 && this.getWorld().getTurn() != 0) {
            if (getPosition() != vanish) {
                lastPosition = getPosition();
            }
            setPosition(vanish);
        }
        else {
            if (lastPosition != null) {
                setPosition(lastPosition);
            }
        }

        ArrayList<Position> pomPositions = getNeighboringPositions(getPosition());
        if (pomPositions.size() > 0) {
            Random ran = new Random();
            ActionEnum ae = new ActionEnum();
            newPosition = pomPositions.get(ran.nextInt(pomPositions.size()));
            result.add(new Action(ae.A_MOVE, newPosition, 0, this));
            lastPosition = getPosition();
            Organism metOrganism = this.getWorld().getOrganismFromPosition(newPosition);
            if (metOrganism != null) {
                result.addAll(metOrganism.consequences(this));
            }
        }

        return result;
    }

    @Override
    public ArrayList<Action> action() {
        return new ArrayList<>();
    }

    @Override
    public void initParams() {
        this.setPower(100);
        this.setInitiative(4);
        this.setLiveLength(32);
        this.setPowerToReproduce(129);
        this.setSign(ConsoleColors.PURPLE_BACKGROUND_BRIGHT + ConsoleColors.WHITE_BOLD + "[A]" + ConsoleColors.RESET);
    }

    @Override
    public Organism clone() {
        return null;
    }

    public ArrayList<Position> getNeighboringPositions(Position position) {
        return this.getWorld().getNeighboringPositions(position);
    }
}
