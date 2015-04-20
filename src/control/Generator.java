package control;

import model.Circuit;
import model.Juggler;
import model.ListSet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

/**Generates lists of jugglers and circuits from input file
 * Created by ben on 3/25/15.
 */
public class Generator {

    public static ListSet generateLists(String filename) throws IOException {

        //init hashmaps
        HashMap<Integer, Juggler> jugglers = new HashMap<>();
        HashMap<Integer, Circuit> circuits = new HashMap<>();

        //read in file
        BufferedReader br = new BufferedReader(new FileReader(filename));

        String line = br.readLine();

        while (line != null) {

            if (!line.equals("")) {

                StringTokenizer tokz = new StringTokenizer(line);

                //get ID number
                tokz.nextToken();
                Integer number = Integer.parseInt(tokz.nextToken().substring(1));

                //get statz
                Integer coordination = Integer.parseInt(tokz.nextToken().substring(2));
                Integer endurance = Integer.parseInt(tokz.nextToken().substring(2));
                Integer pizzaz = Integer.parseInt(tokz.nextToken().substring(2));

                //if no more tokens, this is a circuit
                if (!tokz.hasMoreTokens()) {
                    circuits.put(number, new Circuit(number, coordination, endurance, pizzaz));

                //otherwise this is a juggler and we should parse in desired circuits
                } else {
                    StringTokenizer tokz2 = new StringTokenizer(tokz.nextToken(), ",");
                    ArrayList<Integer> desired_circuits = new ArrayList<>();

                    while (tokz2.hasMoreTokens()) {
                        desired_circuits.add(Integer.parseInt(tokz2.nextToken().substring(1)));
                    }

                    jugglers.put(number, new Juggler(number, coordination, endurance, pizzaz, desired_circuits));
                }
            }

            line = br.readLine();
        }

        return new ListSet(jugglers,circuits);
    }
}
