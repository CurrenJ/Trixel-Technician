package com.saucypixelstudios.trixeltechnician.essentials;

public class TrixelTechnicianExecution extends TrixelTechnicianPanel  {

	public TrixelTechnicianExecution(Window window) 
	{
		super(window);
	}

	//Method that will continually run until we exit the program 
	public void start(Window window) 
	{
		while(true) 
		{ 
			try 
			{ 	
				if(!paneOpen)
					window.toFront();
				run();
				repaint(); 
			}catch(Exception e) 
			{ 
				e.printStackTrace(); 
			} 
		} 

	}



}
