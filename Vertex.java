/*
 * Author:	Ji-In Moon (Francisco Moon)
 * Date:	Apr.1.2016
 * File:	Vertex.java
 * Detail:	CSC106 Project
 */

/* Elementary building block of undiGraph along with Edge. */

/* 
 * The idea of seperating Nodes into two objects, Vertex and Edges,
 * is credited to :
	http://en.literateprograms.org/index.php?title=Special%3aDownloadCode/
		Dijkstra%27s_algorithm_%28Java%29&oldid=15444
 */
import java.util.ArrayList;

/** Vertex of a graph. */
public class Vertex 
{
	/* --- DATA FIELD --- */
	protected final String word;
	protected ArrayList<Edge> adj;
	protected Vertex parent;
	protected int distance;

	/**
	 * Default Constructor
	 * @param String word to represent the vertex with.
	 */
	public Vertex (String word) 
	{
		this.word = word;
		this.adj = new ArrayList<>();
		this.distance = -1;
		this.parent = null;
	}
}
