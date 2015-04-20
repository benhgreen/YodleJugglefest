package model;

import java.util.HashMap;

/**Used as a return object for Generator.generateLists()
 * Created by ben on 3/25/15.
 */
public class ListSet {

    private HashMap<Integer, Juggler> jugglers;
    private HashMap<Integer, Circuit> circuits;

    public ListSet (HashMap<Integer, Juggler> jugglers, HashMap<Integer, Circuit> circuits) {
        this.jugglers = jugglers;
        this.circuits = circuits;
    }

    public HashMap<Integer, Circuit> getCircuits() {
        return circuits;
    }

    public HashMap<Integer, Juggler> getJugglers() {
        return jugglers;
    }



}
