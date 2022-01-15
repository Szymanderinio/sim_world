import java.util.ArrayList;
import java.util.Random;

abstract class Animal extends Organism {
    private Position lastPosition;

    public Animal(Organism organism, Position position, World world) {
        super(organism, position, world);
        lastPosition = position;
    }

    public Position getLastPosition() {
        return lastPosition;
    }

    public void setLastPosition(Position lastPosition) {
        this.lastPosition = lastPosition;
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
            lastPosition = getPosition();
            Organism metOrganism = this.getWorld().getOrganismFromPosition(newPosition);
            if (metOrganism != null) {
                result.add(metOrganism.consequences(this));
            }
        }
        return result;
    }

    @Override
    public ArrayList<Action> action() {
        ArrayList<Action> result = new ArrayList<>();
        Animal newAnimal;
        Position newAnimalPosition;
        ArrayList<Position> birthPositions = getNeighboringBirthPosition();

        if (this.ifReproduce() && birthPositions.size() > 0) {
            Random ran = new Random();
            ActionEnum ae = new ActionEnum();

            newAnimalPosition = birthPositions.get(ran.nextInt(birthPositions.size()));
            newAnimal = (Animal) clone();
            newAnimal.initParams();
            newAnimal.setPosition(newAnimalPosition);
            newAnimal.setWorld(getWorld());
            this.setPower(this.getPower()/2);
            result.add(new Action(ae.A_ADD, newAnimalPosition, 0, newAnimal));
        }
        return result;
    }

    public ArrayList<Position> getNeighboringPositions() {
        return this.getWorld().getNeighboringPositions(getPosition());
    }

    public ArrayList<Position> getNeighboringBirthPosition() {
        return this.getWorld().filterFreePositions(this.getWorld().getNeighboringPositions(getPosition()));
    }
}
