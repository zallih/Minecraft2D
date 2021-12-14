package com.ltztec.main;

import java.awt.Color;
import java.awt.Graphics;

import com.ltztec.world.Camera;
import com.ltztec.world.FloorTile;
import com.ltztec.world.Tile;
import com.ltztec.world.WallTile;
import com.ltztec.world.World;

public class Inventory {

	public String[] items = {"grama", "terra", "areia", "pedra", "", "", "", "", "", "delete"};	
	public int boxSize = 40;
	public int initialPosition = ((Game.WIDTH*Game.SCALE) / 2) - ((items.length*boxSize) / 2);
	
	public int selected = 0;
	public boolean isPressed = false;
	public boolean isPlaceItem = false;
	public int mx, my;
	
	public void tick() {
		
		if(isPressed == true) {
			isPressed = false;
			
			if(mx >= initialPosition && mx < initialPosition + (boxSize*items.length)) {
				if(my >= Game.HEIGHT*Game.SCALE - boxSize + 3 && my < Game.HEIGHT*Game.SCALE - boxSize + 3 + boxSize) {
					selected = (int) (mx - this.initialPosition)/ boxSize;
					
				}
			}
		}
		
		if(isPlaceItem == true) {
			isPlaceItem = false;
			
			mx = (int) mx/3 + Camera.x;
			my = (int) my/3 + Camera.y;
			
			int tilex = mx/16;
			int tiley = my/16;
			
			if(World.tiles[tilex+tiley*World.WIDTH].solid == false) {
				
				if(items[selected] == "grama") {
					World.tiles[tilex+tiley*World.WIDTH] = new WallTile(tilex*16, tiley*16, Tile.TILE_GRAMA);
	
				}else if(items[selected] == "terra") {
					World.tiles[tilex+tiley*World.WIDTH] = new WallTile(tilex*16, tiley*16, Tile.TILE_TERRA);
	
				}else if(items[selected] == "areia") {
					World.tiles[tilex+tiley*World.WIDTH] = new WallTile(tilex*16, tiley*16, Tile.TILE_AREIA);
	
				}else if(items[selected] == "pedra") {
					World.tiles[tilex+tiley*World.WIDTH] = new WallTile(tilex*16, tiley*16, Tile.TILE_PEDRA);
	
				}else if(items[selected] == "delete") {
					World.tiles[tilex+tiley*World.WIDTH] = new FloorTile(tilex*16, tiley*16, Tile.DELETE);
	
				}
				
				if(World.isFree(Game.player.getX(), Game.player.getY()) == false) {
					World.tiles[tilex+tiley*World.WIDTH] = new FloorTile(tilex*16, tiley*16, Tile.DELETE);
				}
				
			}
			
		}
		
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
			}else if(items[i] == "delete") {
				g.drawImage(Tile.TILE_DELETE, initialPosition + (i*boxSize) + 5, Game.HEIGHT*Game.SCALE - boxSize + 3, 32,32, null);
			}
			
			if(selected == i) {
				g.setColor(Color.blue);
				g.drawRect(initialPosition + (i*boxSize)-1, Game.HEIGHT*Game.SCALE - boxSize-3, boxSize, boxSize);
			}
			
		}
		
	}
	
	
}
