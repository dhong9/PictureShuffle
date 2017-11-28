package controller;

import game.Game;
import menu.ImageSelectionMenu;
import menu.MainMenu;
import processing.core.PApplet;

public class StateManager {
	
	private State state; // Current state of the game
	private MainMenu mainMenu;
	private ImageSelectionMenu imageSelectionMenu;
	private Game game;
	
	public StateManager(PApplet canvas) {
		this.state = State.MainMenu; // Start the game at the main menu
		this.mainMenu = new MainMenu(canvas);
		this.imageSelectionMenu = new ImageSelectionMenu(canvas);
		this.game = new Game(canvas);
	}
	
	public void draw() {
		switch (state) {
		case MainMenu:
			mainMenu.draw();
			break;
		case ImageSelectionMenu:
			imageSelectionMenu.draw();
			break;
		case Game:
			game.draw();
			break;
		default:
			mainMenu.draw();
			break;
		}
	}
	
	public void mouseClicked() {
		switch (state) {
		case MainMenu:
			mainMenu.mouseClicked();
			state = mainMenu.getDestination();
			break;
		default:
			break;
		}
	}

}
