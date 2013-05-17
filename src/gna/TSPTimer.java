package gna;

import java.util.ArrayList;
import java.util.List;

/**
 * Time the three heuristics by generating random instances of size N.
 */
public class TSPTimer {

  public static void main(String[] args) {
    int SIZE = 600;
    int N = Integer.parseInt(args[0]);
    long start, stop;
    double elapsed;

    // generate data
    List<Point> points = new ArrayList<Point>(N);
    for (int i = 0; i < N; i++) {
      double x = Math.random() * SIZE;
      double y = Math.random() * SIZE;
      points.add(new Point(x, y));
    }
    World world = new World(SIZE, SIZE, points);

    // run nearest insertion heuristic
    start = System.currentTimeMillis();
    Tour tour1 = new NearestNeighborTour(world);
    stop = System.currentTimeMillis();
    System.out.println("Tour distance = " + tour1.getTotalDistance());
    elapsed = (stop - start) / 1000.0;
    System.out.println("Nearest insertion:  " + elapsed + " seconds");

    // run smallest insertion heuristic
    start = System.currentTimeMillis();
    Tour tour2 = new SmallestIncreaseTour(world);
    stop = System.currentTimeMillis();
    System.out.println("Tour distance = " + tour2.getTotalDistance());
    elapsed = (stop - start) / 1000.0;
    System.out.println("Smallest increase:  " + elapsed + " seconds");

    // run MST insertion heuristic
    start = System.currentTimeMillis();
    Tour tour3 = new MinimalSpanningTreeTour(world);
    stop = System.currentTimeMillis();
    System.out.println("Tour distance = " + tour3.getTotalDistance());
    elapsed = (stop - start) / 1000.0;
    System.out.println("MST insertion:  " + elapsed + "seconds");
  }
}
