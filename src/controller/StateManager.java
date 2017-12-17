package controller;

import game.Game;
import menu.ImageSelectionMenu;
import menu.MainMenu;
import menu.PauseMenu;
import menu.SettingsMenu;
import menu.WinMenu;
import processing.core.PApplet;

/**
 * Decides what part of the game draws and functions
 * @author Daniel Hong
 */
public class StateManager {
	
	private PApplet canvas;
	private State state; // Current state of the game
	
	// All states of the game
	private MainMenu mainMenu;
	private ImageSelectionMenu imageSelectionMenu;
	private Game game;
	private PauseMenu pauseMenu;
	private WinMenu winMenu;
	private SettingsMenu settingsMenu;
	
	private float x, y, width; // Window dimensions
	
	/**
	 * Main StateManager constructor
	 * @param canvas
	 */
	public StateManager(PApplet canvas) {
		this.canvas = canvas;
		
		// Adapt game display dimensions to monitor dimensions
		// The display will always be square
		boolean horizontal = canvas.height < canvas.width;
		this.width = horizontal ? canvas.height : canvas.width;
		float offset = horizontal ? findOffset(canvas.width, width) :
			findOffset(canvas.height, width);
		this.x = horizontal ? offset : 0;
		this.y = horizontal ? 0 : offset;
		
		// Initialize states
		this.state = State.MainMenu; // Start the game at the main menu
		this.mainMenu = new MainMenu(canvas, x, y, width);
		this.imageSelectionMenu = new ImageSelectionMenu(canvas, x, y, width);
		this.game = new Game(canvas, x, y, width, "hot-air");
		this.pauseMenu = new PauseMenu(canvas, x, y, width);
		this.winMenu = new WinMenu(canvas,x, y, width, "hot-air");
		this.settingsMenu = new SettingsMenu(canvas, x, y, width);
	}
	
	/**
	 * Calculate how much to shift the game display horizontally/vertically
	 * @param sideLength the longer side length
	 * @param actualSize the width of the main game display
	 * @return number of pixels to shift the game display
	 */
	private float findOffset(float sideLength, float actualSize) {
		return 0.5f * (sideLength - actualSize);
	}
	
	/**
	 * Draw the current part of the game
	 */
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
		case PauseMenu:
			pauseMenu.draw();
			break;
		case WinMenu:
			winMenu.draw();
			break;
		case SettingsMenu:
			settingsMenu.draw();
			break;
		default:
			// If something fails, then we will always be at the main menu
			mainMenu.draw();
			break;
		}
	}
	
	/**
	 * Mouse click event handler
	 */
	public void mouseClicked() {
		switch (state) {
		case MainMenu:
			mainMenu.mouseClicked();
			state = mainMenu.getDestination();
			break;
		case ImageSelectionMenu:
			imageSelectionMenu.mouseClicked();
			String imageName = imageSelectionMenu.getImageName();
			if (!imageName.isEmpty()) {
				game = new Game(canvas, x, y, width, imageSelectionMenu.getImageName());
			}
			state = imageSelectionMenu.getDestination();
			break;
		case Game:
			game.mouseClicked();
			state = game.getDestination();
			winMenu = new WinMenu(canvas, x, y, width, imageSelectionMenu.getImageName());
			break;
		case WinMenu:
			winMenu.mouseClicked();
			state = winMenu.getDestination();
			break;
		case PauseMenu:
			pauseMenu.mouseClicked();
			state = pauseMenu.getDestination();
			break;
		default:
			break;
		}
	}
	
	public void mousePressed() {
		if (state == State.SettingsMenu) {
			settingsMenu.mousePressed();
		}
	}
	
	public void keyPressed() {
		if (canvas.key == 'p') {
			switch (state) {
			case Game:
				game.keyPressed();
				state = game.getDestination();
				break;
			case PauseMenu:
				pauseMenu.keyPressed();
				state = pauseMenu.getDestination();
				break;
			default:
				break;
			}
		}
	}

}
