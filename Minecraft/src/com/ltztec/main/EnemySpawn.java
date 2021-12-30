package com.ltztec.main;

import com.ltztec.entities.Enemy;
import com.ltztec.entities.Entity;
import com.ltztec.world.World;

public class EnemySpawn {

	public int interval = 60*15;
	public int curTime = 0;
	
	public void tick() {
	
		curTime++;
		if(curTime == interval) {
			curTime= 0;
			
			int xInitial = Entity.rand.nextInt((World.WIDTH/2)*16 - 16 -16) + 16;
			Enemy enemy = new Enemy(xInitial, 16, 16, 16, 1);

			Game.entities.add(enemy);
			System.out.println("Inimigo adicionado");
		} 
		
	}
	
}
