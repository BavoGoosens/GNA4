package gna;

import java.util.List;

/**
 * A tour constructed using the smallest increase heuristic.
 */
public class SmallestIncreaseTour extends IncrementallyConstructedTour {
  
  public SmallestIncreaseTour(World world) {
    super(world);
  }

  @Override
  public void insert(Point point) {
    throw new RuntimeException("not implemented");
  }

  @Override
  public double getTotalDistance() {
	  if (getWorld().getNbPoints() < 2)
			return 0;
		else {
			double total = 0;
			tour = super.getTour();
			Point begin = tour.get(0);
			for (int i = 1; i < tour.size(); i ++){
				Point next = tour.get(i);
				total += begin.distanceTo(next);
				begin = next;			
			}
			total += tour.get(tour.size()-1).distanceTo(tour.get(0));
			return total;
		}
  }

  @Override
  public List<Point> getVisitSequence() {
	  return super.getTour();
  }
}
