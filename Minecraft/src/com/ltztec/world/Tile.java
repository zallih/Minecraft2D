package com.ltztec.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.ltztec.main.Game;


public class Tile {
	
	public static BufferedImage TILE_CEU = Game.spritesheet.getSprite(0,0,16,16);
	public static BufferedImage TILE_GRAMA = Game.spritesheet.getSprite(16,0,16,16);
	public static BufferedImage TILE_TERRA = Game.spritesheet.getSprite(16,16,16,16);
	public static BufferedImage TILE_PEDRA = Game.spritesheet.getSprite(0,16,16,16);
	public static BufferedImage TILE_AREIA = Game.spritesheet.getSprite(0,32,16,16);
	

	private BufferedImage sprite;
	private int x,y;
	
	public Tile(int x,int y,BufferedImage sprite){
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	public void render(Graphics g){
		g.drawImage(sprite, x - Camera.x, y - Camera.y, null);
	}

}
