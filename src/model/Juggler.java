package model;

import java.util.ArrayList;

/**Model for Juggler
 * Created by ben on 3/25/15.
 */
public class Juggler{

    private int coordination;
    private int endurance;
    private int pizzaz;

    public int getNumber() {
        return number;
    }

    private int number;
    ArrayList<Integer> desired_circuits;


    public Juggler (int number, int coordination, int endurance, int pizzaz, ArrayList<Integer> desired_circuits) {
        this.coordination = coordination;
        this.endurance = endurance;
        this.pizzaz = pizzaz;
        this.desired_circuits = desired_circuits;
        this.number = number;
    }

    public int getCoordination() {
        return coordination;
    }

    public int getEndurance() {
        return endurance;
    }

    public int getPizzaz() {
        return pizzaz;
    }

    public ArrayList<Integer> getDesired_circuits() {
        return desired_circuits;
    }

}
