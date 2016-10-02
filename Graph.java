/*
 * Author:	Ji-In Moon (Francisco Moon)
 * Date:	Apr.1.2016
 * File:	Graph.java
 * Details:	CSC106 Project
 */

/* ADT Graph */

/* A lot of functionality is currently stripped as our main purpose is
to find the shortest route between two words. Not manipulate the graph
to see what happens. So we do not care about removing vertex or edges. */

/** ADT Graph */
public interface Graph {
	
	/** Adds new vertex in the graph */
	public boolean addVert(Vertex vert);

	/** Create an edge between two vertices */
	public void addEdge(Vertex v1, Vertex v2);
	
	/** Checks whether there is an edge between two vertices */
	public boolean checkEdge(Vertex v1, Vertex v2);

	/** Return stored vertex at given index */
	public Vertex getVert(int index);

	/** Empties the graph */
	public void removeAll();

	/** Checks for empty Graph */
	public boolean isEmpty();

	/** Returns the number of vertex in graph */
	public int size();

	/** Lists the Vertices and edges in text terminal */
	public void list();
}
