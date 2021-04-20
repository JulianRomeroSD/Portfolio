package app;
import structures.MinHeap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import structures.Arc;
import structures.Graph;
import structures.PartialTree;
import structures.Vertex;

/**
 * Stores partial trees in a circular linked list
 * 
 */
public class PartialTreeList implements Iterable<PartialTree> {
    
	/**
	 * Inner class - to build the partial tree circular linked list 
	 * 
	 */
	public static class Node {
		/**
		 * Partial tree
		 */
		public PartialTree tree;
		
		/**
		 * Next node in linked list
		 */
		public Node next;
		
		/**
		 * Initializes this node by setting the tree part to the given tree,
		 * and setting next part to null
		 * 
		 * @param tree Partial tree
		 */
		public Node(PartialTree tree) {
			this.tree = tree;
			next = null;
		}
	}

	/**
	 * Pointer to last node of the circular linked list
	 */
	private Node rear;
	
	/**
	 * Number of nodes in the CLL
	 */
	private int size;
	
	/**
	 * Initializes this list to empty
	 */
    public PartialTreeList() {
    	rear = null;
    	size = 0;
    }

    /**
     * Adds a new tree to the end of the list
     * 
     * @param tree Tree to be added to the end of the list
     */
    public void append(PartialTree tree) {
    	Node ptr = new Node(tree);
    	if (rear == null) {
    		ptr.next = ptr;
    	} else {
    		ptr.next = rear.next;
    		rear.next = ptr;
    	}
    	rear = ptr;
    	size++;
    }

        /**
		 * Initializes the algorithm by building single-vertex partial trees
		 * 
		 * @param graph Graph for which the MST is to be found
		 * @return The initial partial tree list
		 */
		public static PartialTreeList initialize(Graph graph) {
		
			/* COMPLETE THIS METHOD */
			PartialTreeList L = new PartialTreeList();
			
			
			for(int i =0; i<graph.vertices.length;i++) {
			Vertex v = graph.vertices[i];
			
			
			PartialTree T = new PartialTree(v);
			
			while(v.neighbors !=null) {
			Arc first = new Arc(v,v.neighbors.vertex,v.neighbors.weight);
	//		System.out.println("arc to be inserted: " + first);
			T.getArcs().insert(first);
	
			//System.out.println(T.getArcs());
			v.neighbors = v.neighbors.next;
			}
			ArrayList<Arc> temp = new ArrayList<Arc>();
			while(!T.getArcs().isEmpty()) {
			temp.add(T.getArcs().deleteMin());
			}
	for(int p = 0; p < temp.size();p++) {
		T.getArcs().insert(temp.get(p));
	}
			System.out.println("final heap" + T);
			L.append(T);
			//System.out.println(T);
		}
			System.out.println("rear tree: " + L.rear.tree);
		
			
			
			return L;
		}
	
	
	/**
	 * Executes the algorithm on a graph, starting with the initial partial tree list
	 * for that graph
	 * 
	 * @param ptlist Initial partial tree list
	 * @return Array list of all arcs that are in the MST - sequence of arcs is irrelevant
	 */
	public static ArrayList<Arc> execute(PartialTreeList ptlist) {
		
		/* COMPLETE THIS METHOD */
		ArrayList<Arc> MST = new ArrayList<Arc>();
		ArrayList<PartialTree> finalptlist = new ArrayList<PartialTree>();
	
		System.out.println(ptlist.rear.tree);
		 Iterator<PartialTree> iter = ptlist.iterator();
		   while (iter.hasNext()) {
		       PartialTree pt = iter.next();
		       finalptlist.add(pt);
		   }
		   System.out.println("element" + finalptlist.get(0));
		   System.out.println("element" +finalptlist.get(1));
		   System.out.println("element" +finalptlist.get(2));
		   System.out.println("element" +finalptlist.get(3));
//		   System.out.println("element" +finalptlist.get(4));
			
			
				

		System.out.println("size of finalptlist " + finalptlist.size());
		
		PartialTree PTY = null;
		MinHeap<Arc>PQY = null;
		PartialTree PTX = null;
		MinHeap<Arc>PQX = null; 
		int k = 1;
		
		
		while(finalptlist.size() > 1) {
		System.out.println(finalptlist.size() + " trees left");
		System.out.println("step 3 remove first partial tree:");
		PTX = finalptlist.remove(0);
		System.out.println(k + ":" + " Partial tree's " + PTX);
		PQX = PTX.getArcs();
		System.out.println("step 4 pick highest priority queue:");
		Arc alpha =PQX.deleteMin();
//		System.out.println("Alpha: " + alpha);
		Vertex v1 = alpha.getv1();
		Vertex v2 = alpha.getv2();
		System.out.println("Vertex 1: " + v1 + "  Vertex 2: " + v2);
		System.out.println("Vertex1 root: " + v1.getRoot().name + "  Vertex2 root: " + v2.getRoot().name);
		while(v2.getRoot().name.contains(PTX.getRoot().name)) {
			System.out.println("step 5 get next highest priority queue:");
			System.out.println("V2 belongs to PTX Same root");
			System.out.println("Vertex1 root: " + v1.getRoot() + "  Vertex2 root: " + v2.getRoot());
			alpha = PQX.deleteMin();
			v1 = alpha.getv1();
			v2 = alpha.getv2();
			}
		System.out.println("step 6 report alpha:");
			MST.add(alpha);
			System.out.println("Arraylist of alphas" + MST);
	
			int b = 0;
			System.out.println("size of list: " + finalptlist.size());
//			System.out.println("element" + finalptlist.get(0));
//			System.out.println("element" + finalptlist.get(1));
		
			while(b!=finalptlist.size()) {
				
				System.out.println("step 7: find partial tree PTY");
				PartialTree temp = finalptlist.get(b);
				
				System.out.println("iterator element of tree "+ b +": " + temp); 
				System.out.println("Vertex2 partial tree root: " + v2.getRoot() + "  temp partial tree root: " + temp.getRoot());
				
				if(v2.getRoot().name.contains(temp.getRoot().name)) {
					System.out.println("same tree");
					PTY = temp;		
					System.out.println("second partial tree's " + PTY);
					PQY = temp.getArcs();
					finalptlist.remove(b);
					System.out.println("new arrray list " + finalptlist);
					System.out.println("Arcs of second partial tree: " + PQY);
					break;
				}
				b++;	
			}
			
			k++;
			System.out.println("PTX " + PTX);
			System.out.println("PTY " + PTY);	
			if(PTY != null) {
				System.out.println("PTY in which v2 belongs");
			PTX.merge(PTY);
			}
			finalptlist.add(PTX);
			System.out.println("new arrray with merged list " + finalptlist);
			System.out.println("Merged tree " + PTX);
		//System.out.println("Merged Arcs: "+PQX);
		}
		
		System.out.println("Final tree " + PTX);
		//System.out.println("Final Arcs: "+PQX);
		return MST;
	}
	
