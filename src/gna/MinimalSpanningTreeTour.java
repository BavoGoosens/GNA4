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

		private double weight = 0;

		public MSTEdge(Point p1, Point p2) {
			this.p1 = p1;
			this.p2 = p2;
			this.weight = p1.distanceTo(p2);
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

		public Point either() {
			return this.p1;
		}

		public Point other() {
			return this.p2;
		}

	}

	public MinimalSpanningTreeTour(World world) {
		super(world);
		// compute route here
		HashMap<String, Point> vertecesInTree = new HashMap<>();
		TreeSet<MSTEdge> possibleEdges = new TreeSet<>(new weightComperator());

		ArrayList<Point> punten = (ArrayList<Point>) world.getPoints();
		this.root = punten.get(0);
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

		// kruskal 
		ArrayList<ArrayList<Point>> union = new ArrayList<>();
		while(!possibleEdges.isEmpty() && MST.size() < world.getNbPoints() - 1){
			MSTEdge candidate = possibleEdges.pollFirst();
			Point p1 = candidate.either();
			Point p2 = candidate.other();
			// geeft aan in welke disjuncte unie P1 behoort
			int p1union = -1;
			// geeft geeft aan in welke disjuncte unie P2 behoort
			int p2union = -1;
			// als P1 en P2 in dezelfde set zitten wordt dit true
			boolean cycle = false;
			int idx = 0;
			for ( ArrayList<Point> un : union){
				if (un.contains(p1))
					p1union = idx;
				if (un.contains(p2))
					p2union = idx;
				if (p1union == p2union){
					cycle = true;
					// mogen stoppen wanneer we zeker zijn dat er een cycle in de graaf gaat ontstaan
					break;
				}
				idx++;
			}
			// P1 zit in een set maar P2 niet => voeg P2 toe aan de set waar P1 in zit => voeg edge toe aan mst
			if (p1union != -1 && p2union == -1){
				union.get(p1union).add(p2);
				this.MST.add(candidate);
			// P2 zit in een set maar P1 niet => voeg P1 toe aan de set waar P2 in zit => voeg edge toe aan mst
			}else if (p1union == -1 && p2union != -1){
				union.get(p2union).add(p1);
				this.MST.add(candidate);
			// in het geval dat zowel P1 en P2 niet in een set zitten => maak nieuwe set en voeg P1 en P2 toe => voeg edge toe aan mst
			}else if (p1union == -1 && p2union == -1){
				ArrayList <Point> newSet = new ArrayList<Point>();
				newSet.add(p1);
				newSet.add(p2);
				union.add(newSet);
				this.MST.add(candidate);
			// P1 zit in een set en P2 zit ook in een andere set => merge de sets => voeg edge toe aan mst
			}else if (p1union != -1 && p2union != -1 && cycle == false){
				if (p1union < p2union){
					union.get(p1union).addAll(union.get(p2union));
					union.remove(p2union);
					this.MST.add(candidate);
				}else{
					union.get(p2union).addAll(union.get(p1union));
					union.remove(p1union);
					this.MST.add(candidate);
				}
			}
		}

	}

	private Point root;

	private ArrayList<MSTEdge> MST = new ArrayList<MSTEdge>();

	private ArrayList<Point> tour = new ArrayList<Point>();

	@Override
	public double getTotalDistance() {
		if (getWorld().getNbPoints() < 2)
			return 0;
		else {
			double total = 0;
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

	/**
	 * Return the root of the MST used to construct the visit sequence.
	 * 
	 * This method returns null if and only if <code>getWorld().getPoints()</code> is empty.
	 */
	public Point getMSTRoot() {
		if(getWorld().getPoints().isEmpty())
			return null;
		else {
			return this.root;
		}
	}

	/**
	 * Return the edges on the MST used to construct the visit sequence.
	 * 
	 * The result of this method is never null.
	 */
	public List<MSTEdge> getMST() {
		return this.MST;
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
		return traverseTree(this.root,this.tour);
		
	}
	
	private ArrayList<Point> traverseTree(Point punt, ArrayList<Point> tour){

		tour.add(punt);

		for(MSTEdge edge : getMST()){
			Point parent = edge.either();
			Point child = edge.other();
			if( parent.equals(punt) && !tour.contains(child)){		// for each child not already in tour
				tour = traverseTree(child,tour);
			}
		}
		return tour;
	}
}
