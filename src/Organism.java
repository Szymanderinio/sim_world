import java.util.ArrayList;

abstract class Organism {
    private int power;
    private int initiative;
    private Position position;
    private int liveLength;
    private int powerToReproduce;
    private String sign;
    private World world;

    public Organism(Organism organism, Position position, World world) {
        if (organism != null) {
            this.power = organism.power;
            this.initiative = organism.initiative;
            this.liveLength = organism.liveLength;
            this.powerToReproduce = organism.powerToReproduce;
            this.sign = organism.sign;
        }
        else {
            if (position != null) {
                this.position = position;
            }
            if (world != null) {
                this.world = world;
            }
            initParams();
        }
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getInitiative() {
        return initiative;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getLiveLength() {
        return liveLength;
    }

    public void setLiveLength(int liveLength) {
        this.liveLength = liveLength;
    }

    public int getPowerToReproduce() {
        return powerToReproduce;
    }

    public void setPowerToReproduce(int powerToReproduce) {
        this.powerToReproduce = powerToReproduce;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public abstract ArrayList<Action> move();

    public abstract ArrayList<Action> action();

    public abstract void initParams();

    public abstract Organism clone();

    public ArrayList<Action> consequences(Organism attackingOrganism) {
        ArrayList<Action> result = new ArrayList<>();
        ActionEnum ae = new ActionEnum();

        if (power > attackingOrganism.getPower()) {
            result.add(new Action(ae.A_REMOVE, new Position(-1,-1), 0, attackingOrganism));
        }
        else {
            result.add(new Action(ae.A_REMOVE, new Position(-1,-1), 0, this));
        }
        return result;
    }

    public boolean ifReproduce() {
        return (power >= powerToReproduce);
    }

    @Override
    public String toString() {
        return this.getClass().getName() + ": power: " + power + " initiative: "
                + initiative + " liveLength " + liveLength + " position: " + position;
    }
}
