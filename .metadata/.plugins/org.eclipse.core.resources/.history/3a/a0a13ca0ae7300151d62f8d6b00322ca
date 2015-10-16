package com.saucypixelstudios.trixeltechnician.essentials;

import java.awt.BorderLayout;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

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
  }
  
}