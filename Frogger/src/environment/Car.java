package environment;

import gameCommons.Case;
import gameCommons.Game;
import graphicalElements.Element;

import java.awt.*;

public class Car {
	private Game game;
	private Case leftPosition;
	private boolean leftToRight;
	private int length;
	private final Color colorLtR = Color.BLACK;
	private final Color colorRtL = Color.BLUE;

	public Car(Game game, Case leftPosition, boolean leftToRight) {
		this.game = game;
		this.leftPosition = leftPosition;
		this.leftToRight = leftToRight;
		this.length = this.game.randomGen.nextInt(3);
	}

	public void moveUp(){
		this.leftPosition = new Case(this.leftPosition.absc, this.leftPosition.ord - 1);
	}

	public void moveDown(){
		this.leftPosition = new Case(this.leftPosition.absc, this.leftPosition.ord + 1);
	}

	private int isGoingLeft(){
		if(this.leftToRight)
			return 1;

		return -1;
	}

	public void move(boolean ok){
		if(ok) {
			int isLeftToRight = this.isGoingLeft();
			this.leftPosition = new Case(this.leftPosition.absc + isLeftToRight, this.leftPosition.ord);
		}
		this.addToGraphics();
	}

	public boolean isSeen(){
		return(this.leftPosition.absc < game.width && this.leftPosition.absc + this.length >= 0);
	}

	public boolean isOn(Case nextCase){
		if(nextCase.ord != this.leftPosition.ord)
			return false;
		else
			return nextCase.absc >= this.leftPosition.absc && nextCase.absc < this.leftPosition.absc + this.length;

	}
	
	/* Fourni : addToGraphics() permettant d'ajouter un element graphique correspondant a la voiture*/
	private void addToGraphics() {
		for (int i = 0; i < length; i++) {
			Color color = colorRtL;
			if (this.leftToRight){
				color = colorLtR;
			}
			game.getGraphic().add(new Element(leftPosition.absc + i, leftPosition.ord, color));
		}
	}

}
