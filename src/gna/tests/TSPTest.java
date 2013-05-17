package gna.tests;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import gna.*;
import java.util.ArrayList;

public class TSPTest {

  @Test
  public void testNearestNeighbor0() {
    World world = new World(100, 100, new ArrayList<Point>());
    Tour tour = new NearestNeighborTour(world);
    assertTrue(tour.getVisitSequence().isEmpty());
  }
}
