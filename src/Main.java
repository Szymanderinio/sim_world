public class Main {

    public static void main(String[] args) {
        World jaWorld = new World(10, 10);

        Organism newOrg = new Grass(null, new Position(1,1), jaWorld);
        jaWorld.addOrganism(newOrg);
        Organism newOrg2 = new Grass(null, new Position(9,9), jaWorld);
        jaWorld.addOrganism(newOrg2);
        Organism newOrg5 = new Sheep(null, new Position(2,2), jaWorld);
        jaWorld.addOrganism(newOrg5);


        for (int i = 0; i < 30; i ++) {
            jaWorld.makeTurn();
            System.out.println(jaWorld.toString());
        }

    }
}
