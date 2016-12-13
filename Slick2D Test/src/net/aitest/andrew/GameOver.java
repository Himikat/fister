package net.aitest.andrew;

import java.awt.Font;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameOver extends BasicGameState {

	private Game game;
	private String string;
	private String string2 = "Replay? \t [R]";
	private String string3 = "Quit? \t [Q]";
	private String string4 = "Menu? \t [M]";
	private Font font, font1;
	private int width;
	private TrueTypeFont ttf, ttf1;
	private Input input;

	public static int points;

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		string = "Game Over.";
		font = new Font("Verdana", Font.BOLD, 36);
		font1 = new Font("Verdana", Font.PLAIN, 24);
		ttf = new TrueTypeFont(font, true);
		ttf1 = new TrueTypeFont(font1, true);
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		width = ttf.getWidth(string);
		ttf.drawString(((Engine.WIDTH / 2) - (width / 2)), 200, string);
		width = ttf1.getWidth(string2);
		ttf1.drawString(((Engine.WIDTH / 2) - (width / 2)), 300, string2, Color.yellow);
		width = ttf1.getWidth(string3);
		ttf1.drawString(((Engine.WIDTH / 2) - (width / 2)), 350, string3, Color.yellow);
		width = ttf1.getWidth(string4);
		ttf1.drawString(((Engine.WIDTH / 2) - (width / 2)), 400, string4, Color.yellow);
		g.setColor(Color.white);
		g.drawString("Your points: " + points, (Engine.WIDTH / 2) - 70, 550);
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		input = gc.getInput();
		if (Game.gameOver) {
			if (input.isKeyPressed(Input.KEY_R)) {
				Game.setRunningFalse();
				sbg.enterState(1);
			}

			if (input.isKeyPressed(Input.KEY_Q)) {
				gc.exit();
			}

			if (input.isKeyPressed(Input.KEY_M)) {
				sbg.enterState(0);
				Game.setRunningFalse();
			}
		}

	}

	public int getID() {
		return 2;
	}

}
