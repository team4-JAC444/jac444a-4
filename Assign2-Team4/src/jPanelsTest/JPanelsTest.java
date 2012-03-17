package jPanelsTest;

import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.*;


public class JPanelsTest extends JFrame {
	
    public JPanelsTest() throws MalformedURLException     {
		final JFrame frame = new JFrame();     
		final JPanel panel = new JPanel();
		final JLayeredPane lpane = new JLayeredPane();
		
		BufferedImage image = null;
		try {
			image = ImageIO.read(new URL("http://maps.google.com/maps/api/staticmap?center=Berkeley,CA&zoom=14&size=400x400&sensor=false"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}    

    	frame.setPreferredSize(new Dimension(400, 400));         
    	frame.setLayout(new BorderLayout());
		lpane.setBounds(0, 0, 400, 400);
		
		JPanel ctlpan = new JPanel();
		JButton button = new JButton("OK");
		button.setOpaque(false);
		button.setContentAreaFilled( false ); 
		
//		button.setBackground(Color.gray);
		ctlpan.add(button);
    	ctlpan.setBounds(1, 1, 70, 70);         
    	ctlpan.setOpaque(false);
    	
    	final JLabel label = new JLabel(new ImageIcon(image));   
		panel.add(label);  
    	panel.setBounds(0, 0, 400, 400);         
    	panel.setOpaque(false);  

    	lpane.add(panel, new Integer(0), 0);
    	lpane.add(ctlpan, new Integer(1), 0);
    	
//		frame.add(panel);
    	frame.add(lpane, BorderLayout.CENTER);
		frame.pack();     
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		
	    button.addMouseListener(new MouseListener() {
	        public void mouseClicked(MouseEvent e) { 
	        	BufferedImage image = GetImage("http://maps.google.com/maps/api/staticmap?center=Toronto,Canada&zoom=14&size=400x400&sensor=false");
	        	label.setIcon(new ImageIcon(image));
//	        	label.setIcon(null);
	        	label.revalidate();
	        	
	        }
	        public void mousePressed(MouseEvent e) { }
	        public void mouseReleased(MouseEvent e) { }
	        public void mouseEntered(MouseEvent e) { }
	        public void mouseExited(MouseEvent e) { }
	      });    		
		
    }
	
    public BufferedImage GetImage(String uri) {
    	
		try {
			return ImageIO.read(new URL(uri));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}       	
    }
    
    public static void main(String[] args) throws Exception {         
    	new JPanelsTest();     
    }	
}

/*
public class JPanelsTest extends JFrame {

    private JFrame frame = new JFrame();     
    private JLayeredPane lpane = new JLayeredPane();     
    private JPanel panelBlue = new JPanel();     
    private JPanel panelGreen = new JPanel();     
    
    public JPanelsTest()     {         
    	frame.setPreferredSize(new Dimension(600, 400));         
    	frame.setLayout(new BorderLayout());
    	frame.add(lpane, BorderLayout.CENTER);
    	
    	lpane.setBounds(0, 0, 600, 400);
    	
    	panelBlue.setBackground(Color.BLUE);         
    	panelBlue.setBounds(0, 0, 600, 400);         
    	panelBlue.setOpaque(true);  
    	
    	panelGreen.setBackground(Color.GREEN);         
    	panelGreen.setBounds(200, 100, 100, 100);         
    	panelGreen.setOpaque(true);
    	
    	lpane.add(panelBlue, new Integer(0), 0);         
    	lpane.add(panelGreen, new Integer(1), 0);         
    	frame.pack();         
    	frame.setVisible(true); 	
    }

    public static void main(String[] args) {         
    	new JPanelsTest();     
    }
}
*/