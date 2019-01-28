import java.util.*;
import java.util.stream.Stream;

/**
 * Created by karthik on 16/11/18.
 */
public class NaiveGraph {

    private Map<NGNode, Set<NGEdge>> adjMap = new HashMap<>();

    public void addEdge(NGNode source, NGNode destination, Direction direction, int weight) {
        if(!adjMap.containsKey(source)) {
            adjMap.put(source, new HashSet<>());
        }

        if(!adjMap.containsKey(destination)) {
            adjMap.put(destination, new HashSet<>());
        }

        NGEdge edge = new NGEdge(source, destination, weight, direction);
        Set<NGEdge> edges = adjMap.get(source);
        if(!edges.contains(edge)) {
            edges.add(edge);
        }

        NGEdge reverseEdge = new NGEdge(destination, source, weight, direction);
        Set<NGEdge> reverseEdges = adjMap.get(destination);
        if(!reverseEdges.contains(edge)) {
            reverseEdges.add(edge);
        }
    }

    public void printDFS(NGNode source, Set<NGNode> visitedVertices) {
        if(!visitedVertices.contains(source)) {
            System.out.println(source.key);
            visitedVertices.add(source);
            Set<NGEdge> edgeSet = adjMap.get(source);
            for(NGEdge edge : edgeSet) {
                printDFS(edge.destination, visitedVertices);
            }
        }
    }

    public static class NGEdge {
        public NGNode source;
        public NGNode destination;
        public int weight;
        public Direction direction;

        public NGEdge(NGNode source, NGNode destination, int weight, Direction direction) {

            this.source = source;
            this.destination = destination;
            this.weight = weight;
            this.direction = direction;
        }

        @Override
        public int hashCode() {
            return source.hashCode() ^ destination.hashCode();
        }

        @Override
        public boolean equals(Object another) {
            return another instanceof NGEdge &&
                    this.source.equals(((NGEdge) another).source) && this.destination.equals(((NGEdge) another).destination);

        }
    }

    public static class NGNode {
        public String key;
        public Object value;

        public NGNode(String key, Object value) {
            this.key = key;
            this.value = (value == null ? key : value);
        }

        @Override
        public int hashCode() {
            return key.hashCode();
        }

        @Override
        public boolean equals(Object another) {
            if(another instanceof NGNode) {
                return this.key.equals(((NGNode)another).key);
            }

            return false;
        }
    }

    public static enum Direction {
        INCOMING,
        OUTGOING,
        BOTH;
    }

    public static void main(String[] args) {
        NaiveGraph naiveGraph = new NaiveGraph();

        String nodeIdentifiers = "abcdef";
        Map<String, NGNode> allNodes = new HashMap<>();
        char[] chars = nodeIdentifiers.toCharArray();
        for(char character : chars) {
            String key = new String(new char[]{character});
            allNodes.put(key, new NGNode(key, null));
        }

        naiveGraph.addEdge(allNodes.get("a"), allNodes.get("b"), Direction.BOTH, 10);
        naiveGraph.addEdge(allNodes.get("a"), allNodes.get("c"), Direction.BOTH, 3);
        naiveGraph.addEdge(allNodes.get("b"), allNodes.get("d"), Direction.BOTH, 10);
        naiveGraph.addEdge(allNodes.get("c"), allNodes.get("d"), Direction.BOTH, 5);
        naiveGraph.addEdge(allNodes.get("c"), allNodes.get("e"), Direction.BOTH, 7);
        naiveGraph.addEdge(allNodes.get("d"), allNodes.get("e"), Direction.BOTH, 3);
        naiveGraph.addEdge(allNodes.get("e"), allNodes.get("f"), Direction.BOTH, 9);
        naiveGraph.addEdge(allNodes.get("b"), allNodes.get("f"), Direction.BOTH, 18);

        naiveGraph.printDFS(allNodes.get("a"), new HashSet<>());
    }
}
