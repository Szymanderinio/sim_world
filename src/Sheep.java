public class Sheep extends Animal{

    public Sheep(Organism organism, Position position, World world) {
        super(organism, position, world);
    }

    @Override
    public void initParams() {
        this.setPower(3);
        this.setInitiative(3);
        this.setLiveLength(10);
        this.setPowerToReproduce(6);
        this.setSign(ConsoleColors.CYAN_BRIGHT + "[S]" + ConsoleColors.RESET);
    }

    @Override
    public Organism clone() {
        return new Sheep(new Sheep(null,null,null),null,null);
    }
}
