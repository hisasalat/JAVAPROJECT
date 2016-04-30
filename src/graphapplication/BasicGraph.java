/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphapplication;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class BasicGraph extends JPanel {
   public static int MAX_VALUE = 0;
   private final int WINDOWWIDTH = 800;
   private final int WINDOWHEIGHT = 650;
   private final int GAP = 15;
   private final List<Integer> Values;
   public static String TITLE="";
   public static String YTITLE="";
   public static String XTITLE="";
   public BasicGraph(List<Integer> value) {
      this.Values = value;
   }

   @Override
   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D)g;
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

      double xScale = ((double) getWidth() - 2 * GAP) / (Values.size() - 1);
      double yScale = ((double) getHeight() - 2 * GAP) / (MAX_VALUE - 1);

      List<Point> graphPoints = new ArrayList<Point>();
      for (int i = 0; i < Values.size(); i++) {
         int x1 = (int) (i * xScale + GAP);
         int y1 = (int) ((MAX_VALUE - Values.get(i)) * yScale + GAP);
         graphPoints.add(new Point(x1, y1));
      }
      g.drawString(TITLE, getWidth() - 400, 25);
      g.drawString(YTITLE, 0, getHeight()-400);
      g.drawString(XTITLE, getWidth() - 200, getHeight());
      // create x and y axes 
      g2.drawLine(GAP, getHeight() - GAP, GAP, GAP);
      g2.drawLine(GAP, getHeight() - GAP, getWidth() - GAP, getHeight() - GAP);

     
      Stroke oldStroke = g2.getStroke();
      for (int i = 0; i < graphPoints.size() - 1; i++) {
         int x1 = graphPoints.get(i).x;
         int y1 = graphPoints.get(i).y;
         int x2 = graphPoints.get(i + 1).x;
         int y2 = graphPoints.get(i + 1).y;
         g2.drawLine(x1, y1, x2, y2);         
      }
      g2.setStroke(oldStroke);      
   }

   @Override
   public Dimension getPreferredSize() {
      return new Dimension(WINDOWWIDTH, WINDOWHEIGHT);
   }

   private static void CreateGraph() {
       
       DataClass dc=new DataClass();
       dc.ReadTextFile("D:\\sample.txt");
       List<Integer> values = new ArrayList<Integer>();
       values=dc.getDataLIST();
       MAX_VALUE=dc.FinMaxValue(values);
       TITLE=dc.getTITLE();
       YTITLE=dc.getYLABEL();
       XTITLE=dc.getXLABEL();
      BasicGraph mainPanel = new BasicGraph(values);
      JFrame frame = new JFrame("BasicGraph");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.getContentPane().add(mainPanel);
      frame.pack();
      frame.setLocationByPlatform(true);
      frame.setVisible(true);
   }

   public static void main(String[] args) {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            CreateGraph();
         }
      });
   }
}