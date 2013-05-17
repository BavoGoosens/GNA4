package gna;

import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;

public class SmallestIncreaseSolver {
  public static void main(String[] args) throws IOException {
    World world;
    if(args.length == 0) {
      world = new World(System.in);
    } else {
      world = new World(args[0]);
    }
    Tour tour = new SmallestIncreaseTour(world);
    System.out.println("Tour Distance = " + tour.getTotalDistance());
    System.out.print(tour);
    JFrame frame = new JFrame("TSP (smallest increase heuristic)");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    TourView view = new TourView(tour);
    view.setPreferredSize(new Dimension(world.getWidth(), world.getHeight()));
    frame.add(view);
    frame.pack();
    frame.setVisible(true);
  }
}
