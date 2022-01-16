import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class JuniTest {


    @Test
    public void wolfVsSheepConsequences() {
        Position temp = new Position(1,1);
        Wolf wolf = new Wolf(null, temp, null);
        wolf.initParams();
        Sheep sheep = new Sheep(null, temp, null);
        sheep.initParams();
        ArrayList<Action> actionArrayList = wolf.consequences(sheep);
        System.out.println(actionArrayList.get(0));
        assertEquals("class Sheep remove from: (1 1)", actionArrayList.get(0).toString());

    }

    @Test
    public void wolfVsToadstool() {
        Position temp = new Position(1,1);
        Wolf wolf = new Wolf(null, temp, null);
        wolf.initParams();
        Toadstool ts = new Toadstool(null, temp, null);
        ts.initParams();
        ArrayList<Action> actionArrayList = ts.consequences(wolf);
        System.out.println(actionArrayList.get(0));
        assertEquals("class Toadstool remove from: (1 1)\n" +
                        "class Wolf remove from: (1 1)",
                actionArrayList.get(0).toString() + "\n" +actionArrayList.get(1).toString());

    }
}