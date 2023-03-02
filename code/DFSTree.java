import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class DFSTree {
    private IntGraphList graph;
    private Set<Integer> visited=new HashSet<>();
    private Map<Integer,Integer> parents=new HashMap<>();//this is the parents of the vertex
    private Map<Integer,Integer> components=new HashMap<Integer,Integer>();//number of actors in the component
    private Map<Integer,Integer> componentsSummary=new HashMap<Integer,Integer>();//number of components with the same number of actors
    private Map<Integer, HashSet<Integer>> ComponentsMembers= new HashMap<Integer, HashSet<Integer>>();//all the actor id in the component

    public DFSTree(IntGraphList graph) {//this method is ran when the class is called
        this.graph = graph;
    	int i = 0;
    	int id=0;//initialize actor id
    	for(int v: graph.getVertices()) {//all the vertices in the graph
        	if(!isVisitedComponentsMembers(v)){//check this vertices 
        		dfsVisit(v,visited,parents);
        		if(componentsSummary.containsKey(visited.size())) {
    				componentsSummary.put(visited.size(),componentsSummary.get(visited.size())+1);
    			}else {
    				componentsSummary.put(visited.size(),1);
    			}
    			components.put(id,visited.size());
    			ComponentsMembers.put(id, new HashSet<>(visited));
    			id++;
        	}

        	i++;
            if ( i % 50000 == 0) {
                System.out.printf("DFS Processed %,d records so far...\n", i);
            }
    	}
    }

    private boolean isVisitedComponentsMembers(Integer member) {//if this id has the member already return true
    	for(int id=0;id<ComponentsMembers.size();id++) {
    		if(ComponentsMembers.get(id).contains(member)) {
    			return true;
    		}
    	}
    	return false;
    }

    private void dfsVisit(int u, Set<Integer> visited, Map<Integer,Integer> parents) {//check which component the actor is in
    	Stack<Integer> stack = new Stack<Integer>();// construct a new queue to store the vertices
    	stack.add(u);//u is the vertex passed from the call
    	visited.add(u);
    	while (stack.size() != 0 ){
    		u = stack.pop();
	    	for(int v:graph.getAdjacencyList(u)) {
	    			if(!visited.contains(v)) {
	    				stack.add(u);
	    				stack.add(v);
	    				visited.add(v);
	    				parents.put(v, u);
	    				break;
	    			}
	    	}
    	}
    }


    public IntGraphList getGraph() {//get the dfs graph
        return graph;
    }

    public int getParent(int v) {//return parent of this vertice
    	return parents.get(v);
    }

    public List<Integer> getComponentSizes() {
        return new ArrayList<Integer>(components.values());
    }

    public Map<Integer,Integer> getcomponentsSummary() {
        return  componentsSummary;
    }
}
