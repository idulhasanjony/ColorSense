/** ColorSense - v1.0
 *  DSA Semester 3 Project
 *  Instructor : Dr.Hamid Mukhtar
 *  By: Shumail Mohyuddin & Muhammad Bhatti
 *  Dated : December 2013
 */
package com.seven.diagonals.color.sense;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*Gets x and y coordinate of the location where mouse has been clicked lolwut?*/
public class LocationPrinter extends MouseAdapter {
	  @Override
	  public void mouseClicked(MouseEvent me) {
	    int screenX = me.getX();
	    int screenY = me.getY();
	    ColorFrame.rgbImage(screenX, screenY);
	  }
	}