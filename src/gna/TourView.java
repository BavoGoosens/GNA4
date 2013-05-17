package gna;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class TourView extends JPanel {
  private Tour tour;
  
  public TourView(Tour tour) {
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
    List<Point> sequence = tour.getVisitSequence();
    if (sequence.isEmpty())
      return;
    Point previous = sequence.get(0);
    drawPoint(previous, g);
    for (int i = 1; i < sequence.size(); i++) {
      Point next = sequence.get(i);
      drawPoint(next, g);
      drawLine(previous, next, g);
      previous = next;
    }
    drawLine(previous, sequence.get(0), g);
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
