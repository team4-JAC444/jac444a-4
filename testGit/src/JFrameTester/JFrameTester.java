package JFrameTester;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class JFrameTester extends JFrame {
	
  private static BufferedImage _img;

  static String citylist[] = {"Toronto","Ottawa","Montreal","Vancouver","Edmonton","Calgary","Winnipeg"};	  
  static String city_name = new String();	

  final static JTextField t1 = new JTextField(10);
  
  
  public static void main(String[] args)  throws Exception { 
    

	city_name = citylist[0];
	
	final JFrame    f1 = new JFrame("Canadian City Map");
    final JComboBox c1 = new JComboBox(citylist);	
    final JPanel    p1 = new JPanel();
    final JButton   b1 = new JButton("Get Map");    
    final JButton   b2 = new JButton("Zoom In");
    final JButton   b3 = new JButton("Zoom Out");
    final JButton   b4 = new JButton("Save As");    
    final JButton   b5 = new JButton("Cancel");
	
    
    f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    f1.setSize(720, 480);
    f1.setLocation(300,200);
//    f1.setLayout(new GridLayout(0, 4));
 
    p1.setLayout(new GridLayout(12, 0));
    JLabel l1 = new JLabel("Select City"); 
    l1.setHorizontalAlignment( SwingConstants.CENTER );

    p1.add(l1);
    p1.add(c1);
    p1.add(new JLabel(""));    
    p1.add(b1);
    p1.add(new JLabel(""));
    p1.add(b2);
    p1.add(b3);
    p1.add(new JLabel(""));
    p1.add(b4);
    p1.add(new JLabel(""));
    p1.add(new JLabel(""));    
    p1.add(b5);
    
    f1.add(p1);
//    f1.add(t1);
    
    f1.setVisible(true);

    f1.pack();
	
    
//	c1.addItemListener(new ItemListener(){
//		public void itemStateChanged(ItemEvent ie){
//			String str = (String)c1.getSelectedItem();
//		}
//	});
    
    b5.addMouseListener(new MouseListener() {
        public void mouseClicked(MouseEvent e) { f1.dispose(); }
        public void mousePressed(MouseEvent e) { }
        public void mouseReleased(MouseEvent e) { }
        public void mouseEntered(MouseEvent e) { }
        public void mouseExited(MouseEvent e) { }
      });    
    
    b1.addMouseListener(new MouseListener() {
        public void mouseClicked(MouseEvent e) { 
        		city_name = (String) c1.getSelectedItem(); // PrintMapName();
        		
        		final JFrame frame = new JFrame();     
        		JPanel panel = new JPanel();     
        		BufferedImage image = null;
        		String uri = "http://maps.google.com/maps/api/staticmap?center=";
        		uri += city_name;
        		uri += ",Canada&zoom=10&size=400x400&sensor=false";
				try {
					image = ImageIO.read(new URL(uri));
//					image = ImageIO.read(new URL("http://maps.google.com/maps/api/staticmap?center=Toronto,Canada&zoom=10&size=400x400&sensor=false"));
				} catch (MalformedURLException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}     
        		JLabel label = new JLabel(new ImageIcon(image));     
        		panel.add(label);     
        		frame.add(panel);     
        		frame.pack();     
        		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     
        		frame.setLocationRelativeTo(null);     
        		frame.setVisible(true);         		

        		frame.addMouseListener(new MouseListener() {
        			    public void mouseClicked(MouseEvent e) {}
        			    public void mousePressed(MouseEvent e) { frame.dispose();}
        			    public void mouseReleased(MouseEvent e) { }
        			    public void mouseEntered(MouseEvent e) { }
        			    public void mouseExited(MouseEvent e) { }
        			  });        		
        	}
        public void mousePressed(MouseEvent e) { }
        public void mouseReleased(MouseEvent e) { }
        public void mouseEntered(MouseEvent e) { }
        public void mouseExited(MouseEvent e) { }
      });
  }

  public static void PrintMapName()
  {
	 
	String str = "Getting map of " + city_name;
	t1.setText(str);
  }
}