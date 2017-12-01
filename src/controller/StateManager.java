package controller;

import game.Game;
import menu.ImageSelectionMenu;
import menu.MainMenu;
import processing.core.PApplet;

public class StateManager {
	
	private PApplet canvas;
	private State state; // Current state of the game
	private MainMenu mainMenu;
	private ImageSelectionMenu imageSelectionMenu;
	private Game game;
	
	private float x, y, width;
	
	public StateManager(PApplet canvas) {
		this.canvas = canvas;
		boolean horizontal = canvas.height < canvas.width;
		this.width = horizontal ? canvas.height : canvas.width;
		float offset = horizontal ? findOffset(canvas.width, width) :
			findOffset(canvas.height, width);
		this.x = horizontal ? offset : 0;
		this.y = horizontal ? 0 : offset;
		
		this.state = State.MainMenu; // Start the game at the main menu
		this.mainMenu = new MainMenu(canvas, x, y, width);
		this.imageSelectionMenu = new ImageSelectionMenu(canvas, x, y, width);
		this.game = new Game(canvas, x, y, width, "hot-air");
	}
	
	private float findOffset(float sideLength, float actualSize) {
		return 0.5f * (sideLength - actualSize);
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
		case ImageSelectionMenu:
			imageSelectionMenu.mouseClicked();
			game = new Game(canvas, x, y, width, imageSelectionMenu.getImageName());
			state = State.Game;
			break;
		case Game:
			game.mouseClicked();
			break;
		default:
			break;
		}
	}

}
