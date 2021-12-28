package com.ltztec.main;

import com.ltztec.entities.Enemy;

public class EnemySpawn {

	public int interval = 60*15;
	public int curTime = 0;
	
	public void tick() {
	
		curTime++;
		if(curTime == interval) {
			curTime= 0;
			
			Enemy enemy = new Enemy(16, 16, 16, 16, 1);

			Game.entities.add(enemy);
			System.out.println("Inimigo adicionado");
		} 
		
	}
	
}
