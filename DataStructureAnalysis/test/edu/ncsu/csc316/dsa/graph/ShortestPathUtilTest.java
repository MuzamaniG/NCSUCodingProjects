package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc316.dsa.Weighted;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * Test class for ShortestPathUtil
 * Checks the expected outputs of Dijksra's algorithm
 * and the shortest path tree construction method
 *
 * @author Dr. King
 * @author // Your Name Here 
 *
 */
public class ShortestPathUtilTest {

    /**
     * Test the output of Dijkstra's algorithm
     */ 
    @Test
    public void testDijkstra() {
        // Create a graph
        Graph<String, WeightedEdge> graph = new AdjacencyListGraph<>();

        Vertex<String> v1 = graph.insertVertex("A");
        Vertex<String> v2 = graph.insertVertex("B");
        Vertex<String> v3 = graph.insertVertex("C");
        Vertex<String> v4 = graph.insertVertex("D");
        Vertex<String> v5 = graph.insertVertex("E");
        Vertex<String> v6 = graph.insertVertex("F");

        graph.insertEdge(v1, v2, new WeightedEdge(4)); 
        graph.insertEdge(v1, v3, new WeightedEdge(2)); 
        graph.insertEdge(v2, v3, new WeightedEdge(5)); 
        graph.insertEdge(v2, v4, new WeightedEdge(10)); 
        graph.insertEdge(v3, v5, new WeightedEdge(3)); 
        graph.insertEdge(v5, v4, new WeightedEdge(4)); 
        graph.insertEdge(v4, v6, new WeightedEdge(11)); 

        Map<Vertex<String>, Integer> distances = ShortestPathUtil.dijkstra(graph, v1);

        assertTrue(distances.get(v1) == 0); 
        assertTrue(distances.get(v2) == 4); 
        assertTrue(distances.get(v3) == 2); 
        assertTrue(distances.get(v4) == 9);
        assertTrue(distances.get(v5) == 5); 
        assertTrue(distances.get(v6) == 20); 
    }
    
    /**
     * Test the output of the shortest path tree construction method
     */ 
    @Test
    public void testShortestPathTree() {
        // Create a graph
        Graph<String, WeightedEdge> graph = new AdjacencyListGraph<>();

        Vertex<String> v1 = graph.insertVertex("A");
        Vertex<String> v2 = graph.insertVertex("B");
        Vertex<String> v3 = graph.insertVertex("C");
        Vertex<String> v4 = graph.insertVertex("D");
        Vertex<String> v5 = graph.insertVertex("E");
        Vertex<String> v6 = graph.insertVertex("F");

        graph.insertEdge(v1, v2, new WeightedEdge(4)); 
        graph.insertEdge(v1, v3, new WeightedEdge(2)); 
        graph.insertEdge(v2, v3, new WeightedEdge(5)); 
        graph.insertEdge(v2, v4, new WeightedEdge(10));
        graph.insertEdge(v3, v5, new WeightedEdge(3)); 
        graph.insertEdge(v5, v4, new WeightedEdge(4)); 
        graph.insertEdge(v4, v6, new WeightedEdge(11)); 

        Map<Vertex<String>, Integer> distances = ShortestPathUtil.dijkstra(graph, v1);
        Map<Vertex<String>, Edge<WeightedEdge>> shortestPathTree = ShortestPathUtil.shortestPathTree(graph, v1, distances);

        assertEquals(graph.getEdge(v1, v2), shortestPathTree.get(v2));
        assertEquals(graph.getEdge(v1, v3), shortestPathTree.get(v3)); 
        assertEquals(graph.getEdge(v3, v5), shortestPathTree.get(v5));
        assertEquals(graph.getEdge(v5, v4), shortestPathTree.get(v4));
        assertEquals(graph.getEdge(v4, v6), shortestPathTree.get(v6));
    }

    /** Helper class to help with testing */
    private static class WeightedEdge implements Weighted {
    	
    	/** Weight of the given edge */
        private int weight;

        /** 
         * Constructs the weighted edge 
         * @param weight of edge
         */
        public WeightedEdge(int weight) {
            this.weight = weight;
        }

        @Override
        public int getWeight() {
            return weight;
        }
    }
    
}
