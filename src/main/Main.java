package main;

import static helpers.Helper.randInt;

import java.util.ArrayList;

import controller.StateManager;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;

public class Main extends PApplet {
	
	private StateManager stateManager;
	
	private Minim minim;
	private ArrayList<AudioPlayer> songs;
	private AudioPlayer currentSong;
	
	public static void main(String[] args) {
		PApplet.main("main.Main");
	}
	
	public void setup() {
		stateManager = new StateManager(this);
		this.minim = new Minim(this);
		this.songs = new ArrayList<AudioPlayer>();
		songs.add(minim.loadFile("res/songs/Memories.mp3"));
		currentSong = getRandomSong();
		currentSong.loop();
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
	
	private AudioPlayer getRandomSong() {
		return songs.get(randInt(0, songs.size()));
	}
}
