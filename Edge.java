/*
 * Author:	Ji-In Moon (Francisco Moon)
 * Date:	Apr.1.2016
 * File:	Edge.java
 * Detail:	CSC106 Project
 */

/* Indicates other Vertex that this Edge object is contained has connection with */

/** Edge of a graph */
public class Edge {

	/* Points to target Vertex */
	public final Vertex target;

	/** 
	 * Default Constructor 
	 * @param Vertex target vertex to form an edge with.
	 */
	public Edge(Vertex target) 
	{
		this.target = target;
	}
}
