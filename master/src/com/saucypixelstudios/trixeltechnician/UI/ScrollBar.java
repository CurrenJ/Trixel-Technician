package com.saucypixelstudios.trixeltechnician.UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;

import com.saucypixelstudios.trixeltechnician.camera.Camera;
import com.saucypixelstudios.trixeltechnician.trixels.TrixelHandler;

public class ScrollBar {
	public static final int X_AXIS = 0;
	public static final int Y_AXIS = 1;
	private int grabX;
	private int grabY;
	private int axis;
	private final float MINIMUM_GRIP_SIZE = 16;
	private final float MAXIMUM_GRIP_SIZE;
	private final float TRACK_SIZE;
	private float windowScrollAreaSize;
	private float contentSize;
	private float windowPosition;
	private float windowPositionRatio;
	private float trackScrollAreaSize;
	private float gripPositionOnTrack;
	private float newGripPosition;
	private float gripSize;
	private final float WINDOW_SIZE;
	private Color barColor = Color.decode("#747474");
	private Color gripColor = Color.decode("#92AFD7");
	private Color gripColorOutline = Color.decode("#27364C");

	public ScrollBar(int axis){
		this.axis = axis;
		gripPositionOnTrack = 0;
		gripSize = (float) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 12);

		if(axis == X_AXIS){
			WINDOW_SIZE = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
			TRACK_SIZE = WINDOW_SIZE - 24;
		}
		else if(axis == Y_AXIS){
			WINDOW_SIZE = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
			TRACK_SIZE = WINDOW_SIZE - 56;
		}
		else{
			TRACK_SIZE = 0;
			WINDOW_SIZE = 0;
		}

		MAXIMUM_GRIP_SIZE = TRACK_SIZE;
	}

	public void mouseInteraction(int mX, int mY, Camera camera, TrixelHandler trixelHandler){
		System.out.println("Mouse at: " + mX + " " + mY);
		int deltaX = mX - grabX;
		int deltaY = mY - grabY;
		if(axis == X_AXIS){
			contentSize = camera.widthOfTrixelGrid(trixelHandler);
			newGripPosition = gripPositionOnTrack + deltaX;
			windowPosition = camera.getX();
		}
		else if(axis == Y_AXIS){
			contentSize = camera.heightOfTrixelGrid(trixelHandler);
			newGripPosition = gripPositionOnTrack + deltaY;
			windowPosition = camera.getY();
		}

		windowScrollAreaSize = contentSize - WINDOW_SIZE;
		windowPositionRatio = windowPosition / windowScrollAreaSize;
		trackScrollAreaSize = TRACK_SIZE - gripSize;
		//gripPositionOnTrack = trackScrollAreaSize * windowPositionRatio;

		//System.out.println(windowScrollAreaSize + " " + windowPosition + " " + windowPositionRatio);

		if(newGripPosition < 0)
			newGripPosition = 0;
		else if(newGripPosition > trackScrollAreaSize)
			newGripPosition = trackScrollAreaSize;

		float gripPositionRatio = newGripPosition / trackScrollAreaSize;
		if(axis == X_AXIS)
			camera.setX((int) (gripPositionRatio * windowScrollAreaSize));
		else if(axis == Y_AXIS)
			camera.setY((int) (gripPositionRatio * windowScrollAreaSize));
	}

	public void drawBar(Graphics g){
		if(axis == X_AXIS){
			g.setColor(barColor);
			g.fillRect(10, (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 40, (int) TRACK_SIZE, 10);
			g.setColor(Color.BLACK);
			g.drawRect(10, (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight() - 40, (int) TRACK_SIZE, 10);

			g.setColor(gripColor);
			g.fillRect((int) newGripPosition + 10, (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()- 40), (int) gripSize, 10);
			g.setColor(gripColorOutline);
			g.drawRect((int) newGripPosition + 10, (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()- 40), (int) gripSize, 10);
		}
		else if(axis == Y_AXIS){
			g.setColor(barColor);
			g.fillRect((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 18, 10, 10, (int) TRACK_SIZE);
			g.setColor(Color.BLACK);
			g.drawRect((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() - 18, 10, 10, (int) TRACK_SIZE);

			g.setColor(gripColor);
			g.fillRect((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()- 18), (int) newGripPosition + 10, 10, (int) gripSize);
			g.setColor(gripColorOutline);
			g.drawRect((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()- 18), (int) newGripPosition + 10, 10, (int) gripSize);
		}
	}
	
	public void grabBar(int x, int y){
		System.out.println("Grabbed bar at: " + x + " " + y);
		grabX = x;
		grabY = y;
	}
	
	public void releaseBar(){
		gripPositionOnTrack = newGripPosition;
	}

	public boolean collidesWithGrip(int x, int y){
		Rectangle hitbox = null;
		if(axis == X_AXIS)
			hitbox = new Rectangle((int) newGripPosition + 10, (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()- 40), (int) gripSize, 10);
		else if(axis == Y_AXIS)
			hitbox = new Rectangle((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()- 18), (int) newGripPosition + 10, 10, (int) gripSize);
		if(hitbox.contains(x, y))
			return true;
		return false;
	}

	public int getAxis(){
		return axis;
	}
}
