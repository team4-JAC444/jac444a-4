// Assignment # 2
// The map
// Authors: Anna Kalika
//          Mohammad Zainuddin
//          Iulia Tiliute
// Teacher: Peter Liu
// Date: March 29, 2012
// In this assignment we used  Google API
// This map was created from scratch

package JAC444_A2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.*;

	/**
	 *  @Nazmul - Source -- Geocoding Tutorial – Accessing Google Static Maps from Java (April 21, 2008) 
	 *  This class is used to create a Custom Map application
	  
	*/

public class JAC444_A2 extends JFrame {
	
	/** 
	 * Declaring variables to hold the values:
	 * 	@cityList - name of the Cities, 
	 *  @countryList - Countries
	 *  @countryzoom - will handle zooming purposes  
	*/
	
	static String citylist[] = {"Toronto","Ottawa","Montreal","Vancouver","Edmonton","Calgary","Winnipeg"};		
	static String countrylist[] = {"Canada", "Russia", "China", "India", "Cuba","Mexico","France"};
	static Integer countryzooom[] = {3, 2, 3, 4, 6,4,4};
    int zoom = 10;
	static int index = 0;
	BufferedImage image = null;
	boolean status = true;
	
	/**
	   User-Defined exception handling along with all the layout definitions 
	*/
	
    public JAC444_A2() throws MalformedURLException     {
		final JFrame frame = new JFrame("JAC444 - Assignment 2 - the Map");		
		final JPanel panel = new JPanel();
		final JLayeredPane lpane = new JLayeredPane();
	    final JComboBox combo = new JComboBox(citylist);
	    final JComboBox combocountry = new JComboBox(countrylist);
		JPanel ctlpan = new JPanel();
		JButton btn_pls = new JButton("+");
		JButton btn_min = new JButton("-");
		JButton btn_sav = new JButton("Save As");
		JLabel jlb1 = new JLabel("Countries");	
		JLabel jlb2 = new JLabel("Cities");	
		JLabel jlb3 = new JLabel("****Zoom****");
		JLabel jlb4 = new JLabel("Save image");
		
		ctlpan.setBackground(Color.PINK);
		
		jlb1.setForeground( Color.BLUE);
		jlb2.setForeground( Color.BLUE);
		jlb3.setForeground( Color.BLUE);
		jlb4.setForeground( Color.BLUE);
		
		image = GetImage(ComposeURI(citylist[index], zoom));

    	frame.setPreferredSize(new Dimension(500, 400));         
    	frame.setLayout(new BorderLayout());
		lpane.setBounds(0, 0, 500, 400);
		
	//	button.setOpaque(true);
		
		ctlpan.add(jlb1);
		ctlpan.add(combocountry);
		ctlpan.add(jlb2);
		ctlpan.add(combo);	
		ctlpan.add(jlb3);
		ctlpan.add(btn_pls);
		ctlpan.add(btn_min);
		ctlpan.add(jlb4);
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
		
	/** 
	 * @registering listener for the drop down list "cities"
	 */
		combo.addItemListener(new ItemListener() {
			   public void itemStateChanged(ItemEvent ie) {
				   String str = (String)combo.getSelectedItem();
				   image = GetImage(ComposeURI(str, zoom));
				   label.setIcon(new ImageIcon(image));
				   label.revalidate();
				   status = true;
			   }
		});
		
		/** 
		 * @registering listener for the drop down list "countries"		
		 */
		combocountry.addItemListener(new ItemListener() {
			   public void itemStateChanged(ItemEvent ie) {
				   String str = (String)combocountry.getSelectedItem();
				   image = GetImage(ComposeURI_country(str, countryzooom[combocountry.getSelectedIndex()]));
				   label.setIcon(new ImageIcon(image));
				   label.revalidate();
				   status = false;
			   }
		});
		
	/** 
	 * @registering listener for the "+" button		
	 */
	    btn_pls.addMouseListener(new MouseListener() {
	        public void mouseClicked(MouseEvent e) {
	        	
	        	String str = new String("");
	        	Integer zoo;
    	
	        	if(status) {
	        		if(zoom <19) zoom++;
	        		str = (String)combo.getSelectedItem();
	        		zoo = zoom;
	        		image = GetImage(ComposeURI(str, zoo));
	        	}
	        	else {
	        		str = (String)combocountry.getSelectedItem();
	        		zoo = countryzooom[combocountry.getSelectedIndex()];
	        		if(zoo <19) zoo++;
	        		countryzooom[combocountry.getSelectedIndex()] = zoo;
	        		image = GetImage(ComposeURI_country(str, zoo));
	        	}
	        	label.setIcon(new ImageIcon(image));
	        	label.revalidate();			
	        }
	        public void mousePressed(MouseEvent e) { }
	        public void mouseReleased(MouseEvent e) { }
	        public void mouseEntered(MouseEvent e) { }
	        public void mouseExited(MouseEvent e) { }
	      });    
	    
	   /** 
	    * @registering listener for the "-" button	    
	    */

	    btn_min.addMouseListener(new MouseListener() {
	        public void mouseClicked(MouseEvent e) {
	        	
	        	String str = new String("");
	        	Integer zoo;

	        	if(status) {
	        		if(zoom > 1) zoom--;
	        		str = (String)combo.getSelectedItem();
	        		zoo = zoom;
	        		image = GetImage(ComposeURI(str, zoo));
	        	}
	        	else {
	        		str = (String)combocountry.getSelectedItem();
	        		zoo = countryzooom[combocountry.getSelectedIndex()];
	        		if(zoo > 1) zoo--;
	        		countryzooom[combocountry.getSelectedIndex()] = zoo;
	        		image = GetImage(ComposeURI_country(str, zoo));
	        	}
	        	label.setIcon(new ImageIcon(image));
	        	label.revalidate();				        	
	        }
	        public void mousePressed(MouseEvent e) { }
	        public void mouseReleased(MouseEvent e) { }
	        public void mouseEntered(MouseEvent e) { }
	        public void mouseExited(MouseEvent e) { }
	      });    
	    
	   /** 
	    * @registering listener for the button "save to"	    
	    */
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
    
   /** 
    * @returning the composed URI for cities
    */
    public String ComposeURI(String location, int zoom) {
    	String uri = null;
    	uri =  "http://maps.google.com/maps/api/staticmap?center=";
    	uri += location;
    	uri += ",Canada&zoom=";
    	uri += zoom;
    	uri += "&size=500x400&sensor=false";
    	
    	
    	return uri;
    }
   /** 
    * @returning the composed URI for countries    
    */
    public String ComposeURI_country(String location, int zoom) {
    	String uri = null;
    	uri =  "http://maps.google.com/maps/api/staticmap?center=";
    	uri += location;
    	uri +=",";
    	uri += "&zoom=";
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
    
    /** The main method will be called by the JVM.

    @param args the command-line arguments
 */  
    public static void main(String[] args) throws Exception {         
    	new JAC444_A2();     
    }	
}
