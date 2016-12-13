package net.aitest.andrew;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Engine extends StateBasedGame {

	// Application Properties
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static final int FPS = 60;
	public static final String VERSION = "v0.3";

	// Game States
	public static final int MENU = 0;
	public static final int GAME = 1;
	public static final int GAMEOVER = 2;

	public Engine(String name) {
		super(name);

		// Add States;
		this.addState(new Menu());
		this.addState(new Game());
		this.addState(new GameOver());
		//Enter Menu On Launch
		this.enterState(MENU);
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		this.getState(MENU).init(gc, this);
		this.getState(GAME).init(gc, this);
		this.getState(GAMEOVER).init(gc, this);
	}

	public static void main(String[] args) {
		try {
			AppGameContainer app = new AppGameContainer(new Engine("Fooling Around" + VERSION));
			// app.setMouseGrabbed(true);
			app.setDisplayMode(WIDTH, HEIGHT, false);
			// app.setVSync(true);
			app.setShowFPS(true);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}
