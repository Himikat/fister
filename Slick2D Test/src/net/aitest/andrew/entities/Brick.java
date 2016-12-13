package net.aitest.andrew.entities;

import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;


public class Brick {
	
	private int x = 0, y = 0;
	private int col;
	private Random random = new Random();
	private Rectangle brick;
	private Rectangle edge;
	
	private boolean removed = false;
	
	public Brick(int width, int height){
		this.col = random.nextInt();
		brick = new Rectangle(x, y, width, height);
		edge = new Rectangle(x, y - 1, width + 1, height + 1);
	}
	
	public void tick(){
		brick.setLocation(x, y);
		edge.setLocation(x, y - 1);
	}
	
	public void render(Graphics g){
		g.setColor(Color.white);
		g.draw(edge);
		g.setColor(new Color(col));
		g.fill(brick);
	}
	
	public Brick getBrick(){
		return this;
	}
	
	public void setX(int x){
		this.x += x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public Rectangle getRect(){
		return brick;
	}
	
	public boolean isRemoved(){
		return this.removed;
	}
	
	public void remove(){
		this.removed = true;
	}
	
}
