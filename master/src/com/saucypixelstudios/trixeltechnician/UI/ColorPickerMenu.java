package com.saucypixelstudios.trixeltechnician.UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.saucypixelstudios.trixeltechnician.trixels.TrixelHandler;

public class ColorPickerMenu {
	private ArrayList<Color> colors;
	private ArrayList<Point> squarePoints;
	private int squareSize = 16;
	private int menuWidth;
	private int menuHeight;
	private int x;
	private int y;
	private Color menuColor = Color.decode("#747474");

	public ColorPickerMenu(int windowWidth, int windowHeight) throws NumberFormatException, IOException{
		colors = new ArrayList<Color>();
		addColors();
		loadColorPalette("default");
		findBorderDims();
		findCoords(windowWidth, windowHeight);
		System.out.println(colors.size() + " " + x + " " + y + " " + menuWidth + " " + menuHeight);
	}

	public Color getColorOnClick(int mX, int mY){
		for(int c = 0; c < colors.size(); c++){
			int sX = squareSize * ((c % 8)+1) + x;
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

	public void loadColorPalette(String fileName) throws NumberFormatException, IOException{
		boolean fileExists = true;
		FileReader fr = null;
		try {
			fr = new FileReader("palettes/" + fileName + ".txt");
		} catch (FileNotFoundException e) {
			System.out.println("Could not locate palette at \"" + "/palettes/" + fileName + ".txt" + "\"");
			fileExists = false;
		}
		if(fileExists){
			BufferedReader br = new BufferedReader(fr);
			colors.clear();
			
			String color;
			while((color = br.readLine()) != null){
				System.out.println(color);
				addColorToPalette(Color.decode(color));
			}
			br.close();
		}
	}

	public void drawMenu(Graphics g){
		g.setColor(menuColor);
		g.fillRect(x, y, menuWidth, menuHeight);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, menuWidth, menuHeight);
		int primary = -1;
		int secondary = -1;
		for(int c = 0; c < colors.size(); c++){
			g.setColor(colors.get(c));
			g.fillRect(squareSize * ((c % 8)+1) + x, (squareSize * ((int) (c / 8) + 1)) + y, squareSize, squareSize);

			if(colors.get(c).equals(TrixelHandler.primaryColor)){
				primary = c;
			}
			else if(colors.get(c).equals(TrixelHandler.secondaryColor)){
				secondary = c;
			}
		}

		if(secondary != -1){
			g.setColor(Color.WHITE);
			g.drawRect(squareSize * ((secondary % 8)+1) + x, (squareSize * ((int) (secondary / 8) + 1)) + y, squareSize, squareSize);
		}

		if(primary != -1){
			g.setColor(Color.BLACK);
			g.drawRect(squareSize * ((primary % 8)+1) + x, (squareSize * ((int) (primary / 8) + 1)) + y, squareSize, squareSize);
		}
	}

	public void addColorToPalette(Color color){
		colors.add(color);
		findBorderDims();
	}

	public void addColors(){
		colors.add(Color.decode("#FFFFFF"));
		colors.add(Color.decode("#BA4A4A"));
		colors.add(Color.decode("#801515"));
		colors.add(Color.decode("#550000"));

		colors.add(Color.decode("#5DCC8B"));
		colors.add(Color.decode("#2ECC71"));
		colors.add(Color.decode("#27AE60"));

		colors.add(Color.decode("#5DA7D8"));
		colors.add(Color.decode("#3498DB"));
		colors.add(Color.decode("#2980B9"));

		colors.add(Color.decode("#FFDA47"));
		colors.add(Color.decode("#FFCD02"));
		colors.add(Color.decode("#FFA800"));
	}

	public void findBorderDims(){
		if(colors.size() < 8){
			menuWidth = (colors.size() + 2) * squareSize;
			menuHeight = 3 * squareSize;
		}
		else{
			System.out.println("Here");
			menuWidth = 10 * squareSize;
			menuHeight = ((int) (colors.size() / 8 + 3)) * squareSize;
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
