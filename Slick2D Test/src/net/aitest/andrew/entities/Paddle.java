package net.aitest.andrew.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;

import net.aitest.andrew.Engine;

public class Paddle {

	public Input input;

	public float x, y;
	public float xPos, yPos;
	private int width;
	private int height = 20;
	private float speed = 0.8f;
	private Rectangle rect;
	private Rectangle l, r;
	private Puck puck;

	
	public Paddle(int width) {
		this.width = width;
		setRectangle();
		setBounds();
	}

	public void setRectangle() {
		x = (Engine.WIDTH / 2) - (width / 2);
		y = Engine.HEIGHT - height * 2;
		rect = new Rectangle(x, y, width, height);
	}
	
	public void setBounds(){
		l = new Rectangle(x - 1, y, 2, height);
		r = new Rectangle((x + width) - 1, y, 2, height);
	}

	public void tick(GameContainer gc, int delta) {
		input = gc.getInput();
			if (x > 10) {
				if (input.isKeyDown(Input.KEY_LEFT)) {
					x -= delta * speed;
				}
			}
			if (((x + 10) + width) < Engine.WIDTH) {
				if (input.isKeyDown(Input.KEY_RIGHT)) {
					x += delta * speed;
				}
			}
			rect.setWidth(width);
			rect.setLocation(x, y);
			l.setLocation(x - 1, y);
			r.setLocation((x + width) - 1, y);
	}

	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fill(rect);
		g.setColor(Color.red);
		g.fill(l);
		g.fill(r);
	}

	public Rectangle getPaddle() {
		return rect;
	}
	
	public Rectangle getBound(int bound){
		if(bound == 0){
			return l;
		}
		if(bound == 1){
			return r;
		}
		else return null;
	}
	
	public float getX(){
		return this.x;
	}
	
	public float getY(){
		return this.y;
	}
	
	public void setPaddleW(int width){
		this.width = width;
	}
}
