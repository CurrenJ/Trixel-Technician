package com.saucypixelstudios.trixeltechnician.trixels;

import javax.swing.JOptionPane;

public class TrixelBaseSideLength {
	public static int LENGTH;
	
	public static int getMedian(){
		return (int) Math.sqrt((LENGTH*LENGTH) - ((LENGTH * 0.5)*(LENGTH * 0.5)));
	}
	
	public static void init(){
		LENGTH = (Integer.parseInt(JOptionPane.showInputDialog("Enter a trixel size number:")) + 6) * 2;
	}
	
	public static void init(int size) {
		LENGTH = size;
	}
}