    /**
     * Removes the tree that is at the front of the list.
     * 
     * @return The tree that is removed from the front
     * @throws NoSuchElementException If the list is empty
     */
    public PartialTree remove() 
    throws NoSuchElementException {
    			
    	if (rear == null) {
    		throw new NoSuchElementException("list is empty");
    	}
    	PartialTree ret = rear.next.tree;
    	if (rear.next == rear) {
    		rear = null;
    	} else {
    		rear.next = rear.next.next;
    	}
    	size--;
    	return ret;
    		
    }

    /**
	 * Removes the tree in this list that contains a given vertex.
	 * 
	 * @param vertex Vertex whose tree is to be removed
	 * @return The tree that is removed
	 * @throws NoSuchElementException If there is no matching tree
	 */
	public PartialTree removeTreeContaining(Vertex vertex) 
	throws NoSuchElementException {
			/* COMPLETE THIS METHOD */
		PartialTree treeRemoved = null;
		if(rear == null) {
			throw new NoSuchElementException("no matching tree");
		}
		if(size() == 1) {
			if(vertex.getRoot().equals(rear.tree.getRoot())) {
			PartialTree temp = rear.tree;
			rear = null;
			size--;
			return temp;
			}
			else {
				throw new NoSuchElementException("no matching tree");
			}
		}
			
			Node prev = rear;
			Node ptr = rear.next;
			while(ptr!=rear) {
			if(vertex.getRoot().name.contains(prev.tree.getRoot().name)){
				System.out.println("prev: " + prev.tree + "ptr: " + ptr.tree);
				treeRemoved = prev.tree;
				break;
			}
			if(vertex.getRoot().name.contains(ptr.tree.getRoot().name)) {
				System.out.println("prev: " + prev.tree + "ptr: " + ptr.tree);
				System.out.println("tree to delete found");
				treeRemoved = ptr.tree;
				prev.next = ptr.next;
				size--;
				break;
			}
			ptr = ptr.next;
			prev = prev.next;
		}
		

	
	
	
		if(treeRemoved==null) {
			throw new NoSuchElementException("no matching tree");
		}
		
		
		System.out.println("Tree removed " + treeRemoved);
		return treeRemoved;
	 }
    
    /**
     * Gives the number of trees in this list
     * 
     * @return Number of trees
     */
    public int size() {
    	return size;
    }
    
    /**
     * Returns an Iterator that can be used to step through the trees in this list.
     * The iterator does NOT support remove.
     * 
     * @return Iterator for this list
     */
    public Iterator<PartialTree> iterator() {
    	return new PartialTreeListIterator(this);
    }
    
    private class PartialTreeListIterator implements Iterator<PartialTree> {
    	
    	private PartialTreeList.Node ptr;
    	private int rest;
    	
    	public PartialTreeListIterator(PartialTreeList target) {
    		rest = target.size;
    		ptr = rest > 0 ? target.rear.next : null;
    	}
    	
    	public PartialTree next() 
    	throws NoSuchElementException {
    		if (rest <= 0) {
    			throw new NoSuchElementException();
    		}
    		PartialTree ret = ptr.tree;
    		ptr = ptr.next;
    		rest--;
    		return ret;
    	}
    	
    	public boolean hasNext() {
    		return rest != 0;
    	}
    	
    	public void remove() 
    	throws UnsupportedOperationException {
    		throw new UnsupportedOperationException();
    	}
    	
    }
}


