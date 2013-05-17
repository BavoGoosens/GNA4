package gna;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class World {
  private final int width;
  private final int height;
  private final List<Point> points;
  
  public World(int width, int height, List<Point> points) {
    this.width = width;
    this.height = height;
    this.points = new ArrayList<>(points);
  }
  
  public World(InputStream stream) throws NoSuchElementException, IllegalStateException {
    Scanner scanner = new Scanner(stream);
    width = scanner.nextInt();
    height = scanner.nextInt();
    points = new ArrayList<Point>();
    while(scanner.hasNext()) {
      double x = scanner.nextDouble();
      double y = scanner.nextDouble();
      points.add(new Point(x, y));
    }
  }
  
  public World(String path) throws FileNotFoundException, NoSuchElementException, IllegalStateException {
    this(new FileInputStream(path));
  }
  
  public int getWidth() {
    return width;
  }
  
  public int getHeight() {
    return height;
  }
  
  public int getNbPoints() {
    return points.size();
  }
  
  public List<Point> getPoints() {
    return new ArrayList<Point>(points);
  }
}
