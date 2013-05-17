package gna;

import java.util.List;

/**
 * A tour constructed using the minimal spanning tree heuristic.
 */
public class MinimalSpanningTreeTour extends Tour {
  
  public class MSTEdge {
    public final Point p1, p2;
    
    public MSTEdge(Point p1, Point p2) {
      this.p1 = p1;
      this.p2 = p2;
    }
  }
  
  public MinimalSpanningTreeTour(World world) {
    super(world);
    // compute route here
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
    throw new RuntimeException("not implemented");
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
