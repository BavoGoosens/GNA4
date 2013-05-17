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
			int idxCount = 0;
			double minimum = point.distanceTo(super.getTour().get(0));
			for(Point punt: super.getTour()){
				if(punt.distanceTo(point) < minimum ){
					minimum = punt.distanceTo(point);
					index = idxCount;
				}
				idxCount++;
			}
			// het nieuwe punt moet na de dichtsbijzijnde node worden ingevoegd
			super.getTour().add(index + 1, point);
		}
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
