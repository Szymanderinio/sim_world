import java.util.ArrayList;

public class World {
    private final int worldX;
    private final int worldY;
    private int turn = 0;
    private ArrayList<Organism> organisms = new ArrayList<>();
    private ArrayList<Organism> newOrganisms = new ArrayList<>();
    private final String separator = "[ ]";


    public World(int worldX, int worldY) {
        this.worldX = worldX;
        this.worldY = worldY;
    }

    public int getWorldX() {
        return worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public ArrayList<Organism> getOrganisms() {
        return organisms;
    }

    public void setOrganisms(ArrayList<Organism> organisms) {
        this.organisms = organisms;
    }

    public ArrayList<Organism> getNewOrganisms() {
        return newOrganisms;
    }

    public void setNewOrganisms(ArrayList<Organism> newOrganisms) {
        this.newOrganisms = newOrganisms;
    }

    public String getSeparator() {
        return separator;
    }

    public ArrayList<Organism> getAllAliveOrganismsOnBoard() {
        ArrayList<Organism> result = new ArrayList<>();

        for (Organism org : organisms) {
            if (org.getLiveLength() > 0) {
                result.add(org);
            }
        }

        return result;
    }

    public ArrayList<Organism> getAllOrganismsOnBoard(ArrayList<Organism> listOfOrg) {
        ArrayList<Organism> result = new ArrayList<>();

        for (Organism org : listOfOrg) {
            if (positionOnBoard(org.getPosition())) {
                result.add(org);
            }
        }

        return result;
    }

    /* makeTurn*/
    public void makeTurn() {
        ArrayList<Action> actions = new ArrayList<>();

        for (Organism org : organisms) {
            if (positionOnBoard(org.getPosition())) {
                actions = org.move();
                for (Action a : actions) {
                    makeMove(a);
                }
                actions.clear();
                actions = org.action();
                for (Action a : actions) {
                    makeMove(a);
                }
                actions.clear();
            }
        }

        organisms = getAllOrganismsOnBoard(organisms);

        for (Organism o : organisms) {
            o.setLiveLength(o.getLiveLength()-1);
            o.setPower(o.getPower()+1);
            if (o.getLiveLength() < 1) {
                System.out.println(o.getClass().toString() + ": died of old age at: " + o.getPosition().toString());
            }
        }

        organisms = getAllAliveOrganismsOnBoard();
        newOrganisms = getAllOrganismsOnBoard(newOrganisms);
        organisms.addAll(newOrganisms);
        organisms.sort(new OrganismInitiativeDesc());
        newOrganisms.clear();
        turn += 1;
    }

    public void makeMove(Action action) {
        System.out.println(action.toString());
        ActionEnum ae = new ActionEnum();
        if (action.getAction() == ae.A_ADD) {
            newOrganisms.add(action.getOrganism());
        }
        if (action.getAction() == ae.A_INCREASEPOWER) {
            action.getOrganism().setPower(action.getOrganism().getPower() + action.getValue());
        }
        if (action.getAction() == ae.A_MOVE) {
            action.getOrganism().setPosition(action.getPosition());
        }
        if (action.getAction() == ae.A_REMOVE) {
            action.getOrganism().setPosition(new Position(-1,-1));
        }
    }

    public boolean addOrganism(Organism organism) {
        Position newOrgPosition = new Position(organism.getPosition().getX(), organism.getPosition().getY());

        if (positionOnBoard(newOrgPosition)) {
            organisms.add(organism);
            organisms.sort(new OrganismInitiativeDesc());
            return true;
        }
        return false;
    }

    public boolean positionOnBoard(Position position) {
        return (position.getX() >= 0) && (position.getY() >= 0)
                && (position.getX() < worldX) && (position.getY() < worldY);
    }

    public Organism getOrganismFromPosition(Position position) {
        Organism pomOrganism = null;

        for (Organism organism : organisms) {
            if (organism.getPosition().equals(position)) {
                pomOrganism = organism;
                break;
            }
        }
        if (pomOrganism == null) {
            for (Organism organism : newOrganisms) {
                pomOrganism = organism;
                break;
            }
        }

        return pomOrganism;
    }

    public ArrayList<Position> getNeighboringPositions(Position position) {
        ArrayList<Position> result = new ArrayList<>();
        Position pomPosition;

        for (int y = -1; y < 2; y++) {
            for (int x = -1; x < 2; x++) {
                pomPosition = new Position(position.getX()+x, position.getY()+y);
                if (positionOnBoard(pomPosition) && !(y==0 && x == 0)) {
                    result.add(pomPosition);
                }
            }
        }

        return result;
    }

    public ArrayList<Position> filterFreePositions(ArrayList<Position> fields) {
        ArrayList<Position> result = new ArrayList<>();

        for (Position field : fields) {
            if (getOrganismFromPosition(field) == null) {
                result.add(field);
            }
        }

        return result;
    }

    /* BRAKUJE filterPositionsWithoutAnimals(self, fields): */

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("\nTurn: " + turn + "\n");
        for (int wY = 0; wY < worldY; wY++) {
            for (int wX = 0; wX < worldX; wX++) {
                Organism org = getOrganismFromPosition(new Position(wX, wY));
                if (org != null) {
                    result.append(org.getSign());
                }
                else {
                    result.append(separator);
                }
            }
            result.append("\n");
        }
        return result.toString();
    }
}
