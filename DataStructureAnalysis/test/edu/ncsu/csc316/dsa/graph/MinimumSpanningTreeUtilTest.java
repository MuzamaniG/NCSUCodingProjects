package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc316.dsa.Weighted;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;
import edu.ncsu.csc316.dsa.set.HashSet;
import edu.ncsu.csc316.dsa.set.Set;

/**
 * Test class for MinimumSpanningTreeUtil
 * Checks the expected outputs of Prim-Jarnik's algorithm
 * and Kruskal's algorithm
 *
 * @author Dr. King
 * @author mhgausi
 *
 */
public class MinimumSpanningTreeUtilTest {

    /**
     * Test the output of Prim-Jarnik's algorithm
     */ 
    @Test
    public void testPrimJarnik() {
        Graph<String, Weighted> graph = new AdjacencyListGraph<>();

        Vertex<String> v1 = graph.insertVertex("Vertex1");
        Vertex<String> v2 = graph.insertVertex("Vertex2");
        Vertex<String> v3 = graph.insertVertex("Vertex3");
        Vertex<String> v4 = graph.insertVertex("Vertex4");
        Vertex<String> v5 = graph.insertVertex("Vertex5");

        graph.insertEdge(v1, v2, new WeightedEdge(10));
        graph.insertEdge(v1, v3, new WeightedEdge(20));
        graph.insertEdge(v2, v3, new WeightedEdge(30));
        graph.insertEdge(v2, v4, new WeightedEdge(40));
        graph.insertEdge(v3, v4, new WeightedEdge(50));
        graph.insertEdge(v4, v5, new WeightedEdge(60));

        PositionalList<Edge<Weighted>> mst = MinimumSpanningTreeUtil.primJarnik(graph);
        assertNotNull(mst);
        
        Set<Edge<Weighted>> mstEdges = new HashSet<>();
        for (Edge<Weighted> edge : mst) {
            assertNotNull(edge);
            assertNotNull(edge.getElement());
            mstEdges.add(edge);
        }
        
        for (Edge<Weighted> edge : graph.edges()) {
            if (mstEdges.contains(edge)) {
                assertNotNull(edge.getElement());
                assertTrue(edge.getElement().getWeight() > 0);
            }
        }
    }
    
    /**
     * Test the output of Kruskal's algorithm
     */ 
    @Test
    public void testKruskal() {
        Graph<String, Weighted> graph = new AdjacencyListGraph<>();

        Vertex<String> v1 = graph.insertVertex("Vertex1");
        Vertex<String> v2 = graph.insertVertex("Vertex2");
        Vertex<String> v3 = graph.insertVertex("Vertex3");
        Vertex<String> v4 = graph.insertVertex("Vertex4");
        Vertex<String> v5 = graph.insertVertex("Vertex5");

        graph.insertEdge(v1, v2, new WeightedEdge(10));
        graph.insertEdge(v1, v3, new WeightedEdge(20));
        graph.insertEdge(v2, v3, new WeightedEdge(30));
        graph.insertEdge(v2, v4, new WeightedEdge(40));
        graph.insertEdge(v3, v4, new WeightedEdge(50));
        graph.insertEdge(v4, v5, new WeightedEdge(60));

        PositionalList<Edge<Weighted>> mst = MinimumSpanningTreeUtil.kruskal(graph);

        for (Edge<Weighted> edge : mst) {
            assertNotNull(edge);
            assertNotNull(edge.getElement());
        }
        
    }
    
    /**
     * Example weighted edge class to use in tests.
     */
    private static class WeightedEdge implements Weighted {
    	/** Weight of edge */
        private int weight;
        
        public WeightedEdge(int weight) {
            this.weight = weight;
        }
        
        @Override
        public int getWeight() {
            return weight;
        }
    }

}
