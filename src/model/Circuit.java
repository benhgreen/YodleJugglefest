package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/**Model for Circuit
 * Created by ben on 3/25/15.
 */
public class Circuit {

    private int coordination;
    private int endurance;
    private int pizzaz;
    private static int maxsize;

    public int getNumber() {
        return number;
    }

    public static void setMaxSize(int maxsize) {
        Circuit.maxsize = maxsize;
    }

    private int number;
    private ArrayList<Juggler> jugglers;

    public Circuit (int number, int coordination, int endurance, int pizzaz) {
        this.coordination = coordination;
        this.endurance = endurance;
        this.pizzaz = pizzaz;
        this.number = number;
        this.jugglers = new ArrayList<>();
    }

    /**
     * Add juggler to circuit. Will remove inferior juggler if necessary.
     * @param juggler Juggler to add
     * @param jugglers Pool of jugglers to return inferior juggler to
     * @return True if juggler added, false if refused
     */
    public boolean addJuggler(Juggler juggler, HashMap<Integer,Juggler> jugglers) {

        Integer[] circuit_stats = new Integer[]{coordination,endurance,pizzaz};

        if (this.jugglers.size() < maxsize) {
            this.jugglers.add(juggler);
            sortJugglers();
            return true;
        } else {

            //see if there's an inferior juggler we can kick out
            for (Juggler juggler2 : getJugglers()) {
                if (getDotProduct(juggler, circuit_stats) > getDotProduct(juggler2, circuit_stats)) {
                    this.jugglers.add(juggler);

                    sortJugglers();

                    jugglers.put(juggler2.getNumber(), juggler2);
                    this.jugglers.remove(juggler2);
                    return true;
                }
            }

            return false;


        }
    }

    private void sortJugglers() {
        Collections.sort(jugglers, new Comparator<Juggler>() {
            @Override
            public int compare(Juggler j0, Juggler j1) {
                Integer[] circuit_stats = new Integer[]{coordination,endurance,pizzaz};
                return getDotProduct(j0, circuit_stats).compareTo(getDotProduct(j1, circuit_stats));
            }
        });
    }

    /**
     * Get dot product of juggler and circuit
     * @param juggler Juggler to compare
     * @param circuit_stats Circuit stats to compare
     * @return Dot product
     */
    public static Integer getDotProduct(Juggler juggler, Integer[] circuit_stats) {
        Integer coord_total = juggler.getCoordination() * circuit_stats[0];
        Integer endur_total = juggler.getEndurance() * circuit_stats[1];
        Integer pizzaz_total = juggler.getPizzaz() * circuit_stats[2];

        return coord_total + endur_total + pizzaz_total;
    }

    /**
     * Getter for Jugglers
     * @return Jugglers
     */
    public ArrayList<Juggler> getJugglers() {
        return jugglers;
    }

    /**
     * Get stats of circuit
     * @return Integer[coordination, endurance,pizzaz]
     */
    public Integer[] getStats() {
        return new Integer[]{coordination,endurance,pizzaz};
    }

    /**
     * Gets sum of circuit's juggler IDs
     * @return Said sum
     */
    public int sumJugglers() {
        int sum = 0;

        for (Juggler juggler : jugglers) {
            sum += juggler.getNumber();
        }

        return sum;
    }
}
