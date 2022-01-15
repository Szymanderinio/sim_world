public class Main {

    public static void main(String[] args) {
        World jaWorld = new World(5, 5);

        Organism newOrg = new Grass(null, new Position(2,2), jaWorld);
        jaWorld.addOrganism(newOrg);

        for (int i = 0; i < 30; i ++) {
            jaWorld.makeTurn();
            System.out.println(jaWorld.toString());
        }

    }
}
