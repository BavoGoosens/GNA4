package gna;

/**
 * Tours that are constructed incrementally by inserting the elements in the
 * given world one by one.
 */
public abstract class IncrementallyConstructedTour extends Tour {

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
