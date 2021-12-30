package com.ltztec.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.ltztec.main.Game;
import com.ltztec.world.Camera;
import com.ltztec.world.World;

public class Player extends Entity {

	public boolean right, left;
	public double speed = 1.2;
	private double gravity = 1.2;

	public double life = 100, maxLife = 100;

	public static int currentCoins = 0, maxCoins = 0;

	public boolean jump = false;
	public boolean isJumping = false;
	public int jumpHeight = 40;
	public int jumpFrames = 0;
	private double vspd = 0;

	public int right_dir = 0, left_dir = 1, idle = 2;
	public int dir = right_dir;

	private int frames = 0, maxFrames = 8, index = 0, maxIndex = 3;
	private boolean moved = false;
	private BufferedImage[] rightPlayer;
	private BufferedImage[] leftPlayer;
	private BufferedImage idlePlayer;

	public BufferedImage attack_right;
	public BufferedImage attack_left;

	public boolean attack = false;
	public boolean isAttacking = false;
	public int attackFrames = 0;
	public int attackMaxFrames = 10;

	public Player(int x, int y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);

		attack_right = Game.spritesheet.getSprite(32, 0, 16, 16);
		attack_left = Game.spritesheet.getSprite(32, 16, 16, 16);

		rightPlayer = new BufferedImage[4];
		leftPlayer = new BufferedImage[4];

		idlePlayer = Game.spritesheet.getSprite(48, 32, 16, 16);

		for (int i = 0; i < 4; i++) {
			rightPlayer[i] = Game.spritesheet.getSprite(80 + (i * 16), 32, 16, 16);
		}
		for (int i = 0; i < 4; i++) {
			leftPlayer[i] = Game.spritesheet.getSprite(80 + (i * 16), 48, 16, 16);
		}
	}

	public void tick() {
		depth = 2;

		Camera.x = Camera.clamp(this.getX() - Game.WIDTH / 2, 0, World.WIDTH * 16 - Game.WIDTH);
		Camera.y = Camera.clamp(this.getY() - Game.HEIGHT / 2, 0, World.HEIGHT * 16 - Game.HEIGHT);

		if (World.isFree((int) x, (int) (y + gravity)) && isJumping == false) {
			y += gravity;

		}

		moved = false;
		if (right && World.isFree((int) (x + speed), this.getY())) {
			moved = true;
			dir = right_dir;
			x += speed;
		} else if (left && World.isFree((int) (x - speed), this.getY())) {
			moved = true;
			dir = left_dir;
			x -= speed;
		} else {
			dir = idle;
		}

		if (moved) {
			frames++;
			if (frames == maxFrames) {
				frames = 0;
				index++;
				if (index > maxIndex) {
					index = 0;
				}
			}
		}

		if (jump == true) {
			if (!World.isFree(this.getX(), this.getY() + 1)) {
				isJumping = true;
			} else {
				jump = false;
			}
		}

		if (isJumping == true) {
			if (World.isFree(this.getX(), this.getY() - 2)) {
				y -= 2;
				jumpFrames += 2;
				jumpHeight = 40;
				if (jumpFrames == jumpHeight) {
					isJumping = false;
					jump = false;
					jumpFrames = 0;
				}
			} else {
				isJumping = false;
				jump = false;
				jumpFrames = 0;
			}
		}

		if (attack == true) {
			if (isAttacking == false) {
				attack = false;
				isAttacking = true;
			}
		}

		if (isAttacking == true) {
			attackFrames++;
			if (attackFrames == attackMaxFrames) {
				isAttacking = false;
				attackFrames = 0;
			}
		}

		if (life <= 0) {
			life = 0;
			World.restartGame();
		}

		collisionEnemy();
		
	}

	public void collisionEnemy() {
		for (int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if (e instanceof Enemy) {
				if (Entity.rand.nextInt(100) < 30) {
					if (Entity.isColidding(this, e)) {
						life-=0.3;
						
						if(isAttacking == true) {
							((Enemy) e).life--;
						}
					}
					
				}
			}
		}
	}

	public void render(Graphics g) {

		if (dir == right_dir) {
			g.drawImage(rightPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);

			if (isAttacking) {
				g.drawImage(attack_right, this.getX() - Camera.x + 15, this.getY() - Camera.y, null);
			}

		} else if (dir == left_dir) {
			g.drawImage(leftPlayer[index], this.getX() - Camera.x, this.getY() - Camera.y, null);

			if (isAttacking) {
				g.drawImage(attack_left, this.getX() - Camera.x - 15, this.getY() - Camera.y, null);
			}
		} else if (dir == idle) {
			g.drawImage(idlePlayer, this.getX() - Camera.x, this.getY() - Camera.y, null);

			if (isAttacking) {
				g.drawImage(attack_right, this.getX() - Camera.x + 15, this.getY() - Camera.y, null);
			}
		}

	}

}
