package gna;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

/**
 * A tour constructed using the minimal spanning tree heuristic.
 */
public class MinimalSpanningTreeTour extends Tour {
  
  public class MSTEdge {
    public final Point p1, p2;
    
    private double weight = this.p1.distanceTo(this.p2);
    
    public MSTEdge(Point p1, Point p2) {
      this.p1 = p1;
      this.p2 = p2;
    }
    
    public double getWeight(){
    	return this.weight;
    }

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		MSTEdge other = (MSTEdge) obj;
		if (this.getWeight() != other.getWeight())
			return false;
		if (comparePoints(this.p1, other.p1) && comparePoints(this.p2, other.p2))
			return true;
		if (comparePoints(this.p1, other.p2) && comparePoints(this.p2, other.p1))
			return true;
		if (comparePoints(other.p1, this.p1) && comparePoints(other.p2, this.p2))
			return true;
		if (comparePoints(other.p1, this.p2) && comparePoints(other.p2, this.p1))
			return true;
		return false;
	}
	
	/** 
	 * methode om 2 punten met elkaar te vergelijken op gelijkheid
	 * Als de punten gelijk zijn geeft de methode true terug
	 * in het andere geval false
	 */
	public boolean comparePoints(Point p1, Point p2){
		if (p1.getX() != p2.getX())
			return false;
		if (p1.getY() != p2.getY())
			return false;
		if (p2.getX() != p1.getX())
			return false;
		if (p2.getY() != p1.getY())
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.p1.toString() + "---" + this.getWeight() + "---" + p2.toString();
	}
    
  }
  
  public MinimalSpanningTreeTour(World world) {
    super(world);
    // compute route here
    HashMap<double[], Point> vertecesInTree = new HashMap<>();
    TreeSet<MSTEdge> possibleEdges = new TreeSet<>(new weightComperator());
    
    ArrayList<Point> punten = (ArrayList<Point>) world.getPoints();
    // alle mogelijke edges aanmaken
    MSTEdge dummy = new MSTEdge(new Point(0, 0), new Point(0, 0));
    for( Point punt: punten){
    	for (Point anderpunt : punten){
    		if (!dummy.comparePoints(punt, anderpunt)){
    			MSTEdge possible = new MSTEdge(punt, anderpunt);
    			// gaat dubbele edges tegen
    			if (!possibleEdges.contains(possible))
    				possibleEdges.add(possible);
    		}
    	}
    }
    
    
  }

  @Override
  public double getTotalDistance() {
    throw new RuntimeException("not implemented");
  }
  
  /**
   * Return the root of the MST used to construct the visit sequence.
   * 
   * This method returns null if and only if <code>getWorld().getPoints()</code> is empty.
   */
  public Point getMSTRoot() {
	  if(getWorld().getPoints().isEmpty())
		  return null;
	  else {
		  // geef de wortel van de boom terug
		  
	  }
  }
  
  /**
   * Return the edges on the MST used to construct the visit sequence.
   * 
   * The result of this method is never null.
   */
  public List<MSTEdge> getMST() {
    throw new RuntimeException("not implemented");
  }

  @Override
  /**
   * The visit sequence is a PRE-ORDER traversal of the MST
   * starting at a root (e.g. the first point of the world).
   *
   * Traverse the children of each node in increasing order of distance.
   * 
   * Return the empty list if world is empty.
   */
  public List<Point> getVisitSequence() {
    throw new RuntimeException("not implemented");
  }
}
