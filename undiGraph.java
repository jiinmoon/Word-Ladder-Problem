/*
 * Author:	Ji-In Moon (Francisco Moon)
 * Date:	Apr.1.2016
 * File:	undiGraph.java
 * Details:	CSC106 Project
 */

/* Implementation of ADT graph - undirected graph */
import java.util.ArrayList;

/**
 * undiGraph is a undirectional graph implementation of Graph interface.
 * It is implemented as adjacency list.
 * Note: it does not check for duplicates in addVert.
 */
public class undiGraph implements Graph 
{	
	/* -- DATA FIELD -- */
	private	ArrayList<Vertex> vArray;
	private int count;

	/**
	 * Default Constructor */
	public undiGraph() 
	{
		this.vArray = new ArrayList<>();
		this.count = 0;
	}	

	/**
	 * Adds new vertex in the graph.
	 * @param Vertex new vertex to be added.
	 * @return true if succesful; false otherwise.
	 */
	public boolean addVert(Vertex newVert) 
	{
		count++;
		return vArray.add(newVert);
	}

	/**
	 * Searches and returns vertex at given index.
	 * @param int index of target vertex.
	 * @return Vertex at given index.
	 */
	public Vertex getVert(int index)
	{
		return vArray.get(index);
	}

	/**
	 * Adds edges between two vertices.
	 * Edges are created iff there already is not an edge between
	 * two vertices.
	 * @param Vertex v1, v2 to be connected by an edge.
	 */
	public void addEdge(Vertex v1, Vertex v2) 
	{		
		/* Seperate the Vertex and Edges. Vertex will keep track
		edges while edges will keep reference to target Vertex */
		if (!checkEdge(v1, v2)) 
		{
			Edge temp = new Edge(v2);
			v1.adj.add(temp);
			temp = new Edge(v1);
			v2.adj.add(temp);
		}	
	}

	/**
	 * Checks and returns T/F whether edge exists between two vertices.
	 * @param Vertex v1, v2 to be checked.
	 * @return boolean true if exists. false, otherwise.
	 */
	public boolean checkEdge(Vertex v1, Vertex v2)
	{
		for(Edge e: v1.adj)
		{
			if (e.target == v2) return true;
		}
		return false;
	}

	/** The size of the list */
	public int size()
	{
		return count;
	}

	/** Clears graph */
	public void removeAll()	
	{ 
		vArray.clear(); 
	}

	/** Checks for empty graph */
	public boolean isEmpty() 
	{ 
		return count == 0; 
	}

	/**
	 * Prints the content of graph in terminal. 
	 * Note: it is not a toString() :: it prints the contents of
	 * the graph directly into the terminal.
	 */
	public void list() 
	{
		System.out.println("\n[GRAPH]\n");
		Vertex temp;
		for(int i = 0; i < vArray.size(); i++)
		{
			temp = vArray.get(i);
			System.out.print("[Vertex " +i+ ": " + temp.word +"]" 
						+ "\tEdges : ");
				for(Edge e : temp.adj) 
				{
					Vertex temp2 = e.target;
					System.out.print(temp2.word + " ");
				} // end for
			System.out.println();
		} // end for
	}

	/**
	 * Testing only.
	 * @param args not used.
	 */
	public static void main(String[] args) 
	{
		Vertex v1 = new Vertex("Hello");
		Vertex v2 = new Vertex("World");
		Vertex v3 = new Vertex("Surprised???");
		Vertex v4 = new Vertex("I am :D");
		Graph g = new undiGraph();
		g.addVert(v1);
		g.addVert(v2);
		g.addVert(v3);
		g.addVert(v4);
		g.addEdge(v1, v2);
		g.addEdge(v1, v3);
		g.addEdge(v3, v4);
		g.list();	
		System.out.println(g.checkEdge(v1, v4));
	}
}
