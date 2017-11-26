package controller;

import game.Game;
import menu.MainMenu;
import processing.core.PApplet;

public class StateManager {
	
	private State state; // Current state of the game
	private MainMenu mainMenu;
	private Game game;
	
	public StateManager(PApplet canvas) {
		this.state = State.MainMenu; // Start the game at the main menu
		this.mainMenu = new MainMenu(canvas);
		this.game = new Game(canvas);
	}
	
	public void draw() {
		switch (state) {
		case MainMenu:
			mainMenu.draw();
			break;
		case Game:
			game.draw();
			break;
		default:
			mainMenu.draw();
			break;
		}
	}

}
