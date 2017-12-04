package main;

import static helpers.Helper.randInt;

import java.util.ArrayList;

import controller.StateManager;
import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;

public class Main extends PApplet {
	
	private StateManager stateManager;
	
	private int time, wait;
	private boolean pauseTime, first;
	private Minim minim;
	private ArrayList<AudioPlayer> songs;
	private AudioPlayer currentSong;
	
	public static void main(String[] args) {
		PApplet.main("main.Main");
	}
	
	public void setup() {
		stateManager = new StateManager(this);
		this.time = millis();
		this.wait = 30000;
		this.pauseTime = false;
		this.first = true;
		this.minim = new Minim(this);
		this.songs = new ArrayList<AudioPlayer>();
		songs.add(minim.loadFile("../res/songs/Memories.mp3"));
	}
	
	public void settings() {
		fullScreen();
	}
	
	public void draw() {
		stateManager.draw();
		
		if (!pauseTime && millis() - time >= wait) {
			pauseTime = true;
			currentSong = getRandomSong();
			currentSong.play();
			first = false;
		}
		
		if (!first && !currentSong.isPlaying()) {
			System.out.println("updated");
			pauseTime = false;
			first = true;
			time = millis();
		}
	}
	
	public void mouseClicked() {
		stateManager.mouseClicked();
	}
	
	private AudioPlayer getRandomSong() {
		return songs.get(randInt(0, songs.size()));
	}
}
