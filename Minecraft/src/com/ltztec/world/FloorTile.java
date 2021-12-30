package com.ltztec.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class FloorTile extends Tile{

	public FloorTile(int x, int y, BufferedImage sprite) {
		super(x, y, sprite);
	}

	public void render(Graphics g) {
		if(World.cycle == World.day) {
			g.drawImage(Tile.TILE_CEU, x - Camera.x, y - Camera.y, null);
		}if(World.cycle == World.night) {
			g.drawImage(Tile.TILE_CEU_NOITE, x - Camera.x, y - Camera.y, null);
		}
	}
	
}
