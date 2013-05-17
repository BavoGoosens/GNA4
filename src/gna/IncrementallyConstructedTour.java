package gna;

import java.awt.List;
import java.util.ArrayList;

/**
 * Tours that are constructed incrementally by inserting the elements in the
 * given world one by one.
 */
public abstract class IncrementallyConstructedTour extends Tour {

	protected ArrayList<Point> tour = new ArrayList<Point>();

	public ArrayList<Point> getTour() {
		return tour;
	}

	public void setTour(ArrayList<Point> tour) {
		this.tour = tour;
	}

	public IncrementallyConstructedTour(World world) {
		super(world);
		/**
		 * Note: we expect the elements to be inserted in the same order they are added to the world.
		 */
		for (Point point : world.getPoints()) {
			insert(point);
		}
	}

	protected abstract void insert(Point point);
}
