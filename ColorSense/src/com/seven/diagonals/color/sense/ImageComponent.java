/** ColorSense - v1.0
 *  DSA Semester 3 Project
 *  Instructor : Dr.Hamid Mukhtar
 *  By: Shumail Mohyuddin & Muhammad Bhatti
 *  Dated : December 2013
 */
package com.seven.diagonals.color.sense;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

class ImageComponent extends Component {
	private int w;
	private int h;
    private BufferedImage bi;
    private BufferedImage img;
    private int scaleFactor=1;
    private boolean isCircle = false;
    private int diameter = 20;
    private Color myColor = new Color (255, 255, 255);
    /*paint circle with this color*/
    public void changeColor(int r, int g, int b){
    	myColor = new Color (r,g,b);
    }
    public int imageW()
    {
    	return w/scaleFactor;
    }
    public int imageH()
    {
    	return h/scaleFactor;
    }
    public ImageComponent()
    {
    	isCircle = true;    	
    }
    /*reads image and outputs it on frame*/
    public ImageComponent(String imageSrc) {
        try {
            img = ImageIO.read(new File(imageSrc));
            w = img.getWidth(null);
            h = img.getHeight(null);
            bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            Graphics g = bi.getGraphics();
            g.drawImage(img, 0, 0, null);
 
        } catch (IOException e) {
            System.out.println("Image could not be read");
        }
    }
 
    /*Dimension, (to set size) if image or if circle*/
    public Dimension getPreferredSize() {
    	if(!isCircle)
    		return new Dimension(bi.getWidth(null), bi.getHeight(null));
    	else
    		return new Dimension(diameter,diameter);
    }
 
    /*paint which draws circle or image to the panel*/
    public void paint(Graphics g) {
    	Graphics2D g2d = (Graphics2D)g;
    	/*Antialising to remove jaggies from text/image etc*/
    	g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
    		    RenderingHints.VALUE_ANTIALIAS_ON);
    	/*Draws image*/
    	if(!isCircle)
    		g2d.drawImage(bi, 0,0,imageW()/scaleFactor, imageH()/scaleFactor, 0, 0, imageW(), imageH(), null);
    	else
    	{
    		/*Draws filled cirlce*/
	        Ellipse2D.Double circle = new Ellipse2D.Double(0, 0, diameter,diameter);
	        g2d.setColor(myColor);
	        g2d.fill(circle);
    	}
    }
}