package net.aitest.andrew.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import net.aitest.andrew.Engine;

public class Edges {
	
	private Rectangle l, r, t, b;
	
	public Edges(){
		l = new Rectangle(0, 10, 10, Engine.HEIGHT - 20);
		r = new Rectangle(Engine.WIDTH - 10, 10, 10, Engine.HEIGHT - 20);
		t = new Rectangle(0, 0, Engine.WIDTH, 10);
		b = new Rectangle(0, Engine.HEIGHT - 10, Engine.WIDTH, 10);
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics g){
		g.setColor(Color.gray);
		g.fill(l);
		g.fill(r);
		g.fill(t);
		g.fill(b);
	}
	
	public Rectangle getEdge(int side){
		if (side == 0) {
			return t;
		} else if (side == 1) {
			return r;
		} else if (side == 2) {
			return b;
		} else if (side == 3) {
			return l;
		} else {
			return null;
		}
	}
}
