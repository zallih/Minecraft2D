package com.ltztec.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.ltztec.main.Game;
import com.ltztec.main.Sound;
import com.ltztec.world.Camera;
import com.ltztec.world.FloorTile;
import com.ltztec.world.Tile;
import com.ltztec.world.WallTile;
import com.ltztec.world.World;


public class Enemy extends Entity {

	private double speed = 0.8;

	public boolean right = true, left = false;
	public int right_dir = 1, left_dir = -1;
	public int dir = right_dir;

	private int maskx = 2, masky = 2, maskw = 16, maskh = 16;
	
	public static int frames = 0, maxFrames = 10, index = 0, maxIndex = 3;
	public static BufferedImage[] enemyLeft;
	public static BufferedImage[] enemyRight;
	

	private double gravity = 1.2;

	public double life = 50;
	public double maxLife = 50;
	
	
	public boolean isDamage = false;
	private int damageFrames = 0;



	public Enemy(double x, double y, int width, int height, double speed ) {
		super(x, y, width, height, speed, null);

		enemyLeft = new BufferedImage[4];
		enemyRight = new BufferedImage[4];
		
		
		for (int i = 0; i < 4; i++) {
			enemyLeft[i] = Game.spritesheet.getSprite(80 + (i * 16), 80, 16, 16);
		}
		
		for (int i = 0; i < 4; i++) {
			enemyRight[i] = Game.spritesheet.getSprite(80 + (i * 16), 64, 16, 16);
		}
		
	}

	public void tick() {
		depth = 1;
		
		frames++;
		if (frames == maxFrames) {
			frames = 0;
			index++;
			//System.out.println(index);
			if (index > maxIndex) {
				index = 0;
			}
		}

		if (World.isFree((int) x, (int) (y + gravity))) {
			y += gravity;
		} 
		
		
		if (life <= 0) {
			this.destroySelf();
			return;
		}
		
		if (isDamage) {
			Sound.hit.play();
			this.damageFrames++;
			if (this.damageFrames == 5) {
				this.damageFrames = 0;
				this.isDamage = false;
			}
		}
		
		if(dir == right_dir) {
			if(World.isFree( (int)(x+speed), (int)y)) {
				x+=speed;
			}else {
				
				int xnext = (int)((x+speed)/16)+1;
				int ynext = (int)(y/16);
		
				if(World.tiles[xnext+ynext*World.WIDTH] instanceof WallTile && World.tiles[xnext+ynext*World.WIDTH].solid == false) {
					World.tiles[xnext+ynext*World.WIDTH] = new FloorTile((xnext)*16, ynext*16, Tile.TILE_CEU);
				}				
				dir = left_dir;
			}
		}else if(dir == left_dir) {
			if(World.isFree( (int)(x-speed), (int)y)) {
				x-=speed;
			}else {
				
				int xnext = (int)((x-speed)/16);
				int ynext = (int)(y / 16);
		
				if(World.tiles[xnext+ynext*World.WIDTH] instanceof WallTile && World.tiles[xnext+ynext*World.WIDTH].solid == false) {
					World.tiles[xnext+ynext*World.WIDTH] = new FloorTile((xnext)*16, ynext*16, Tile.TILE_CEU);
				}
				
				dir = right_dir;
			}
		}
		
	}

	
	public void destroySelf() {
		Game.enemies.remove(this);
		Game.entities.remove(this);
		Game.currentEnemy--;
	}
	
	public boolean isCollidingWithPlayer() {
		
		Rectangle enemyCurrent = new Rectangle(this.getX() + maskx, this.getY(), maskw, maskh);
		Rectangle player = new Rectangle(Game.player.getX(), Game.player.getY(), 16, 16);
		return enemyCurrent.intersects(player);
	}


	
	public void render(Graphics g) {

		if(dir == right_dir) {
			g.drawImage(enemyRight[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
		} else if(dir == left_dir) {
			g.drawImage(enemyLeft[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
		}
		
		g.setColor(Color.red);
		g.fillRect(this.getX() - Camera.x, this.getY()- Camera.y -5, 12, 3);
		
		g.setColor(Color.green);
		g.fillRect(this.getX()- Camera.x, this.getY() - Camera.y - 5, (int)((life/maxLife) * 12), 3);
	}

}
