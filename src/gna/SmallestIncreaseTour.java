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
		if(super.getTour().isEmpty()){
			super.getTour().add(point);
		}else{
			double beginDis = getTotalDistance();
			//enkel start zit in tour
			if (super.getTour().size() == 1 ){
				super.getTour().add(point);
			}
			// tour >= 2 
			int insertIndex = 1; 
			int beginIndex = 0;
			int nextIndex = 1;
			double minimum = possibleTotalDistance(beginIndex,nextIndex,beginDis,point);
			beginIndex = nextIndex;
			for (int i = 2; i < super.getTour().size(); i ++){
				if (minimum >)
				
			}
		}
	}

	/**
	 * Een methode om op efficiënte wijze na te gaan hoeveel langer de tour wordt door een punt tussen 
	 * de meegegeven indexen te plaatsen.
	 */
	private double possibleTotalDistance(int beginIndex, int nextIndex, double beginDis, Point point) {
		tour = super.getTour();
		double newDistance = beginDis - tour.get(beginIndex).distanceTo(tour.get(nextIndex));
		newDistance += tour.get(beginIndex).distanceTo(point);
		newDistance += point.distanceTo(tour.get(nextIndex));		
		return newDistance;
	}

	@Override
	public double getTotalDistance() {
		if (getWorld().getNbPoints() < 2)
			return 0;
		else {
			double total = 0;
			tour = super.getTour();
			if(tour.size() > 1){
				Point begin = tour.get(0);
				for (int i = 1; i < tour.size(); i ++){
					Point next = tour.get(i);
					total += begin.distanceTo(next);
					begin = next;			
				}
				total += tour.get(tour.size()-1).distanceTo(tour.get(0));
				return total;
			}else{
				// het enige pad dat in de tour zit is de start
				return 0;
			}
		}
	}

	@Override
	public List<Point> getVisitSequence() {
		return super.getTour();
	}
}
