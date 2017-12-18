package main;

import controller.StateManager;
import processing.core.PApplet;

public class Main extends PApplet {
	
	private StateManager stateManager;
	
	public static void main(String[] args) {
		PApplet.main("main.Main");
	}
	
	public void setup() {
		stateManager = new StateManager(this);
	}
	
	public void settings() {
		fullScreen();
	}
	
	public void draw() {
		stateManager.draw();
	}
	
	public void mouseClicked() {
		stateManager.mouseClicked();
	}
	
	public void mousePressed() {
		stateManager.mousePressed();
	}
	
	public void keyPressed() {
		stateManager.keyPressed();
	}
}
