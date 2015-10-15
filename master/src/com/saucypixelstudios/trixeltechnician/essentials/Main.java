package com.saucypixelstudios.trixeltechnician.essentials;

public class Main {
  
  public static void main(String args[]) {
	  
    Window window = new Window("Edit title in \"Main.java\"");
    TrixelTechnicianExecution area = new TrixelTechnicianExecution(window);
    window.requestFocus();
    window.add(area);
    window.pack();
    window.setVisible(true);
    area.start(window); 
  }
  
}
