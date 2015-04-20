package view;

import control.Assigner;
import control.Generator;
import model.Circuit;
import model.Juggler;
import model.ListSet;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by ben on 3/25/15.
 */
public class JuggleFest {

    public static void main (String[] args) throws IOException {

        if (args.length < 1) {
            System.out.println("please specify input file");
            System.exit(0);
        }

        String filename = args[0];

        File test = new File(filename);
        if (!test.exists() || test.isDirectory()) {
            System.out.println("specified input file doesn't exist or is a directory :(");
            System.exit(0);
        }

        //generate lists
        ListSet generated = Generator.generateLists(filename);

        //initialize sets
        HashMap<Integer, Juggler> jugglers = generated.getJugglers();
        HashMap<Integer, Circuit> circuits = generated.getCircuits();

        //set max circuit size
        Integer ratio = jugglers.size()/circuits.size();
        Circuit.setMaxSize(ratio);

        //assign jugglers
        Assigner.assign(jugglers, circuits);

        //prepare output
        FileWriter out = new FileWriter("output.txt");

        //write output
        for (Circuit circuit : circuits.values()) {

            String line = " ";

            line = line.concat("C" + circuit.getNumber());
            for (Juggler juggler : circuit.getJugglers()) {
                line = line.concat(" J" + juggler.getNumber() + " ");
                for (Integer desired : juggler.getDesired_circuits()) {
                    Circuit c2 = circuits.get(desired);
                    line = line.concat("C" + c2.getNumber() + ":" + Circuit.getDotProduct(juggler, c2.getStats()) + " ");
                }
            }
            out.write(line + "\n");
        }

        out.close();

        //get email
        if (circuits.containsKey(1970)) {
            System.out.println("\nemail should be sent to " + circuits.get(1970).sumJugglers() + "@yodle.com");
        }
    }
}
