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
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.saucypixelstudios.trixeltechnician.trixels.ColorPickerMenu;
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
	boolean colorPickerOpen;
	ColorPickerMenu colorPickerMenu;
	boolean paneOpen;

	public TrixelTechnicianPanel(Window window){
		this.window = window;
		mouseDown = false;
		mouseButton = MouseEvent.BUTTON1;
		colorPickerOpen = false;
		paneOpen = false;

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
		if(colorPickerMenu == null)
			colorPickerMenu = new ColorPickerMenu(window.getWidth() ,window.getHeight());
		int mX = getMouseX()-4;
		int mY = getMouseY()-26;

		//System.out.println("Mouse: " + mX + ", " + mY);

		if(!colorPickerOpen){
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

			if(mouseDown && mouseButton == MouseEvent.BUTTON1)
				trixelHandler.fillCurTrixel(trixelHandler.PRIMARY);
			else if(mouseDown && mouseButton == MouseEvent.BUTTON3)
				trixelHandler.fillCurTrixel(trixelHandler.SECONDARY);
		}
	}

	public void save(){
		BufferedImage bi = new BufferedImage(this.getSize().width, this.getSize().height, BufferedImage.TYPE_INT_ARGB); 
		Graphics g = bi.createGraphics();
		this.paint(g);  //this == JComponent
		g.dispose();
		try{ImageIO.write(bi,"png",new File("Trixel Technician Art.png"));}catch (Exception e) {}
	}

	public void paint(Graphics g){
		super.paint(g);

		for(int t = 0; t < trixelHandler.trixels.length; t++){
			for(int tH = 0; tH < trixelHandler.trixels[0].length; tH++)
				if(trixelHandler.trixels[t][tH] != null)
					trixelHandler.trixels[t][tH].drawTrixel(g, trixelHandler);
		}
		if(trixelHandler.curTrixel != null)
			trixelHandler.curTrixel.drawTrixel(g, trixelHandler);


		if(colorPickerMenu != null && colorPickerOpen)
			colorPickerMenu.drawMenu(g);
	}

	public int getMouseX(){
		try{
			return (int) (window.getMousePosition().getX());
		} catch(Exception e){}
		return 0;
	}

	public int getMouseY(){
		try{
			return (int) (window.getMousePosition().getY());
		} catch(Exception e){}
		return 0;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(colorPickerOpen){
			if(e.getButton() == MouseEvent.BUTTON1)
				trixelHandler.primaryColor = colorPickerMenu.getColorOnClick(e.getX(), e.getY());
			else if(e.getButton() == MouseEvent.BUTTON3)
				trixelHandler.secondaryColor = colorPickerMenu.getColorOnClick(e.getX(), e.getY());
		}
	}

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
	public void keyPressed(KeyEvent e) {
		if(e.getKeyChar() == 'c')
			colorPickerOpen = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyChar() == 'c')
			colorPickerOpen = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if(e.getKeyChar() == 'x'){
			paneOpen = true;
			trixelHandler.primaryColor = Color.decode("#" + JOptionPane.showInputDialog("Enter the hex code you would like the primary color to be:"));
			paneOpen = false;
		}
		if(e.getKeyChar() == 'v'){
			paneOpen = true;
			trixelHandler.secondaryColor = Color.decode("#" + JOptionPane.showInputDialog("Enter the hex code you would like the secondary color to be:"));
			paneOpen = false;
		}		
		if(e.getKeyChar() == 's'){
			paneOpen = true;
			if(JOptionPane.showConfirmDialog(null, 
					"Would you like to save your current drawing?", 
					"Choose", 
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
				save();
			paneOpen = false;
		}
	}
}
