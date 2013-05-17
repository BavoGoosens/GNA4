package gna;

import java.util.List;

/**
 * A tour constructed using the nearest neighbor heuristic.
 */
public class NearestNeighborTour extends IncrementallyConstructedTour {
  
  public NearestNeighborTour(World world) {
    super(world);
  }
  
  @Override
  public void insert(Point point) {
	  // shizzles
  }

  @Override
  public double getTotalDistance() {
    throw new RuntimeException("not implemented");
  }

  @Override
  public List<Point> getVisitSequence() {
    throw new RuntimeException("not implemented");
  }
}
