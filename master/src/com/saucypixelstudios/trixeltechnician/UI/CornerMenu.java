package com.saucypixelstudios.trixeltechnician.UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import com.saucypixelstudios.trixeltechnician.UI.MenuElement;;

public class CornerMenu {
	
	private ArrayList<MenuElement> elements;
	private ArrayList<Rectangle> bounds;
	private Rectangle menuBounds;
	private int brX; //bottom right x
	private int brY; //bottom right y
	private int x;
	private int y;
	private int width;
	private int height;
	private Graphics g;
	private Color primaryColor;
	
	public CornerMenu(int x, int y) { //position of bottom right corner of menu
		elements = new ArrayList<MenuElement>();
		bounds = new ArrayList<Rectangle>();
		menuBounds = new Rectangle();
		brX = x;
		brY = y;
		this.x = brX;
		this.y = brY;
	}
	
	public void calcSize() {
		width = 0;
		height = 0;
		for(MenuElement e : elements) {			
			width = (e.getWidth() < width) ? width : e.getWidth(); //sets width to the largest element width over the course of loop
			height += e.getHeight();
		}
		x = brX - width;
		y = brY - height;
		
		int tempHeight = 0;
		for(MenuElement e : elements ) {
			bounds.set(elements.indexOf(e), new Rectangle(x, y + tempHeight, width, e.getHeight()));
			e.calcBounds(x, y + tempHeight, width, height);

			tempHeight += e.getHeight();
		}
		
		System.out.println("Menu size recalced");
		//System.out.println("brX: " + brX + " | x: " + x);
		//System.out.println("brY: " + brY + " | y: " + y);
		menuBounds.setBounds(x, y, width, height);
	}
	
	public void addCheckbox(boolean checked, String text) {
		elements.add(new Checkbox(checked, text));
		bounds.add(new Rectangle());
	}
	
	public void addColorPalette() {
		elements.add(new ColorPalette());
		bounds.add(new Rectangle());
	}
	
	public Color getPrimaryColor() {
		return primaryColor;
	}
	
	public void loadPalette(String paletteName) {
		for(MenuElement e : elements) {
			if(e instanceof ColorPalette) {
				((ColorPalette)e).loadColorPalette(paletteName);
				e.calcBounds(x, y, width, height);
				e.recalcNeeded();
			}
		}
	}
	
	public void drawMenu(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(x, y, width, height);
		g.setColor(Color.BLACK);
		g.drawRect(x, y, width, height);
		
		int elemY = 0;
		for(MenuElement e : elements) {
			e.drawElement(g, x, y + elemY, width, height);
			if(e.recalcNeeded())
				calcSize();
			
			elemY += e.getHeight();
			
			//debug for elements bounds
//			g.setColor(Color.RED);
//			Rectangle area = bounds.get(elements.indexOf(e)).getBounds();
//			g.drawRect(area.x, area.y, area.width, area.height);
		}
	}
	
	public boolean inMenuBounds(int x, int y) {
		return (x >= menuBounds.getBounds().x && x <= menuBounds.getBounds().x + menuBounds.getBounds().width && y >= menuBounds.getBounds().y && y <= menuBounds.getBounds().y + menuBounds.getBounds().height);
	}
	
	public void mouseClicked(int mX, int mY) {
		if(inMenuBounds(mX, mY)) {
			for(MenuElement e : elements) {
				Rectangle area = bounds.get(elements.indexOf(e)).getBounds();
				if(mX >= area.x && mX <= area.x + area.width && mY >= area.y && mY <= area.y + area.height) {
					e.mouseClicked(mX, mY, area.x, area.y);
					
					if(e instanceof ColorPalette) {
						primaryColor = ((ColorPalette)e).getPrimaryColor();
					}
				}
			}
		}
	}
}