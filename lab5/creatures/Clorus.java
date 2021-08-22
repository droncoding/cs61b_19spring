package creatures;

import edu.princeton.cs.algs4.StdRandom;
import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

import static huglife.HugLifeUtils.randomEntry;

public class Clorus extends Creature{
    private int r;
    private int b;
    private int g;

    public Clorus(double e){
        super("clorus");
        r = 0;
        b = 0;
        g = 0;
        energy = e;
    }

    public Clorus(){
        this(1);
    }

    public Color color() {
        r = 34;
        b = 231;
        g = 0;
        return color(r, g, b);
    }

    @Override
    public void attack(Creature c) {
        double cenergy = c.energy();
        energy += cenergy;

    }

    @Override
    public Clorus replicate() {
        energy = energy*0.5;
        Clorus repc = new Clorus(energy);
        return repc;
    }

    @Override
    public void move() {
        energy -= 0.03;
    }

    @Override
    public void stay() {
        energy -= 0.01;
    }

    @Override
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plipDirect = new ArrayDeque<>();
        boolean anyPlip = false;

        for (Direction dir : neighbors.keySet()){
            Occupant occupant = neighbors.get(dir);
            if (occupant.name() == "empty"){
                emptyNeighbors.addLast(dir);
            }
            if (occupant.name()=="plip"){
                anyPlip = true;
                plipDirect.addLast(dir);
            }
        }

        if (emptyNeighbors.isEmpty()) {
            return new Action(Action.ActionType.STAY);
        }

        else if (plipDirect.size()>0){
            return new Action(Action.ActionType.ATTACK,randomEntry(plipDirect));
        }

        else if(energy>= 1){
            return new Action(Action.ActionType.REPLICATE,randomEntry(emptyNeighbors));
        }

        return new Action(Action.ActionType.MOVE,randomEntry(emptyNeighbors));
    }
}
