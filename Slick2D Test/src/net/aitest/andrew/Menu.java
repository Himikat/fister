package net.aitest.andrew;

import java.awt.Font;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Menu extends BasicGameState{
	
	private String string = "Press 'Enter' to start.";
	private Font font;
	private int width, height;
	private TrueTypeFont ttf;
	private Input input;
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		font = new Font("Verdana", Font.BOLD, 36);
		ttf = new TrueTypeFont(font, true);
		width = ttf.getWidth(string);
		height = ttf.getHeight();
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		ttf.drawString(((Engine.WIDTH / 2) - (width / 2)), (Engine.HEIGHT / 2) - (height / 2), string);
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		input = gc.getInput();
			if (input.isKeyDown(Input.KEY_ENTER)) {
				sbg.enterState(1);
			}
	}

	public int getID() {
		// TODO Auto-generated method stub
		return 0;
	}

}
