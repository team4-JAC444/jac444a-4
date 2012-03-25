package JAcc_444_a2;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.*;


public class JAcc_444_a2 extends JFrame {
	
	static String citylist[] = {"Toronto","Ottawa","Montreal","Vancouver","Edmonton","Calgary","Winnipeg"};	  
    int zoom = 10;
	static int index = 0;
	BufferedImage image = null;
	
    public JAcc_444_a2() throws MalformedURLException     {
		final JFrame frame = new JFrame("JAC444 - Assignment 2 - Canadian City Map");     
		final JPanel panel = new JPanel();
		final JLayeredPane lpane = new JLayeredPane();
	    final JComboBox combo = new JComboBox(citylist);	
		JPanel ctlpan = new JPanel();
		JButton btn_pls = new JButton("+");
		JButton btn_min = new JButton("-");
		JButton btn_sav = new JButton("Save As");
				
		image = GetImage(ComposeURI(citylist[index], zoom));

    	frame.setPreferredSize(new Dimension(500, 400));         
    	frame.setLayout(new BorderLayout());
		lpane.setBounds(0, 0, 500, 400);
		
//		button.setOpaque(true);

		ctlpan.add(combo);
		ctlpan.add(btn_pls);
		ctlpan.add(btn_min);
		ctlpan.add(btn_sav);
		
    	ctlpan.setBounds(0, 0, 100, 400);         
    	ctlpan.setOpaque(true);
    	
    	final JLabel label = new JLabel(new ImageIcon(image));   
		panel.add(label);  
    	panel.setBounds(100, 0, 500, 400);         
    	panel.setOpaque(false);  

    	lpane.add(panel, new Integer(0), 0);
    	lpane.add(ctlpan, new Integer(1), 0);

    	frame.add(lpane, BorderLayout.CENTER);
		frame.pack();     
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		combo.addItemListener(new ItemListener() {
			   public void itemStateChanged(ItemEvent ie) {
				   String str = (String)combo.getSelectedItem();
				   image = GetImage(ComposeURI(str, zoom));
				   label.setIcon(new ImageIcon(image));
				   label.revalidate();
			   }
		});
 
	    btn_pls.addMouseListener(new MouseListener() {
	        public void mouseClicked(MouseEvent e) {
	        	if(zoom <19) zoom++;
	        	String str = (String)combo.getSelectedItem();
				image = GetImage(ComposeURI(str, zoom));
				label.setIcon(new ImageIcon(image));
				label.revalidate();
	        }
	        public void mousePressed(MouseEvent e) { }
	        public void mouseReleased(MouseEvent e) { }
	        public void mouseEntered(MouseEvent e) { }
	        public void mouseExited(MouseEvent e) { }
	      });    				

	    btn_min.addMouseListener(new MouseListener() {
	        public void mouseClicked(MouseEvent e) {
	        	if(zoom > 1) zoom--;
	        	String str = (String)combo.getSelectedItem();
				image = GetImage(ComposeURI(str, zoom));
				label.setIcon(new ImageIcon(image));
				label.revalidate();
	        }
	        public void mousePressed(MouseEvent e) { }
	        public void mouseReleased(MouseEvent e) { }
	        public void mouseEntered(MouseEvent e) { }
	        public void mouseExited(MouseEvent e) { }
	      });    		
	    
	    btn_sav.addMouseListener(new MouseListener() {
	        public void mouseClicked(MouseEvent e) {
	        	final JFileChooser fc = new JFileChooser();
	        	Component aComponent = null;
				int returnVal = fc.showSaveDialog(aComponent);
				
				if (returnVal == JFileChooser.APPROVE_OPTION) {
						File file = fc.getSelectedFile();
				        try {            
				        	ImageIO.write(image, "jpg", file);  
				        	} catch(IOException ex) {            
				        		System.out.println("Write error for " + file.getPath());
				        	}						
				        }
			}
			public void mousePressed(MouseEvent e) { }
	        public void mouseReleased(MouseEvent e) { }
	        public void mouseEntered(MouseEvent e) { }
	        public void mouseExited(MouseEvent e) { }
	      });    		    	    
    }

    public String ComposeURI(String location, int zoom) {
    	String uri = null;
    	uri =  "http://maps.google.com/maps/api/staticmap?center=";
    	uri += location;
    	uri += ",Canada&zoom=";
    	uri += zoom;
    	uri += "&size=500x400&sensor=false";
    	
    	
    	return uri;
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
    	new JAcc_444_a2();     
    }	
}
