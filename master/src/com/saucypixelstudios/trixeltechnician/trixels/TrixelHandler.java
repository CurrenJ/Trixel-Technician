package com.saucypixelstudios.trixeltechnician.trixels;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JOptionPane;

public class TrixelHandler {
	public static Trixel[][] trixels;
	public static Trixel curTrixel;
	public static Color primaryColor;
	public static Color secondaryColor;
	public static final int PRIMARY = 0;
	public static final int SECONDARY = 1;

	public TrixelHandler(){
		TrixelBaseSideLength.init();
//		int horizontal = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / TrixelBaseSideLength.getMedian());
//		int vertical = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / TrixelBaseSideLength.LENGTH * 2);
//		trixels = new Trixel[horizontal][vertical];
		init();
		curTrixel = null;
		primaryColor = Color.decode("#801515");
		secondaryColor = Color.decode("#550000");
		for(int tW = 0; tW < trixels.length; tW++){
			for(int tH = 0; tH < trixels[0].length; tH++){
				int direction = TrixelDirectionMap.LEFT;
				if(tW % 2 == 0){
					if(tH % 2 == 0)
						direction = TrixelDirectionMap.RIGHT;
				}
				else if(tW % 2 == 1){
					if(tH % 2 == 1)
						direction = TrixelDirectionMap.RIGHT;
				}
				Trixel trixel = new Trixel(1, direction, (int) (tW * TrixelBaseSideLength.getMedian()), tH * TrixelBaseSideLength.LENGTH / 2);
				trixel.setColor("ffffff");
				trixels[tW][tH] = trixel;
			}
		}
		System.out.println("Trixel grid size: " + trixels.length + ", " + trixels[0].length);
	}

	public void setTrixel(int indexX, int indexY, Trixel trixel){
		trixels[indexX][indexY] = trixel;
	}

	public void fillCurTrixel(int colorChoice) {
		if(colorChoice == PRIMARY)
			curTrixel.setColor(primaryColor);
		else if(colorChoice == SECONDARY)
			curTrixel.setColor(secondaryColor);
	}
	
	public static void init(){
		trixels  = new Trixel[(Integer.parseInt(JOptionPane.showInputDialog("X Axis Length (in trixels): ")))][(Integer.parseInt(JOptionPane.showInputDialog("Y Axis Length (in trixels): ")))];
	}
}
