public class Dandelion extends Plant {

    public Dandelion(Organism organism, Position position, World world) {
        super(organism, position, world);
    }

    @Override
    public void initParams() {
        this.setPower(0);
        this.setInitiative(0);
        this.setLiveLength(8);
        this.setPowerToReproduce(4);
        this.setSign(ConsoleColors.YELLOW_BOLD + "[D]" + ConsoleColors.RESET);
    }

    @Override
    public Organism clone() {
        return new Dandelion(new Dandelion(null,null,null), null, null);
    }
}
