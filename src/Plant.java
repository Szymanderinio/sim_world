import java.util.ArrayList;
import java.util.Random;

abstract class Plant extends Organism {

    public Plant(Organism organism, Position position, World world) {
        super(organism, position, world);
    }

    @Override
    public ArrayList<Action> move() {
        return new ArrayList<>();
    }

    @Override
    public ArrayList<Action> action() {
        ArrayList<Action> result = new ArrayList<>();
        Plant newPlant;
        Position newPosition;

        if (this.ifReproduce()) {
            ArrayList<Position> pomPositions = getFreeNeighboringPosition(this.getPosition());
            if (pomPositions.size() > 0) {
                Random ran = new Random();
                ActionEnum ae = new ActionEnum();

                newPosition = pomPositions.get(ran.nextInt(pomPositions.size()));
                newPlant = (Plant) clone();
                newPlant.setPosition(newPosition);
                newPlant.setWorld(getWorld());
                this.setPower(this.getPower()/2);
                result.add(new Action(ae.A_ADD, newPosition, 0, newPlant));

            }
        }
        return result;
    }

    public ArrayList<Position> getFreeNeighboringPosition(Position position) {
        return this.getWorld().filterFreePositions(this.getWorld().getNeighboringPositions(position));
    }
}
