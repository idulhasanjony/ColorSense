/** ColorSense - v1.0
 *  DSA Semester 3 Project
 *  Instructor : Dr.Hamid Mukhtar
 *  By: Shumail Mohyuddin & Muhammad Bhatti
 *  Dated : December 2013
 */


package com.seven.diagonals.color.sense;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JTextPane;
import java.awt.Toolkit;

public class ColorFrame extends JFrame{
	private File file;
	private final JFileChooser fileChooser = new JFileChooser("C:\\Users\\Muhammad\\Desktop\\finalguiimage");
	/*Panel*/
	private final JPanel panel = new JPanel();
	public final static JPanel panel2 = new JPanel();
	/*Draw*/
	private ImageComponent surface;
	private static ImageComponent but1;
	public static ImageComponent but2;
	/*Img*/
	private String imagePath;
	static BufferedImage imgR = null;
	private static int rgbPixel[] = new int [3];
	/*Label + TextPane*/
	private final JLabel lblColor = new JLabel("Color");
	private final JLabel lblColor2 = new JLabel("Closest");
	private final JLabel lblText = new JLabel("RGB");
	private final JLabel lblText2 = new JLabel("RGB");
	private final JLabel lblText3 = new JLabel("Name");
	private static final JTextPane textPane = new JTextPane();
	private static final JTextPane textPane2 = new JTextPane();
	private static final JTextPane colorTextP = new JTextPane();
	
	public ColorFrame() {
		/*Set font of labels and tpain :D */
		Font nFont = new Font("SansSerif", Font.BOLD, 13);
		textPane.setBackground(null);
		textPane2.setBackground(null);
		colorTextP.setBackground(null);
		lblColor.setFont(nFont);
		lblColor2.setFont(nFont);
		lblText.setFont(nFont);
		lblText2.setFont(nFont);
		lblText3.setFont(nFont);
		textPane.setFont(nFont);
		textPane2.setFont(nFont);
		colorTextP.setFont(nFont);
		colorTextP.setForeground(Color.BLUE);
		textPane.setText("?????????");
		textPane2.setText("?????????");
		colorTextP.setText("????????????");
		initGUI();		
	}
	
	/*Displays everything (JFrame)*/
	private void initGUI() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Muhammad\\Desktop\\l.jpg"));
		setTitle("Color Sense v1.0");
		//JOptionPane.showMessageDialog(this,"   Please choose an Image   ");
		
		/*FileChooser to display image*/
		int returnValue = fileChooser.showOpenDialog( null ) ;
		if( returnValue == JFileChooser.APPROVE_OPTION )
		       file = fileChooser.getSelectedFile() ;
		if(file != null){
		     imagePath = file.getPath();
		     readImage();
		}
		
		/*Set Layout + Panel etc Boring stuff :o */
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().add(panel, BorderLayout.WEST);
		FlowLayout flowLayout = (FlowLayout) panel2.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		getContentPane().add(panel2, BorderLayout.SOUTH);
		surface = new ImageComponent(imagePath);
		
		/*Mouse listener that displays relative info of pixel at the clicked location*/
		surface.addMouseListener(new LocationPrinter());
		/*Adding labels and textpanes etc*/
		panel.add(surface);
		panel2.add(lblColor);		
		but1 =new ImageComponent();
		panel2.add(but1, BorderLayout.EAST);
		
		panel2.add(lblText);
		textPane.setEditable(false);		
		panel2.add(textPane);
		
		panel2.add(lblColor2);
		but2 =new ImageComponent();
		panel2.add(but2, BorderLayout.EAST);		
		
		panel2.add(lblText2);
		textPane2.setEditable(false);
		panel2.add(textPane2);
		panel2.add(lblText3);
		panel2.add(colorTextP);

		/*JFrame stuff*/
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);				
	}
	/*Reads image and stores in buffer*/
	public void readImage()
	{
		try {
            imgR = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
        }
	}
	/*Following code displays relative info about pixel  and draws stuff and whatnot etccc*/
	public static void changeColor(int r, int g, int b)
	{
		but2.changeColor(r, g, b);
		but2.repaint();
	}
	public static void changeText(String text, String ctext)
	{	
		textPane2.setText(text);
		colorTextP.setText(ctext);
	}
	public static void rgbImage(int x, int y)
	{
		/*Gets rgb of selected pixel at the selected coordinate*/
		int rgb = imgR.getRGB(x,y);
		/*Extracts to r g b values*/
		rgbPixel[0]=   (rgb >> 16) & 0xFF;
		rgbPixel[1] = (rgb >>  8) & 0xFF;
		rgbPixel[2] =  (rgb      ) & 0xFF;
		
		/*Display color + RGB value*/
		but1.changeColor(rgbPixel[0], rgbPixel[1], rgbPixel[2]);
		but1.repaint();
		textPane.setText("(" + rgbPixel[0] + ", " + rgbPixel[1] + ", " + rgbPixel[2] + ")");
		ColorDetection detect = new ColorDetection(rgbPixel);
	}
	
	/*Initialiser for JFrame and alll theings related?*/
	public static void main(String[] args){
		EventQueue.invokeLater(new Runnable(){  // same as SwingUtilities.invokeLater()
		@Override
		public void run(){
			try{
					ColorFrame frame = new ColorFrame();
					frame.setVisible(true);
				}catch (Exception e){
					e.printStackTrace();
				}
			}
		});
	}
	
}
