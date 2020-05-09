
/** 
* File Name: CMIS242PRJ3XaireB
* Author: Borja X13
* Date: 29 April 2020
* UMGC CMIS 242
* Project 3
*/

package cmis242prj3xaireb;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


/*
 * This program creates a UI to allow the user to select the shape, color and
 * and fill. It will draw the shapes and give errors if a non-integer is 
 * entered or if the shape will not fit inside the box.
 */


public class CMIS242PRJ3XaireB {

public static abstract class Shape extends Rectangle {
    
   private static final long serialVersionUID = 1L;
   // instance variables
   private String color;
   private String shape;
   private static int count;
   
   // create shape constructor
   public Shape(Point p, Dimension d, String color, String shape) {
       super(p, d);
       this.color = color;
       this.shape = shape;
       count++;
   }//end public Shape(Point p, Dimension d, String color, String shape)
   
   // method to set color of the graphics
   public void setColor(Graphics g) {
       if(color.equalsIgnoreCase("black"))
           g.setColor(Color.BLACK);
       else if(color.equalsIgnoreCase("red"))
           g.setColor(Color.RED);
       else if(color.equalsIgnoreCase("orange"))
           g.setColor(Color.ORANGE);
       else if(color.equalsIgnoreCase("yellow"))
           g.setColor(Color.YELLOW);
       else if(color.equalsIgnoreCase("green"))
           g.setColor(Color.GREEN);
       else if(color.equalsIgnoreCase("blue"))
           g.setColor(Color.BLUE);
       else if(color.equalsIgnoreCase("magenta"))
           g.setColor(Color.MAGENTA);
   }//end public void setColor(Graphics g)
   
   // return shape type is solid or hollow
   public String getSolid() {
       return shape;
   }//end public String getSolid() 
   
   // returns no of shapes draw
   public static int getNoOfShapes() {
       return count;
   }//end public static int getNoOfShapes()
   
   // abstract draw method
   public abstract void draw(Graphics g);
   
}//end public static abstract class Shape extends Rectangle

//create oval class
public static class Oval extends Shape {
    
   private static final long serialVersionUID = 1L;
   
   // create oval constructor
   public Oval(Point p, Dimension d, String color, String shape) {
       super(p, d, color, shape);
   }//end public Oval(Point p, Dimension d, String color, String shape)
  
   //
   public void draw(Graphics g) {
       // drawing shape according to shape type
       if(getSolid().equalsIgnoreCase("hollow"))
           g.drawOval((int)getX(), (int)getY(), (int)getWidth(), (int)getHeight());
       else if(getSolid().equalsIgnoreCase("solid"))
           g.fillOval((int)getX(), (int)getY(), (int)getWidth(), (int)getHeight());
   }//end public void draw(Graphics g)
   
} //end public static class Oval extends Shape   

public static class Rectangular extends Shape {
  
   private static final long serialVersionUID = 1L;
   // parameterized constructor
   public Rectangular(Point p, Dimension d, String color, String shape) {
       super(p, d, color, shape);
   }//end public Rectangular(Point p, Dimension d, String color, String shape)
   
   public void draw(Graphics g) {
       // drawing shape according to shape type
       if(getSolid().equalsIgnoreCase("hollow"))
           g.drawRect((int)getX(), (int)getY(), (int)getWidth(), (int)getHeight());
       else if(getSolid().equalsIgnoreCase("solid"))
           g.fillRect((int)getX(), (int)getY(), (int)getWidth(), (int)getHeight());
   }//end public void draw(Graphics g)
   
}//end public static class Rectangular extends Shape

// user defined exception
public static class OutsideBounds extends Exception {
   private static final long serialVersionUID = 1L;
   // parameterized constructor
   public OutsideBounds(String errorMessage) {
       // call super class parameterized constructor
       super(errorMessage);   
   }//end public OutsideBounds(String errorMessage)
   
}//end public class OutsideBounds extends Exception
    
public static class Drawing extends JFrame implements ActionListener {
    
   private static final long serialVersionUID = 1L;
   // create Shape class reference
   Shape s=null;
   // declare required controls
   private JLabel shape;
   private JLabel fillType;
   private JLabel color;
   private JLabel width;
   private JLabel height;
   private JLabel x;
   private JLabel y;
   private JLabel printCount;
   private JComboBox<String> cshape;
   private JComboBox<String> cfillType;
   private JComboBox<String> ccolor;
   private JTextField twidth;
   private JTextField theight;
   private JTextField tx;
   private JTextField ty;
   private JButton draw;
   private JPanel panel;
   // declare variables
   int shapeWidth;
   int shapeHeight;
   int shapeX;
   int shapeY;
   // default constructor

