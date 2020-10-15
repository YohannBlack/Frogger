package frog;

import gameCommons.Case;
import gameCommons.Direction;
import gameCommons.Game;

public class FrogInf extends Frog {

    public FrogInf(Game game) {
        super(game);
        this.position = new Case(this.game.width/2, 1);
    }

    @Override
    public void move(Direction key){
        this.direction = key;

        //Meme chose sauf qu'on rajoute une Lane a chaque fois qu'on avance
        if(key == Direction.up) {
            //this.position = new Case(this.position.absc, this.position.ord);
            this.game.score++;
            this.game.environment.up();
            this.game.testLose();
        }
        else if(key == Direction.down) {
            //this.position = new Case(this.position.absc, this.position.ord - 1);
            this.game.score--;
            this.game.environment.down();
            this.game.testLose();
        }
        else if (key == Direction.right && this.position.absc < this.game.width - 1)
            this.position = new Case(this.position.absc + 1, this.position.ord);
        else if (key == Direction.left && this.position.absc > 0)
            this.position = new Case(this.position.absc - 1, this.position.ord);

        this.game.testLose();
        //Pas besoin de tester si on gagne puisque infini
    }
}
