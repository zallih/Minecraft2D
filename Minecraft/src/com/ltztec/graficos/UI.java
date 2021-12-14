package com.ltztec.graficos;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.ltztec.main.Game;





public class UI {
	
	public int seconds = 0, minutes = 0,hours = 0, frames = 0;
	
	public void tick() {
		
		frames++;
		
		if(frames == 60) {
			frames = 0;
			seconds++;
			if(seconds == 60) {
				seconds = 0;
				minutes++;
				if(minutes == 60) {
					minutes = 0;
					hours++;
				}
			}
		}
		
		
	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(15, 15, 150, 25);
		g.setColor(Color.green);
		g.fillRect(15, 15, (int)((Game.player.life/Game.player.maxLife)*150), 25);
		
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.BOLD,20));
		g.drawString((int)Game.player.life+"/"+(int)Game.player.maxLife, 42, 35);
		
		
		
		String formatTime = "";
		
		if(hours < 10) {
			formatTime+="0"+hours+":";
			
		}else {
			formatTime += hours+":";
		}
		
		if(minutes < 10) {
			formatTime+="0"+minutes+":";
			
		}else {
			formatTime += minutes+":";
		}
		
		if(seconds < 10) {
			formatTime+="0"+seconds;
			
		}else {
			formatTime += seconds;
		}
		
		
		g.setFont(new Font("Arial", Font.BOLD,20));
		g.drawString(formatTime, 600, 35);
		
		
	}
	
}
