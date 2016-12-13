package net.aitest.andrew;

import java.awt.Font;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import net.aitest.andrew.entities.Brick;
import net.aitest.andrew.entities.Edges;
import net.aitest.andrew.entities.Paddle;
import net.aitest.andrew.entities.Puck;

public class Game extends BasicGameState {

	public static final int BRICK_WIDTH = 50, BRICK_HEIGHT = 20;
	public static final int BRICK_COLS = 12;
	public static final int BRICK_ROWS = 10;
	public static final int PADDLE_WIDTH = 40;

	public Paddle paddle;
	public Puck puck;
	public Edges edges;
	// public Bricks bricks;
	public Input input;
	private Font font;
	private TrueTypeFont ttf, pointz;
	private ArrayList<Brick> bricks = new ArrayList<Brick>();

	public Rectangle r, l, b, t, background;
	public Rectangle[] array;

	public static boolean gameOver = false;
	public static boolean running = false;

	private String string;
	private String string1;
	public float x, y;
	private int bWidth = 50, bHeight = 20;
	private int sWidth;
	private int xSpace = 0;
	private int ySpace = 50;
	public int points = 0;
	private int passedTime;
	private int paddlePoints = 0, speedPoints = 0;
	private int bonus = 0;
	private int modifier = 0;

	// private int brickAmount = 12;
	// private int rows = 5;

	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		string = "Press 'Space' to begin.";
		string1 = "Points: ";
		font = new Font("Verdana", Font.BOLD, 22);
		ttf = new TrueTypeFont(font, true);
		pointz = new TrueTypeFont(font, true);
		sWidth = ttf.getWidth(string);
		background = new Rectangle(0, 0, Engine.WIDTH, Engine.HEIGHT);

		paddle = new Paddle(PADDLE_WIDTH);
		puck = new Puck(500, 200, 20, 20);
		edges = new Edges();
		for (int i = 0; i < bricks.size(); i++) {
			bricks.get(i).remove();
			if (bricks.get(i).isRemoved()) bricks.removeAll(bricks);
			xSpace = 0;
			ySpace = 50;
		}
		initBricks(BRICK_COLS, BRICK_ROWS);
		x = puck.getX();
		y = puck.getY();
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		edges.render(g);
		g.setColor(Color.white);
		paddle.render(g);
		puck.render(g);
		for (Brick b : bricks) {
			b.render(g);
		}
		if (!running) {
			ttf.drawString((Engine.WIDTH / 2) - (sWidth / 2), Engine.HEIGHT / 2 + 50, string);
		}
		if (running) {
			pointz.drawString(300, 10, string1 + " " + points);
		}
	}

	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		for (Brick b : bricks) {
			b.tick();
		}
		input = gc.getInput();
		paddle.tick(gc, delta);
		if (!running) {
			puck.setX(paddle.getX() + (PADDLE_WIDTH / 2));
			puck.setY(paddle.getY() - 30);
		}
		if (input.isKeyDown(Input.KEY_SPACE)) {
			setRunningTrue();
			Puck.moving = true;
		}
		puck.tick(delta);
		if (puck.getSide(1).intersects(edges.getEdge(1))) {
			puck.setDirX(-1);
		}
		if (puck.getSide(2).intersects(edges.getEdge(2))) {
			GameOver.points = points;
			gameOver = true;
			puck.reset();
			paddle.setPaddleW(PADDLE_WIDTH);
			modifier = 0;
			bonus = 0;
			for (int i = 0; i < bricks.size(); i++) {
				bricks.get(i).remove();
				if (bricks.get(i).isRemoved()) bricks.removeAll(bricks);
			}
			Puck.moving = false;
			xSpace = 0;
			ySpace = 50;
			initBricks(BRICK_COLS, BRICK_ROWS);
			sbg.enterState(2);
		}
		if (puck.getSide(0).intersects(edges.getEdge(0))) {
			puck.setDirY(1);
		}
		if (puck.getSide(3).intersects(edges.getEdge(3))) {
			puck.setDirX(1);
		}

		if (puck.getSide(2).intersects(paddle.getPaddle())) {
			puck.setDirY(-1);
		}

		for (Brick b : bricks) {
			Rectangle r = b.getRect();
			if (puck.getSide(0).intersects(r)) {
				puck.setDirY(1);
				b.remove();
				points++;
				speedPoints++;
				paddlePoints++;
			}
			if (puck.getSide(1).intersects(r)) {
				puck.setDirX(-1);
				b.remove();
				points++;
				speedPoints++;
				paddlePoints++;
			}
			if (puck.getSide(2).intersects(r)) {
				puck.setDirY(-1);
				b.remove();
				points++;
				speedPoints++;
				paddlePoints++;
			}
			if (puck.getSide(3).intersects(r)) {
				puck.setDirX(1);
				b.remove();
				points++;
				speedPoints++;
				paddlePoints++;
			}
			if (puck.getSide(3).intersects(paddle.getBound(1))) {
				puck.setDirX(1);
			}
			if (puck.getSide(1).intersects(paddle.getBound(0))) {
				puck.setDirX(-1);
			}
		}

		for (int i = 0; i < bricks.size(); i++) {
			if (bricks.get(i).isRemoved()) bricks.remove(i);
		}

		if (points == BRICK_COLS * BRICK_ROWS) {
			sbg.enterState(2);
		}
		passedTime += delta;
		if (passedTime % 500 == 0) {
			if (paddlePoints % (3 + modifier) == 0 && paddlePoints > 0) {
				modifier += 2;
				bonus += 20;
				addPaddle(bonus);
				System.out.println("Adding Paddle Width!");
				paddlePoints = 0;
			}
			if (speedPoints % 15 == 0 && speedPoints > 0) {
				addSpeed();
				System.out.println("Adding Puck Speed!");
				speedPoints = 0;
			}
		}

	}

	public int getID() {
		return 1;
	}

	public static void setRunningTrue() {
		running = true;
	}

	public static void setRunningFalse() {
		running = false;
	}

	public int getPoints() {
		return points;
	}

	public void initBricks(int cols, int rows) {
		points = 0;
		speedPoints = 0;
		paddlePoints = 0;
		for (int i = 0; i < rows; i++) {
			xSpace = (Engine.WIDTH / 2) - ((BRICK_WIDTH + 5) * cols) / 2;
			for (int a = 0; a < cols; a++) {
				Brick brick = new Brick(BRICK_WIDTH, BRICK_HEIGHT);
				brick.setX(xSpace);
				brick.setY(ySpace);
				bricks.add(brick.getBrick());
				xSpace += bWidth + 5;
			}
			ySpace += bHeight + 5;
		}
	}

	public void addSpeed() {
		puck.addXspeed(0.1f);
		puck.addYspeed(0.1f);
	}

	public void addPaddle(int bonus) {
		paddle.setPaddleW(PADDLE_WIDTH + bonus);
	}

}
