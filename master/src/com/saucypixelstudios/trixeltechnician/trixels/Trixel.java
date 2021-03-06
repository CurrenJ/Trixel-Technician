package com.saucypixelstudios.trixeltechnician.trixels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

public class Trixel {
	private double scale;
	private int direction;
	private int x;
	private int y;
	private int[] xPoints;
	private int[] yPoints;
	private Color color;
	private Polygon shape;

	public Trixel(double scale, int direction, int x, int y){
		this.scale = scale;
		this.direction = direction;
		this.x = x;
		this.y = y;
		xPoints = new int[3];
		yPoints = new int[3];
		generatePoints();
		color = Color.decode("#FFFFFF");
		shape = new Polygon(xPoints, yPoints, 3);
	}

	public void setColor(String hexCode){
		color = Color.decode("#" + hexCode);
	}

	public void generatePoints(){
		if(direction == TrixelDirectionMap.RIGHT){
			xPoints[0] = x;
			yPoints[0] = y;

			xPoints[1] = x;
			yPoints[1] = (int) (y + TrixelBaseSideLength.LENGTH * scale);

			xPoints[2] = (int) (x + TrixelBaseSideLength.getMedian() * scale);
			yPoints[2] = (int) (y + TrixelBaseSideLength.LENGTH * scale * 0.5);
		}
		else if(direction == TrixelDirectionMap.LEFT){
			xPoints[0] = (int) (x + TrixelBaseSideLength.getMedian() * scale);
			yPoints[0] = y;

			xPoints[1] = (int) (x + TrixelBaseSideLength.getMedian() * scale);
			yPoints[1] = (int) (y + TrixelBaseSideLength.LENGTH * scale);

			xPoints[2] = x;
			yPoints[2] = (int) (y + TrixelBaseSideLength.LENGTH * scale * 0.5);
		}
	}

	public void drawTrixel(Graphics g, TrixelHandler trixelHandler) {
		g.setColor(color);
		g.fillPolygon(shape);
		if(trixelHandler.curTrixel != null && trixelHandler.curTrixel.equals(this)){
			g.setColor(Color.BLACK);
			g.drawPolygon(shape);
		}
	}

	public Polygon getShape(){
		return shape;
	}
	
	public Color getColor(){
		return color;
	}

	public void setColor(Color color){
		if(color != null)
			this.color = color;
	}
}
