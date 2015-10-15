package com.saucypixelstudios.trixeltechnician.essentials;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.saucypixelstudios.trixeltechnician.trixels.Trixel;
import com.saucypixelstudios.trixeltechnician.trixels.TrixelBaseSideLength;
import com.saucypixelstudios.trixeltechnician.trixels.TrixelDirectionMap;
import com.saucypixelstudios.trixeltechnician.trixels.TrixelHandler;

public class TrixelTechnicianPanel extends JPanel implements KeyListener, MouseListener{
	JTextField typingArea;
	static Window window;
	TrixelHandler trixelHandler;
	boolean mouseDown;
	int mouseButton;

	public TrixelTechnicianPanel(Window window){
		this.window = window;
		mouseDown = false;
		mouseButton = MouseEvent.BUTTON1;

		typingArea = new JTextField();
		typingArea.addKeyListener(this);
		addMouseListener(this);
		add(typingArea, BorderLayout.PAGE_START);

		setBackground(Color.decode("#FFFFFF"));
		setPreferredSize(new Dimension(Window.WIDTH, Window.HEIGHT));
		setDoubleBuffered(true);

		trixelHandler = new TrixelHandler();
	}
	
	public void run(){
		int mX = getMouseX()-4;
		int mY = getMouseY()-26;

		//System.out.println("Mouse: " + mX + ", " + mY);
		
		boolean onTrixel = false;
		for(int tW = 0; tW < trixelHandler.trixels.length; tW++)
			for(int tH = 0; tH < trixelHandler.trixels[0].length; tH++){
				Trixel trixel = trixelHandler.trixels[tW][tH];
				if(trixel.getShape().contains(new Point(mX, mY))){
					trixelHandler.curTrixel = trixel;
					//System.out.println("Shape: " + trixel.getShape().xpoints[0] + ", " + trixel.getShape().ypoints[0]);
					onTrixel = true;
				}
			}
		if(!onTrixel)
			trixelHandler.curTrixel = null;
		
		if(mouseDown && mouseButton == MouseEvent.BUTTON1)
			trixelHandler.fillCurTrixel(trixelHandler.PRIMARY);
		else if(mouseDown && mouseButton == MouseEvent.BUTTON3)
			trixelHandler.fillCurTrixel(trixelHandler.SECONDARY);
	}

	public void paint(Graphics g){
		super.paint(g);

		for(int t = 0; t < trixelHandler.trixels.length; t++){
			for(int tH = 0; tH < trixelHandler.trixels[0].length; tH++)
				if(trixelHandler.trixels[t][tH] != null)
					trixelHandler.trixels[t][tH].drawTrixel(g, trixelHandler);
		}
		trixelHandler.curTrixel.drawTrixel(g, trixelHandler);
	}

	public int getMouseX(){
		return (int) (window.getMousePosition().getX());
	}

	public int getMouseY(){
		return (int) (window.getMousePosition().getY());
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		mouseDown = true;
		mouseButton = e.getButton();
		System.out.println(e.getButton());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mouseDown = false;
		mouseButton = e.getButton();
	}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}