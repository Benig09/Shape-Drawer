/*
 * To cha nge this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shape;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DrawShape extends JApplet {
    private int x1, x2, y1, y2;
    private boolean mousePressed = false;
    private Point clickPoint;
    int[] xPoints = {0, 25, 50}; //triangle x-points
    int[] yPoints = {50, 0, 50}; //triangle y-points
    double angle = 0f;
    
    String[] shapes = {"Oval", "Rectangle", };
    String[] colors = {"Red", "Blue", "Green", "Black"};
    private JComboBox shapeMenu = new JComboBox(shapes);
    private JComboBox colorsMenu = new JComboBox(colors);
    
    private Canvas canvas = new Canvas();
    
    public void init(){
        //To make the menu bar
        JPanel menuPanel = new JPanel();
        JButton clear = new JButton("Clear");
        menuPanel.add(shapeMenu);
        menuPanel.add(colorsMenu);
        menuPanel.add(clear);
        
        clear.addActionListener(new CanvasListener());
        canvas.addMouseListener(new MouseListener());
        canvas.addMouseMotionListener(new MouseMotionListener());
        canvas.setBackground(Color.white);
        getContentPane().add(canvas, BorderLayout.CENTER); 
        add(menuPanel, BorderLayout.SOUTH);
    }
    
    class MouseListener extends MouseAdapter{
       
        public void mousePressed(MouseEvent e){
            mousePressed = true;
            clickPoint = new Point(e.getPoint());
            if (clickPoint.y < 40){
                clickPoint.equals(false);
            }
        }
    }
    
    class MouseMotionListener extends MouseMotionAdapter {
 
        public void mouseDragged(MouseEvent e){
            x1 = Math.min(e.getX(), clickPoint.x);
            y1 = Math.min(e.getY(), clickPoint.y);
            x2 = Math.max(e.getX(), clickPoint.x);
            y2 = Math.max(e.getY(), clickPoint.y);
            int size = Math.min(x2 - x1, y2 - y1);
            if (x1 < clickPoint.x) {
                x1 = clickPoint.x - size;
            }
            if (y1 < clickPoint.y) {
                y1 = clickPoint.y - size;
            }
            angle = -Math.toDegrees(Math.atan2(e.getPoint().x - clickPoint.x, 
                    e.getPoint().y - clickPoint.y)) + 180;
            repaint();
        }
    }
    

    class CanvasListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            canvas.Clear();
        }
    }

   
    class Canvas extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
           
            
            super.paintComponent(g);
            
            String s = (String) shapeMenu.getSelectedItem();
            String c = (String) colorsMenu.getSelectedItem();
            Graphics2D g2d = (Graphics2D) g.create();
            
            if (mousePressed) {
                switch(c){
                    case "Red":
                        g.setColor(Color.RED);
                        g2d.setColor(Color.RED);
                        break;
                    case "Blue":
                        g.setColor(Color.BLUE);
                        g2d.setColor(Color.BLUE);
                        break;
                    case "Green":
                        g.setColor(Color.GREEN);
                        g2d.setColor(Color.GREEN);
                        break;
                    case "Black":
                        g.setColor(Color.BLACK);
                        g2d.setColor(Color.BLACK);
                        break;
                }
                switch(s){
                    case "Oval":
                        g.fillOval(x1, y1, x2-x1, y2-y1);
                        break;
                    case "Rectangle":
                        g.fillRect(x1, y1, x2 - x1, y2 - y1);
                        break;
                   
                }
            }
        }
        
        //method to clear the canvas
       
    
     public void Clear(){
            x1 = x2 = y1 = y2 = clickPoint.x = clickPoint.y = 0;
            repaint();
        }
    }
}
