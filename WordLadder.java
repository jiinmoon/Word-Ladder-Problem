/*
 * Author:	Ji-In Moon (Francisco Moon)
 * Date:	Apr.1.2016
 * File:	WordLadder.java
 * Details:	CSC106 Project
 */

/* WORD LADDER: FIND THE SHORTEST ROUTE */

/*
 * In the findRoute() method, BFS algorithmn is credited to:
	https://en.wikipedia.org/wiki/Breadth-first_search#Pseudocode 
 */

import java.util.*;
import java.io.*;

/**
 * WordLadder is a program that finds the shortest route in the word ladder.
 * It reads the given text-file and generates a graph - which it will then
 * perform BFS to find shortest path from two words.
 */
public class WordLadder 
{
	/* -- DATA FIELD -- */
	static private Graph G;
	private List<String> shortRoute;

	/** 
	 * Default Constructor
	 * Creates a graph with given array of words.
	 * @param String[] array of words to form a graph with.
	 * @throws Exception is thrown when cannot convert to Graph.
		Most likely a wrong format input. 
	 */
	public WordLadder(String[] words) throws Exception
	{
		this.G = new undiGraph();
		createGraph(G, words);
		createEdges(G);
	}

	/* Stores each words into Graph as Vertices */

	/**
	 * Stores the words into array to form outline of graph (vertices but no edges).
	 * @param Graph the graph object.
	 * @param words words to form vertices with.
	 * @return Graph graph without edges.
	 * @throws Exception if duplicate word is detected in given input.
	 */
	private Graph createGraph(Graph G, String[] words) throws Exception
	{
		/* Check duplicate. */
		for(String w : words)
		{
			if(findWord(w) != null) throw new 
				RuntimeException("Duplicate word detected. Check your file.");
			G.addVert(new Vertex(w));
		}
		return G;
	}

	/**
	 * Creates an edges between vertices.
	 * Edges are created iff two words differ by a single character without rearrangement.
	 * @param Graph graph to connect vertices with edges.
	 */
	private Graph createEdges(Graph G)
	{
		Vertex temp1;
		Vertex temp2;
		for(int i=0; i<G.size(); i++)
		{
			temp1 = G.getVert(i);
			for(int k=i+1; k<G.size(); k++)
			{
				temp2 = G.getVert(k);
				if(compareWords(temp1.word, temp2.word))
				{
					G.addEdge(temp1, temp2);
				}
			}
		}
		return G;
	}

	/** 
	 * Compare words to see whether two words differ
	 * by exactly single character 
	 * @param String two words to compare.
	 * @return true if so. false otherwise.
	 */
	private boolean compareWords(String w1, String w2)
	{
		char[] word1 = w1.toCharArray();
		char[] word2 = w2.toCharArray();
		int count = 0;

		for(int i = 0; i<word1.length; i++)
		{
			if(word1[i] != word2[i]) count++;
		}
		return count == 1;
	}

	/**
	 * Searches graph to see whether a given word is in the graph.
	 * @param String word to search for in graph
	 * @return Vertex the vertex containing the word. Null, if not found.
	 */
	private static Vertex findWord(String word)
	{
		Vertex vert;
		for(int i=0; i<G.size(); i++)
		{
			vert = G.getVert(i);
			if (vert.word.equals(word)) return vert;
		}
		return null;
	}

	/**
	 * Performs BFS & Makes a list of shortest route. 
	 * @param String w1, w2 which are two words to find shorest route from-to.
	 * @return List<String> List of vertices/words path between two input.
	 * @throws Exception if BFS cannot be completed.
	 */
	private static List<String> findRoute(String w1, String w2) throws Exception
	{
		List<String> list = new ArrayList<>();
		Queue<Vertex> vq = new LinkedList<>();

		/* initialize for different parameter call */
		Vertex iniTempV;
		for(int i = 0; i<G.size(); i++) {
			iniTempV = G.getVert(i);
			iniTempV.distance = -1;
			iniTempV.parent = null;
		}

		/* find and prep starting vertex */
		Vertex startV = findWord(w1);
		startV.distance = 0;
		vq.add(startV);

		/* BFS */
		Vertex temp1;
		Vertex temp2;
		while (!vq.isEmpty())
		{
			temp1 = vq.remove();
			for (Edge e : temp1.adj)
			{
				temp2 = e.target;
				if(temp2.distance == -1)
				{
					temp2.distance = temp1.distance +1;
					temp2.parent = temp1;
					vq.add(temp2);
				}
			}
		}

		/* find the word destination and follow parent path up tree */
		Vertex lastV = findWord(w2);
		do
		{
			list.add(0, lastV.word);
			lastV = lastV.parent;
		} while (lastV != null);
		return list;
	}

	/**
	 * Tokenizes given string of inputs into an array.
	 * @param String one string of inputs.
	 * @return String[] array of words.
	 */
	private static String[] tokenize(String s)
	{
		String[] contents = s.split(" ");
		return contents;

	}

	/** 
	 * Main client
	 * Provides REPL terminal.
	 * @param arg not used.
	 * @throws Exception FileIO, WrongFormat, NullPointer.
	 */
	public static void main (String[] arg) throws Exception 
	{

		System.out.println("\n[Word Ladder: Shortest Route Finder]");
		
		String content = "";	
		
		try (Scanner s = new Scanner(new File("wordladder.txt")))
		{
				content = s.nextLine();
		}
		catch (FileNotFoundException e)
		{
			System.out.println(
				"Check your file name. It should be wordladder.txt");
			return;
		}

		String[] contents = tokenize(content);
		try
		{
			WordLadder wordladder = new WordLadder(contents);
			G.list();
		}
		catch (ArrayIndexOutOfBoundsException e1)
		{
			System.out.println("File format incorrect. Check words in file.");
			return;
		}

		System.out.println("\nEnter two words to find shortest route from-to:");

		Scanner ui = new Scanner(System.in);
		
		do
		{
			System.out.print("WLadder>");
			String w1 = ui.next();
			String w2 = ui.next();
			try
			{
				System.out.println("\nThe shortest route is:");
				System.out.println("\t" + findRoute(w1, w2).toString());
				System.out.println(
		"\nEnter another words to find shortest route, or CRTL+C to terminate.");
				System.out.print("WLadder>");
			}
			catch (NullPointerException ee)
			{
				System.out.println("\tWrong inputs, or route does not exist.");
				System.out.println("\nEnter correct two words to find route, or CRTL+C to terminate.");
				System.out.print("WLadder>");
			}
		} while (ui.hasNext());
	}
}
