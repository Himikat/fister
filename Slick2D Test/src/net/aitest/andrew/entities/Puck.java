package net.aitest.andrew.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import net.aitest.andrew.Engine;
import net.aitest.andrew.Game;

public class Puck {

	private float x, y;
	private float bX, bY;
	private float width, height;
	private Rectangle rect;
	private Rectangle right, left, top, bottom;
	private Paddle paddle;
	private float r, l, t, b;
	private int xa = 1;
	private int ya = -1;
	public static boolean moving = false;
	public float xSpeed = 0.3f;
	public float ySpeed = 0.3f;

	public Puck(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		rect = new Rectangle(this.x, this.y, this.width, this.height);
		right = new Rectangle(r, bY, 2, height - 8);
		left = new Rectangle(l, bY, 2, height - 8);
		top = new Rectangle(bX, t, width - 8, 2);
		bottom = new Rectangle(bX, b, width - 8, 2);
	}

	public void tick(int delta) {
		bX = x + 4;
		bY = y + 4;
		
		if (moving) {
			if (xa == 1) {
				x += delta * xSpeed;
			}
			if (xa == -1) {
				x -= delta * xSpeed;
			}
			if (ya == 1) {
				y += delta * ySpeed;
			}
			if (ya == -1) {
				y -= delta * ySpeed;
			}
		}
		rect.setLocation(x, y);
		updateBounds(x, y);
		right.setLocation(r, bY);
		left.setLocation(l, bY);
		top.setLocation(bX, t);
		bottom.setLocation(bX, b);
	}

	public void render(Graphics g) {
		g.setColor(Color.cyan);
		g.fill(rect);
		/*
		 * g.setColor(Color.red); g.fill(right); g.fill(left); g.fill(top); g.fill(bottom);
		 */

	}

	public void setX(float x) {
		this.x = x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public Rectangle getSide(int side) {
		if (side == 0) {
			return top;
		} else if (side == 1) {
			return right;
		} else if (side == 2) {
			return bottom;
		} else if (side == 3) {
			return left;
		} else {
			return null;
		}
	}

	public void updateBounds(float x, float y) {
		this.b = y + height - 2;
		this.t = y;
		this.l = x;
		this.r = x + width - 2;
	}

	public void setDirX(int dir) {
		this.xa = dir;
	}

	public void setDirY(int dir) {
		this.ya = dir;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void reset() {
		x = 0;
		y = 0;
		xa = 1;
		ya = -1;
		setYspeed(0.3f);
		setXspeed(0.3f);
		rect.setLocation(x, y);
	}

	public void setYspeed(float y) {
		ySpeed = y;
	}

	public void setXspeed(float x) {
		xSpeed = x;
	}
	
	public void addYspeed(float y) {
		ySpeed += y;
	}

	public void addXspeed(float x) {
		xSpeed += x;
	}

	public void setPuck(int x, int y) {
		rect.setLocation(x, y);
	}
}
