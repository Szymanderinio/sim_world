public class Grass extends Plant{

    public Grass(Organism organism, Position position, World world) {
        super(organism, position, world);
    }

    @Override
    public void initParams() {
        this.setPower(0);
        this.setInitiative(0);
        this.setLiveLength(6);
        this.setPowerToReproduce(3);
        this.setSign("[G]");
    }

    @Override
    public Organism clone() {
        return new Grass(this, null, null);
    }
}
