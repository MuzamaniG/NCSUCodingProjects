package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * Test class for GraphTraversalUtil
 * Checks the expected outputs of depth first search
 * and breadth first search
 *
 * @author Dr. King
 * @author mhgausi
 *
 */
public class GraphTraversalUtilTest {

    /**
     * Test the output of depth first search on a graph
     */ 
    @Test
    public void testDepthFirstSearch() {
        Graph<String, Integer> graph = new AdjacencyListGraph<>();

        Vertex<String> v1 = graph.insertVertex("F");
        Vertex<String> v2 = graph.insertVertex("R");
        Vertex<String> v3 = graph.insertVertex("O");
        Vertex<String> v4 = graph.insertVertex("W");
        Vertex<String> v5 = graph.insertVertex("N");

        graph.insertEdge(v1, v2, 10); 
        graph.insertEdge(v1, v3, 20); 
        graph.insertEdge(v2, v4, 30); 
        graph.insertEdge(v3, v4, 40); 
        graph.insertEdge(v4, v5, 50); 

        Map<Vertex<String>, Edge<Integer>> edgesMap = GraphTraversalUtil.depthFirstSearch(graph, v1);

        assertNotNull(edgesMap.get(v2));
        assertNotNull(edgesMap.get(v3));
        assertNotNull(edgesMap.get(v4));
        assertNotNull(edgesMap.get(v5));
        assertEquals(4, edgesMap.size());
    }
    
    /**
     * Test the output of the breadth first search
     */ 
    @Test
    public void testBreadthFirstSearch() {
        Graph<String, Integer> graph = new AdjacencyListGraph<>();

        Vertex<String> v1 = graph.insertVertex("S");
        Vertex<String> v2 = graph.insertVertex("M");
        Vertex<String> v3 = graph.insertVertex("I");
        Vertex<String> v4 = graph.insertVertex("L");
        Vertex<String> v5 = graph.insertVertex("E");

        graph.insertEdge(v1, v2, 5);  
        graph.insertEdge(v1, v3, 15); 
        graph.insertEdge(v2, v4, 25); 
        graph.insertEdge(v3, v4, 35); 
        graph.insertEdge(v4, v5, 45); 

        Map<Vertex<String>, Edge<Integer>> edgesMap = GraphTraversalUtil.breadthFirstSearch(graph, v1);
        assertNotNull(edgesMap.get(v2));
        assertNotNull(edgesMap.get(v3));
        assertNotNull(edgesMap.get(v4));
        assertNotNull(edgesMap.get(v5));
        assertEquals(4, edgesMap.size());
    }
    
}
