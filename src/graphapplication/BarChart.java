package graphapplication;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;


public class BarChart extends JPanel {
   public static int MAX_VALUE = 0;
    private final int WINDOWWIDTH = 800;
   private final int WINDOWHEIGHT = 650;
   private static final int GAP = 15;
   private static int XIS_COUNT = 0;
   private List<Integer> Value;
   public static String TITLE="";
   public static String YTITLE="";
   public static String XTITLE="";
   public static int INTERVAL=0;
   public static int AVERAGE=0;
   public BarChart(List<Integer> value) {
      this.Value = value;
   }

   @Override
   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D)g;
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

      double xScale = ((double) getWidth() - 2 * GAP) / (Value.size() - 1);
      double yScale = ((double) getHeight() - 2 * GAP) / (MAX_VALUE - 1);

      List<Point> graphPoints = new ArrayList<Point>();
      for (int i = 0; i < Value.size(); i++) {
         int x1 = (int) (i * xScale + GAP);
         int y1 = (int) ((MAX_VALUE - Value.get(i)) * yScale + GAP);
         graphPoints.add(new Point(x1, y1));
      }
      g.drawString(TITLE, getWidth() - 400, 25);
      g.drawString(YTITLE, 0, getHeight()-400);
      g.drawString(XTITLE, getWidth() - 200, getHeight());
      // create x and y axes 
      g2.drawLine(GAP, getHeight() - GAP, GAP, GAP);
      g2.drawLine(GAP, getHeight() - GAP, getWidth() - GAP, getHeight() - GAP);

      // VALUES ON Y AXIS
      for (int i = 0; i < Value.size(); i++) {
         int x0 = GAP;
         int y0 = getHeight() - (((i + 1) * (getHeight() - GAP * 2)) / XIS_COUNT + GAP);
          g.drawString( Integer.toString(Value.get(i)), x0, y0);
      }

      // VALUES ON X AXIS
      for (int i = 0; i < Value.size() - 1; i++) {
         int x0 = (i + 1) * (getWidth() - GAP * 2) / (Value.size() - 1) + GAP;
         int y0 = getHeight() - GAP;
         int temp=INTERVAL*i;
         g.drawString( Integer.toString(temp), x0, y0);
      }

      for (int i = 0; i < graphPoints.size() - 1; i++) {
         int x1 = graphPoints.get(i).x;
         int y1 = graphPoints.get(i).y;
         int x2 = graphPoints.get(i + 1).x;
         int y2 = graphPoints.get(i + 1).y;
         g.setColor(Color.red);
         g.fillRect(x1, y1, 40, WINDOWHEIGHT-y1);
         g.drawRect(x1, y1, 40, WINDOWHEIGHT-y1);
      }
      
      g2.drawLine(0, WINDOWHEIGHT-AVERAGE*3, WINDOWWIDTH, WINDOWHEIGHT-AVERAGE*3);   
    }
     
   

   @Override
   public Dimension getPreferredSize() {
      return new Dimension(WINDOWWIDTH, WINDOWHEIGHT);
   }

   private static void CreateGraph() {
      DataClass dc=new DataClass();
       dc.ReadTextFile("D:\\sample3.txt");
       List<Integer> values = new ArrayList<Integer>();
       values=dc.getDataLIST();
       values=dc.SortList(values);
       MAX_VALUE=dc.FinMaxValue(values);
       TITLE=dc.getTITLE();
       YTITLE=dc.getYLABEL();
       XTITLE=dc.getXLABEL();
       XIS_COUNT=values.size();
       INTERVAL=dc.getInterval();
      AVERAGE= dc.FindAverage(values);
      BarChart mainPanel = new BarChart(values);

      
      JFrame frame = new JFrame("BarChart");
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