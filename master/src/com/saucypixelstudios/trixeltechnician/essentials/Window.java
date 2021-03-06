package com.saucypixelstudios.trixeltechnician.essentials;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;


public class Window extends JFrame {
	static GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
  // The width and height of the window (in pixels).
  public static final int WIDTH =  gd.getDisplayMode().getWidth();
  public static final int HEIGHT = gd.getDisplayMode().getHeight();
  
  // The constructor for the Window class.
  public Window(String title) {
    setTitle(title);
    setLayout(new BorderLayout());
    setDefaultCloseOperation(EXIT_ON_CLOSE);

    setLocationRelativeTo(null);
    setResizable(false);
    centerFrame();
  }
  
  private void centerFrame() {
	  Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	  Point middle = new Point(screenSize.width / 2, screenSize.height / 2);
	  Point newLocation = new Point(middle.x - (getWidth() / 2), 
	                                middle.y - (getHeight() / 2));
	  setLocation(-3, -1);
}

  
}