   public Drawing() {
       setSize(600,400); // set size fo frame
       setDefaultCloseOperation(EXIT_ON_CLOSE);
       setLayout(null); // set no layout
       setLocationRelativeTo(null); // centered frame
       setTitle("Geometric Drawing"); // title
       // instantiate all declared controls
       shape=new JLabel("Shape Type");
       fillType=new JLabel("Fill Type");
       color=new JLabel("Color");
       width=new JLabel("Width");
       height=new JLabel("Height");
       x=new JLabel("X Coordinate");
       y=new JLabel("Y Coordinate");
       printCount=new JLabel("1");
       cshape=new JComboBox<>(new String[] {"Oval","Rectangle"});
       cfillType=new JComboBox<>(new String[] {"Solid","Hollow"});
       ccolor=new JComboBox<>(new String[] {"Black","Red","Orange","Yellow","Green","Blue","Magenta"});
       twidth=new JTextField();
       theight=new JTextField();
       tx=new JTextField();
       ty=new JTextField();
       draw=new JButton("Draw");
       panel=new JPanel();
       // set border with title to panel
       panel.setBorder(BorderFactory.createTitledBorder("Shape Drawing"));
       // add all controls into frame
       add(shape);
       add(fillType);
       add(color);
       add(width);
       add(height);
       add(x);
       add(y);
       add(printCount);
       add(cshape);
       add(cfillType);
       add(ccolor);
       add(twidth);
       add(theight);
       add(tx);
       add(ty);
       add(draw);
       add(panel);
       // set bounds for all controls
       shape.setBounds(20, 20, 100, 30);
       fillType.setBounds(20, 55, 100, 30);
       color.setBounds(20, 90, 100, 30);
       width.setBounds(20, 125, 100, 30);
       height.setBounds(20, 160, 100, 30);
       x.setBounds(20, 195, 100, 30);
       y.setBounds(20, 230, 100, 30);
       printCount.setBounds(285, 40, 20, 30);
       cshape.setBounds(140, 20, 100, 30);
       cfillType.setBounds(140, 55, 100, 30);
       ccolor.setBounds(140, 90, 100, 30);
       twidth.setBounds(140, 125, 100, 30);
       theight.setBounds(140, 160, 100, 30);
       tx.setBounds(140, 195, 100, 30);
       ty.setBounds(140, 230, 100, 30);
       draw.setBounds(230, 280, 80, 30);
       panel.setBounds(270, 20, 300, 250);
       // adding action listener to draw button
       draw.addActionListener(this);
       setVisible(true); // make frame visible
   }//end public Drawing() 

   // private method to check all possible exceptions exceptions
   private void checkException() {
       try {
           shapeWidth=Integer.parseInt(twidth.getText());
           shapeHeight=Integer.parseInt(theight.getText());
           shapeX=Integer.parseInt(tx.getText());
           shapeY=Integer.parseInt(ty.getText());
           if((shapeWidth+shapeX)>300 || (shapeHeight+shapeY)>250 || shapeWidth<0 || shapeHeight<0 || shapeX<0 || shapeY<0)
               throw new OutsideBounds("This shape will not fit im the box.");
       }catch(OutsideBounds o) {
           JOptionPane.showMessageDialog(null, o.getMessage());
       }catch(Exception e) {
               JOptionPane.showMessageDialog(null, "Height, Width and Coordinates must be integer values.");
       }//end try catch block

   }//end private void checkException()

   
   public void paint(Graphics g) {
       try {
           // print count of shapes
           printCount.setText(Shape.getNoOfShapes()+"");
           super.paintComponents(g);
           s.setColor(g);
           s.draw(g);
       }catch(Exception e) {}
   }//end public void paint(Graphics g)
  
   public void actionPerformed(ActionEvent arg0) {
       try {
           checkException();
           Point p=new Point(shapeX+270, shapeY+50);
           Dimension d=new Dimension(shapeWidth, shapeHeight);
           if(((String) cshape.getSelectedItem()).equalsIgnoreCase("oval"))
               s=new Oval(p, d, (String)ccolor.getSelectedItem(), (String)cfillType.getSelectedItem());
           else
               s=new Rectangular(p, d,(String)ccolor.getSelectedItem(), (String)cfillType.getSelectedItem());
           repaint(); // call paint() method
       }catch(Exception e) {}
   }//end public void actionPerformed(ActionEvent arg0)
   
}//end public class Drawing extends JFrame implements ActionListener
    
    public static void main(String[] args) {
       new Drawing();
        
    }//end public static void main(String[] args)
    
}//end public class CMIS242PRJ3XaireB
