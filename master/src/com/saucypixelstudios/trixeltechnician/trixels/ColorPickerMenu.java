package com.saucypixelstudios.trixeltechnician.trixels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class ColorPickerMenu {
	private ArrayList<Color> colors;
	private ArrayList<Point> squarePoints;
	private int squareSize = 16;
	private int menuWidth;
	private int menuHeight;
	private int x;
	private int y;
	private Color menuColor = Color.decode("#747474");

	public ColorPickerMenu(int windowWidth, int windowHeight){
		colors = new ArrayList<Color>();
		addColors();
		findBorderDims();
		findCoords(windowWidth, windowHeight);
		System.out.println(colors.size() + " " + x + " " + y + " " + menuWidth + " " + menuHeight);
	}
	
	public Color getColorOnClick(int mX, int mY){
		for(int c = 0; c < colors.size(); c++){
			int sX = squareSize * (c + 1) + x;
			int sY = (squareSize * ((int) (c / 8) + 1)) + y;
			for(int sW = 0; sW < squareSize; sW++){
				for(int sH = 0; sH < squareSize; sH++){
					if(mX == sX + sW && mY == sY + sH)
						return colors.get(c);
				}
			}
		}
		return null;
	}
	
	public void drawMenu(Graphics g){
		g.setColor(menuColor);
		g.fillRect(x, y, menuWidth, menuHeight);
		for(int c = 0; c < colors.size(); c++){
			g.setColor(colors.get(c));
			g.fillRect(squareSize * (c + 1) + x, (squareSize * ((int) (c / 8) + 1)) + y, squareSize, squareSize);
		}
	}
	
	public void addColors(){
		colors.add(Color.decode("#FFFFFF"));
		colors.add(Color.decode("#BA4A4A"));
		colors.add(Color.decode("#801515"));
		colors.add(Color.decode("#550000"));
	}
	
	public void findBorderDims(){
		if(colors.size() < 8){
			menuWidth = (colors.size() + 2) * squareSize;
			menuHeight = 3 * squareSize;
		}
		else{
			menuWidth = 10 * squareSize;
			menuHeight = ((int) (colors.size() / 8 + 2)) * squareSize;
		}	
	}
	
	public void findCoords(int windowWidth, int windowHeight){
		x = windowWidth / 2 - menuWidth / 2;
		y = windowHeight / 2 - menuHeight / 2;
	}

	public int getMenuWidth() {
		return menuWidth;
	}

	public void setMenuWidth(int menuWidth) {
		this.menuWidth = menuWidth;
	}

	public int getMenuHeight() {
		return menuHeight;
	}

	public void setMenuHeight(int menuHeight) {
		this.menuHeight = menuHeight;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Color getMenuColor() {
		return menuColor;
	}

	public void setMenuColor(Color menuColor) {
		this.menuColor = menuColor;
	}
}