package frog;

import gameCommons.Case;
import gameCommons.Direction;
import gameCommons.Game;
import gameCommons.IFrog;

public class Frog implements IFrog {
	
	protected Game game;
	protected Case position;
	protected Direction direction;

	public Frog(Game game){
		this.game = game;
		this.position = new Case(game.width/2, 0);
		this.direction = null;
	}

	@Override
	public Case getPosition() {
		return this.position;
	}

	@Override
	public Direction getDirection() {
		return this.direction;
	}

	@Override
	public void move(Direction key) {
		if(key == Direction.up && this.position.ord < game.height - 1)
			this.position = new Case(this.position.absc, this.position.ord + 1);
		else if(key == Direction.down && this.position.ord > 1)
			this.position = new Case(this.position.absc, this.position.ord -1);
		else if(key == Direction.left && this.position.absc > 1)
			this.position = new Case(this.position.absc - 1, this.position.ord);
		else if(key == Direction.right && this.position.absc < game.width - 1)
			this.position = new Case(this.position.absc + 1, this.position.ord);

		this.game.testWin();
		this.game.testLose();
	}
}
