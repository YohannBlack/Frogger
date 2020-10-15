package gameCommons;

import graphicalElements.Element;
import graphicalElements.IFroggerGraphics;

import java.awt.*;
import java.util.Random;

public class Game {

	public final Random randomGen = new Random();

	// Caracteristique de la partie
	public final int width;
	public final int height;
	public final int minSpeedInTimerLoops;
	public final double defaultDensity;

//	public int chrono = 0; //Pour le timer

	// Lien aux objets utilis�s
	public IEnvironment environment;
	private IFrog frog;
	private IFroggerGraphics graphic;
	public int score;

	/**
	 * 
	 * @param graphic
	 *            l'interface graphique
	 * @param width
	 *            largeur en cases
	 * @param height
	 *            hauteur en cases
	 * @param minSpeedInTimerLoop
	 *            Vitesse minimale, en nombre de tour de timer avant d�placement
	 * @param defaultDensity
	 *            densite de voiture utilisee par defaut pour les routes
	 */
	public Game(IFroggerGraphics graphic, int width, int height, int minSpeedInTimerLoop, double defaultDensity) {
		super();
		this.graphic = graphic;
		this.width = width;
		this.height = height;
		this.minSpeedInTimerLoops = minSpeedInTimerLoop;
		this.defaultDensity = defaultDensity;
		this.score = 0;
	}

	/**
	 * Lie l'objet frog � la partie
	 * 
	 * @param frog
	 */
	public void setFrog(IFrog frog) {
		this.frog = frog;
	}

	public boolean isInfinite(){
		return this.frog.getClass().getName().equals("frog.FrogInf");
	}

	/**
	 * Lie l'objet environment a la partie
	 * 
	 * @param environment
	 */
	public void setEnvironment(IEnvironment environment) {
		this.environment = environment;
	}

	/**
	 * 
	 * @return l'interface graphique
	 */
	public IFroggerGraphics getGraphic() {
		return graphic;
	}

	/**
	 * Teste si la partie est perdue et lance un �cran de fin appropri� si tel
	 * est le cas
	 * 
	 * @return true si le partie est perdue
	 */
	public boolean testLose() {
//		System.out.println(this.frog.getPosition().ord);
		if(this.isInfinite()) {
			if (!this.environment.isSafe(this.frog.getPosition())) {
				this.graphic.endGameScreen("Game over" + " Score : " + this.score);
				return true;
			}
		} else {
			if (!this.environment.isSafe(this.frog.getPosition())) {
				this.graphic.endGameScreen("Game over");
				return true;
			}
		}
		return false;
	}

	/**
	 * Teste si la partie est gagnee et lance un �cran de fin appropri� si tel
	 * est le cas
	 * 
	 * @return true si la partie est gagn�e
	 */
	public boolean testWin() {
		if(this.environment.isWinningPosition(this.frog.getPosition())){
			this.graphic.endGameScreen("Win");
			return true;
		}
		return false;
	}

	/**
	 * Actualise l'environnement, affiche la grenouille et verifie la fin de
	 * partie.
	 */
	public void update() {
		graphic.clear();
		environment.update();
		this.graphic.add(new Element(frog.getPosition(), Color.GREEN));
		testLose();

		testWin();

//		Timer timer = new Timer(1000, new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				chrono++; //On incremente a chaque second
//			}
//		});
	}

}
