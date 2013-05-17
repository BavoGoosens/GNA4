package gna;

import gna.MinimalSpanningTreeTour.MSTEdge;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MSTView extends JPanel {
  private MinimalSpanningTreeTour tour;
  
  public MSTView(MinimalSpanningTreeTour tour) {
    this.tour = tour;
  }
  
  private void drawPoint(Point point, Graphics g) {
    int size = 5;
    double x = point.getX();
    double y = point.getY();
    g.fillRect((int) (x - size / 2), (int) ((tour.getWorld().getHeight() - y) - size / 2), size, size);
  }
  
  private void drawLine(Point from, Point to, Graphics g) {
    g.drawLine((int) from.getX(), (int) (tour.getWorld().getHeight() - from.getY()), (int) to.getX(), (int) (tour.getWorld().getHeight() - to.getY()));
  }
  
  private void doPaint(Graphics g) {
    g.setColor(Color.WHITE);
    g.fillRect(0, 0, tour.getWorld().getWidth(), tour.getWorld().getHeight());
    g.setColor(Color.BLACK);
    List<MSTEdge> mst = tour.getMST();
    for(Point p : tour.getWorld().getPoints()) {
      drawPoint(p, g);
    }
    g.setColor(Color.RED);
    drawPoint(tour.getMSTRoot(), g);
    g.setColor(Color.BLACK);
    for(MSTEdge e : mst) {
      drawLine(e.p1, e.p2, g);
    }
  }
  
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    doPaint(g);
  }
  
  public void saveAsImage(String path) throws IOException {
    BufferedImage image = new BufferedImage(tour.getWorld().getWidth(), tour.getWorld().getHeight(), BufferedImage.TYPE_INT_ARGB);
    Graphics g = image.createGraphics();
    doPaint(g);
    g.dispose();
    ImageIO.write(image, "png", new File(path));
  }
}
