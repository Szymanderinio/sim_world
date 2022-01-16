public class Main {

    public static void main(String[] args) {
        World jaWorld = new World(20, 10);

        Organism newOrg = new Grass(null, new Position(1,1), jaWorld);
        jaWorld.addOrganism(newOrg);
        Organism newOrg2 = new Dandelion(null, new Position(9,1), jaWorld);
        jaWorld.addOrganism(newOrg2);
        Organism newOrg3 = new Wolf(null, new Position(15,7), jaWorld);
        jaWorld.addOrganism(newOrg3);
        Organism newOrg4 = new Toadstool(null, new Position(19,1), jaWorld);
        jaWorld.addOrganism(newOrg4);
        Organism newOrg5 = new Sheep(null, new Position(4,4), jaWorld);
        jaWorld.addOrganism(newOrg5);
        Organism newOrg6 = new Alien(null, new Position(5,5), jaWorld);
        jaWorld.addOrganism(newOrg6);


        for (int i = 0; i < 30; i ++) {
            jaWorld.makeTurn();
            System.out.println(jaWorld.toString());
        }

    }
}
