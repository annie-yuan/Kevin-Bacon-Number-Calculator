import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class BFSTree {
    private IntGraphList graph;
    private int source;
    private Map<Integer,Integer> distances=new HashMap<>();//vertices to integers
    private Map<Integer,Integer> parents= new HashMap<>();//vertices to vertices


    public BFSTree(IntGraphList graph, int source) {//this is done when the class is called
        this.graph = graph;//constructor set the graph to the parameter passed
        this.source = source;//set the person source(zach)

    	for(int node : graph.getVertices()){//for every vertex
    		distances.put(node,-1);//initialize, set distance node as -1 first
    		parents.put(node,-1);//set parents
    	}

    	Queue<Integer> q = new LinkedList<>();// construct a new linkedList 
    	distances.put(source,0);//set the key as the source and value as 0
    	q.add(source);//put the source person into the linkedList

    	while(!q.isEmpty()){// go through everything in the LinkedList
    		int u=q.remove();//stored the removed data
    		for(int v:graph.getAdjacencyList(u)){//for every vertex in the graph
    			if(distances.get(v)==-1){// the value is only initialized here since i put -1 in it
    				distances.put(v,distances.get(u)+1);//put vertex as the key, and the removed value plus 1 as the value
    				parents.put(v,u);//set the parents of vertex as key, and the removed value
    				q.add(v);//add the vertex to the queue
    			}
    		}
        }
    }

    public int getDistanceTo(int v) {//return the distance to the vertex, which is the value in the map
        // TODO: complete this method
        return distances.get(v);
    }

    public int getParent(int v) {//return the parent map value
        // TODO: complete this method
    	return parents.get(v);
    }

    public IntGraphList getGraph() {// return the graph
        return graph;
    }

    public int getSource() {// get the vertex person
        return source;
    }
    
    public Map<Integer,Integer> getDistancesNumbers() {
        // TODO: complete this method
        Map<Integer, Integer> result = sortMapByValue(distances);//call the method, which will return the sorted map by smallest to biggest value
        Map<Integer,Integer> distancesNumber= new HashMap<>();//initialize the map that we want to return
        for (Map.Entry<Integer, Integer> entry : result.entrySet()) {//for every entry in the sorted map
        	if(distancesNumber.containsKey(entry.getValue())) {//if we have already put the key into the map before
        		distancesNumber.put(entry.getValue(),distancesNumber.get(entry.getValue())+1);//increment the value at the key
    		}else {//the key is not in the map yet
    			distancesNumber.put(entry.getValue(),1);// put the new value into the key set and set as value 1
    		}
    	}
        return  distancesNumber;//return the distance map
    }

    private HashMap<Integer, Integer> sortMapByValue(Map<Integer, Integer> map) {//we want the value of the map sorted instead of the key
        List<Map.Entry<Integer, Integer>> entries = new LinkedList<>(map.entrySet());//convert to linkedList type to compare values
        Collections.sort(entries, new Comparator<Map.Entry<Integer, Integer>>(){//sort the values from the linkedList, not the key
         public int compare(Map.Entry<Integer, Integer>o1,Map.Entry<Integer, Integer>o2) {//similar to the method i used in assignment 3
          return o1.getValue()-o2.getValue(); //use compare method to put the smaller value in the front
         }
        });//syntax for compare need semicolin, this is from the oracle of assignment 3
        LinkedHashMap<Integer, Integer> result = new LinkedHashMap<>();//resultant LinkedHashMap which the values sorted now
        for (Map.Entry<Integer, Integer> entry : entries) {//for all the entry in the entries LinkedList
            result.put(entry.getKey(), entry.getValue());//put each key with their values
        }
        return result;//here is the complete sorted map now
    }
}
