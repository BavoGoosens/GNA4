package gna;

import gna.MinimalSpanningTreeTour.MSTEdge;

import java.util.Comparator;

public class weightComperator implements Comparator<MSTEdge>  {

		@Override
		public int compare(MSTEdge e1, MSTEdge e2) {
			double edgeweight1 = e1.getWeight();
			double edgeweight2 = e2.getWeight();

			if(edgeweight1 > edgeweight2){
				return 1;
			}else if(edgeweight1 < edgeweight2){
				return -1;
			}else{
				return 0;

			}
		}
	}
