//Annie Yuan
//Dec 10th
//this assignment process the data from adj.txt and gives the analyzed data of the actor file
//input adj file with source actors and vertices
// output the quartile mean distance components
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Scanner;

public class Program {
    /**
     * This is the main method of your program.
     * 
     * @param args Command-line arguments
     * @throws IOException If reading from your file had an
     *                     error.
     */
    public static void main(String[] args)
            throws IOException {
        /* TODO: change this source to your actor ID */
        int source = 103785; 

        /*
         * TODO: create several sample adjacency lists in
         * text files yourself to test your code before
         * running it on the large Kevin Bacon dataset
         */

        Scanner scanner = new Scanner(new File("data/adj.txt"));      
       // Scanner scanner = new Scanner(new File("data/small-adj.txt"));// smaller version i created because the adj is too big
        IntGraphList graph = IntGraphList.read(scanner,50000);//use the read method provided, show every 50000 data read
        scanner.close();

        /*
         * TODO: you can also create custom graphs within
         * the code as follows, but it is probably easier to
         * just use your own test file.
         *
         * Example of creating graphs in code, rather than
         * from a file:
         */
        // Graph graph = new Graph();
        //
        // graph.addNode(1);
        // graph.addNode(2);
        // graph.addNode(3);
        // graph.addNode(4);
        //
        // graph.addEdge(1, 2);
        // graph.addEdge(1, 3);
        // graph.addEdge(1, 4);
        // graph.addEdge(2, 1);
        // graph.addEdge(3, 1);
        // graph.addEdge(3, 4);
        // graph.addEdge(4, 1);
        // graph.addEdge(4, 3);

    	System.out.println("");
      //  DFSTree dfsTree = new DFSTree(graph);//call the dfs tree class
    //   printComponentReport(dfsTree);//print out the report

    	System.out.println("");
        BFSTree bfsTree = new BFSTree(graph, source);//call the bfs method, and put the first person in as source
        printPathReport(bfsTree);//print data report

    	System.out.println("");
        System.out.println("Source,Fraction,Mean Distance");
        var allSources = new ArrayList<>(graph.getVertices());
        Collections.shuffle(allSources);
        int min = Math.min(allSources.size(), 1000);//only need values smaller than 1000
        for (int s : allSources.subList(0,min)) {
            if (bfsTree.getDistanceTo(s) >= 1) {
                printSimplePathReport(new BFSTree(graph, s));
            }
        }
    }

    public static void printComponentReport(DFSTree dfsTree) {// this method Report on dfs components and answer the questions of component size and number of size
    	Statistics statics=new Statistics(dfsTree.getComponentSizes());//use the statistics class to get the component size
    	System.out.println("*************** DFS Tree ***************");// this is the dfs tree results
    	System.out.println("Connected Components: "+dfsTree.getComponentSizes().size());//dfs class to get the size of the component size
    	System.out.println("Component Size Minimum: "+statics.getMin());//statistics class method
    	System.out.println("Component Size Maximum: "+statics.getMax());
    	System.out.println("Component Size Mean: "+statics.getMean());
    	System.out.println("Component Size Q1: "+statics.getQ1());
    	System.out.println("Component Size Q2: "+statics.getQ2());
    	System.out.println("Component Size Q3: "+statics.getQ3());

    	System.out.println("");
    	System.out.println("Table of Component Sizes:");
    	System.out.println("| Size of Component | Number (count) of components of this size |");
    	System.out.println("| --- | --- |");
    	for (Map.Entry<Integer, Integer> entry : dfsTree.getcomponentsSummary().entrySet()) {//for every entry using dfs tree, 
    		//get the number of components with the same number of actors
    	    System.out.println("|" + entry.getKey() + "|" + entry.getValue() + "|");
    	}
    //	System.out.println("| etc. one row for each unique size. Do not include sizes that have a count of zero. | XYZ |");
    }

    public static void printPathReport(BFSTree bfsTree) {//this is the bfs report, distance and number of actors in the group
        // Report on shortest path from source
        // Report on components
    	int reach = 0;
    	int all = 0;
    	System.out.println("*************** BFS Tree ***************");
    	System.out.println("| Distance from source | Number of Actors |");
    	System.out.println("| ---- | ---- |");
    	for (Map.Entry<Integer, Integer> entry : bfsTree.getDistancesNumbers().entrySet()) {
    	    System.out.println("|" + entry.getKey() +"|" + entry.getValue() +"|");
    	    if (entry.getKey() > 0) {
    	    	reach += entry.getValue();
    	    }
    	    all += entry.getValue();
    	}
    	all--;
    //	System.out.println("| etc. up to the maximum distance | etc. |");

    	System.out.println("");
    	Statistics statics=new Statistics(new ArrayList<>(bfsTree.getDistancesNumbers().keySet()));
    	//use the statistic calss,bfs distance key array
    	System.out.println("Shortest Distance Fraction: "+(double)reach/all);
    	System.out.println("Shortest Distance Minimum: "+statics.getMin());
    	System.out.println("Shortest Distance Maximum: "+statics.getMax());
    	System.out.println("Shortest Distance Mean: "+statics.getMean());
    	System.out.println("Shortest Distance Q1: "+statics.getQ1());
    	System.out.println("Shortest Distance Q2: "+statics.getQ2());
    	System.out.println("Shortest Distance Q3: "+statics.getQ3());
    }

    public static void printSimplePathReport(BFSTree bfsTree) {//to get the bfs fractions
        // Report on shortest path from source
    	int reach = 0;//initialize numerator
    	int all = 0;//initialize denominator
    	for (Map.Entry<Integer, Integer> entry : bfsTree.getDistancesNumbers().entrySet()) {//use getDistancesNumber method from bfs tree
    	    if (entry.getKey() > 0) {//check for positive distanceNumber in the map
    	    	reach += entry.getValue();//each reachable actors
    	    }
    	    all += entry.getValue();//sum up to the total value
    	}
    	all--;

    	Statistics statics=new Statistics(new ArrayList<>(bfsTree.getDistancesNumbers().keySet()));
    	System.out.printf("%d, %,f, %,f\n",bfsTree.getSource(), (double)reach/all, statics.getMean());
    }
}