package environment;

import gameCommons.Case;
import gameCommons.Game;
import gameCommons.IEnvironment;

import java.util.ArrayList;

public class Environment implements IEnvironment {
		
	private Game game;
	private ArrayList<Lane> numberOfLanes;

    public Environment(Game game) {
        this.game = game;
        if(this.game.isInfinite()){
            this.numberOfLanes = initializeLaneInf();
        } else {
            this.numberOfLanes = initializeLanes();
        }
    }


    private ArrayList<Lane> initializeLanes(){
        ArrayList<Lane> initialized = new ArrayList<>();
        initialized.add(new Lane(game, 0, 0));

        for(int i = 1; i < this.game.height - 1; i++){
            initialized.add(new Lane(game, i, this.game.defaultDensity));
        }
        initialized.add(new Lane(game, this.game.height - 1, 0));
        return initialized;
    }

    private ArrayList<Lane> initializeLaneInf(){
        ArrayList<Lane> initialized = new ArrayList<>();
        initialized.add(new Lane(game, 0, 0));
        initialized.add(new Lane(game, 1, 0));

        for(int i = 2; i < this.game.height; i++){
            initialized.add(new Lane(game, i, this.game.defaultDensity));
        }
        return initialized;
    }

    @Override
    //Code de Martin
    public boolean isSafe(Case c) {
//        System.out.println(c.ord);
//        System.out.println(this.numberOfLanes.get(c.ord).isSafe(c));
        for(Lane l : this.numberOfLanes) {
            if (l.ord == c.ord)
                return l.isSafe(c);
        }
        return false;
    }

    @Override
    public boolean isWinningPosition(Case c) {
        return (c.ord == this.game.height - 1);
    }

    @Override
    public void update() {
        for(Lane l : this.numberOfLanes)
            l.update();
    }

    private int getMaxOrd(){
        int max = -1;
        for(Lane l : numberOfLanes){
            if(max < l.ord)
                max = l.ord;
        }

        return max;
    }

    private int getMinOrd(){
        int min = 999999;
        for(Lane l : numberOfLanes){
            if(min > l.ord)
                min = l.ord;
        }

        return min;
    }

    public void up(){
        for(Lane l : numberOfLanes)
            l.move();

        if(getMaxOrd() < game.height)
            this.numberOfLanes.add(new Lane(this.game, getMaxOrd()+1, this.game.defaultDensity));
    }

    public void down(){
        for(Lane l : numberOfLanes)
            l.moveDown();
        if(getMinOrd() < 0)
            this.numberOfLanes.add(new Lane(this.game, getMinOrd() -1, this.game.defaultDensity));
    }
}
