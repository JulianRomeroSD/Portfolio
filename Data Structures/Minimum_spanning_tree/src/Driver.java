import structures.Arc;
import structures.Graph;
import structures.PartialTree;
import structures.Vertex;

import java.io.IOException;
import java.util.ArrayList;

import app.PartialTreeList;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Graph graph = null;
	        try {
	            graph = new Graph("graph2.txt");
	        }
	        catch (IOException e) {
	            e.printStackTrace();
	        }
	        //
	        PartialTreeList k = PartialTreeList.initialize(graph);// partialTreeList = MST.initialize(graph)
			
	        
	        ArrayList<Arc> arcArrayList = PartialTreeList.execute(k); //MST.execute(partialTreeList);
	        
	        for (int i = 0; i < arcArrayList.size(); i++) {
	            Arc anArcArrayList = arcArrayList.get(i);
	            System.out.println(anArcArrayList);
	        }
	    }
		
	}


