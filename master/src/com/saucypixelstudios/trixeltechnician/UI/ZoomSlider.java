package com.saucypixelstudios.trixeltechnician.UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import com.saucypixelstudios.trixeltechnician.camera.Camera;

public class ZoomSlider {
	private double maxZoom;
	private double zoom;
	private double sliderLevel;
	private Color backgroundColor = Color.decode("#747474");
	private Color indicatorColor = Color.decode("#92AFD7");
	private Color indicatorColorOutline = Color.decode("#27364C");
	private Rectangle hitbox;

	public ZoomSlider(double maxZoom, Camera camera, int screenWidth){
		hitbox = new Rectangle(screenWidth - 44, 20, 8, 120);
		this.maxZoom = maxZoom;
		zoom = camera.getZoom();
		calcSliderLevel();
	}

	public void drawZoomSlider(Graphics g, int screenWidth){
		g.setColor(backgroundColor);
		g.fillRect(screenWidth - 56, 16, 32, 128);
		g.setColor(Color.BLACK);
		g.drawRect(screenWidth - 56, 16, 32, 128);

		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(screenWidth - 44, 20, 8, 120);
		g.setColor(Color.BLACK);
		g.drawRect(screenWidth - 44, 20, 8, 120);

		drawZoomIndicator(g, screenWidth - 44 + 4, (int) sliderLevel);
	}

	public void drawZoomIndicator(Graphics g, int x, int y){
		int width = 16;
		int height = 4;

		g.setColor(indicatorColor);
		g.fillRect(x - width / 2, y - height / 2, width, height);		
		g.setColor(indicatorColorOutline);
		g.drawRect(x - width / 2, y - height / 2, width, height);
	}

	public double getZoom(){
		return sliderLevel;
	}

	public boolean containedInHitbox(int x, int y){
		if(hitbox.contains(x, y))
			return true;
		return false;
	}

	public double mouseInteraction(int x, int y){
		double tempZoom = (hitbox.getHeight() * maxZoom - maxZoom * y + maxZoom * hitbox.getY()) / hitbox.getHeight();
		if(tempZoom > maxZoom)
			zoom = maxZoom;
		else if(tempZoom < 0)
			zoom = 0;
		else zoom = tempZoom;
		calcSliderLevel();
		return zoom;
	}

	public void calcSliderLevel(){
		sliderLevel = (hitbox.getHeight() + hitbox.getY()) - (hitbox.getHeight() / maxZoom * zoom);
	}
}
