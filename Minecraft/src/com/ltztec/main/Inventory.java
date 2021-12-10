package com.ltztec.main;

import java.awt.Color;
import java.awt.Graphics;

import com.ltztec.world.Tile;

public class Inventory {

	public String[] items = {"grama", "terra", "areia", "pedra", "", "", "", "", ""};	
	public int boxSize = 40;
	public int initialPosition = ((Game.WIDTH*Game.SCALE) / 2) - ((items.length*boxSize) / 2);
	
	
	
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < items.length;i++) {
			g.setColor(new Color(0xff708090));
			g.fillRect(initialPosition + (i*boxSize), Game.HEIGHT*Game.SCALE - boxSize-2, boxSize, boxSize);
			g.setColor(Color.black);
			g.drawRect(initialPosition + (i*boxSize), Game.HEIGHT*Game.SCALE - boxSize-2, boxSize, boxSize);
		
			
			if(items[i] == "grama") {
				g.drawImage(Tile.TILE_GRAMA, initialPosition + (i*boxSize) + 5, Game.HEIGHT*Game.SCALE - boxSize + 3, 32,32, null);
			}else if(items[i] == "terra") {
				g.drawImage(Tile.TILE_TERRA, initialPosition + (i*boxSize) + 5, Game.HEIGHT*Game.SCALE - boxSize + 3, 32,32, null);
			}else if(items[i] == "areia") {
				g.drawImage(Tile.TILE_AREIA, initialPosition + (i*boxSize) + 5, Game.HEIGHT*Game.SCALE - boxSize + 3, 32,32, null);
			}else if(items[i] == "pedra") {
				g.drawImage(Tile.TILE_PEDRA, initialPosition + (i*boxSize) + 5, Game.HEIGHT*Game.SCALE - boxSize + 3, 32,32, null);
			}
			
		}
		
	}
	
	
}
