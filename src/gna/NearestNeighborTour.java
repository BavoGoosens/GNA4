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
		// ieder punt toevoegen bij zijn dichtsbijzijnde buur
		if(super.getTour().isEmpty()){
			super.getTour().add(point);
		}else{	  
			int index = 0;
			double minimum = point.distanceTo(super.getTour().get(0));
			for(Point punt: super.getTour()){
				if
			}
		}
	}

	@Override
	public double getTotalDistance() {
		if (getWorld().getNbPoints() < 2)
			return 0;
		else {

		}
	}

	@Override
	public List<Point> getVisitSequence() {

	}
}
