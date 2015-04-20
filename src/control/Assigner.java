package control;

import model.Circuit;
import model.Juggler;

import java.util.HashMap;

/**Main control class for JuggleFest
 * Created by ben on 3/25/15.
 */
public class Assigner {

    public static void assign(HashMap<Integer, Juggler> jugglers, HashMap<Integer, Circuit> circuits) {

        while (!jugglers.isEmpty()) {

            Juggler juggler = jugglers.values().iterator().next();

            boolean assigned = false;

            //check juggler's preferred circuits
            for (Integer desired : juggler.getDesired_circuits()) {
                Circuit circuit = circuits.get(desired);
                if (circuit.addJuggler(juggler, jugglers)) {
                    assigned = true;
                    jugglers.remove(juggler.getNumber());
                    break;
                }
            }

            if (!assigned) {
                //place juggler in first other available circuits
                for (Circuit circuit : circuits.values()) {
                    if (circuit.addJuggler(juggler, jugglers)) {
                        jugglers.remove(juggler.getNumber());
                        break;
                    }
                }
            }
        }
    }
}
