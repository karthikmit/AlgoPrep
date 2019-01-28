import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by karthik on 12/11/18.
 */
public class MaxFlowCalculator {

    private long[][] flow; //max flow beetween i and j verticles
    private long[][] capacity; // edge capacity
    private int[] parent; //parent
    private boolean[] visited; //just for checking if visited
    private int n, m;

    public MaxFlowCalculator(int numOfVertices, int numOfEdges) {
        this.n = numOfVertices;
        this.m = numOfEdges;
        this.flow = new long[n][n];
        this.capacity = new long[n][n];
        this.parent = new int[n];
        this.visited = new boolean[n];
    }

    public void addEdge(int from, int to, long capacity) {
        assert capacity >= 0;
        this.capacity[from][to] += capacity;
    }

    /**
     * Get maximum flow.
     *
     * @param s source
     * @param t target
     * @return maximum flow
     */
    public long getMaxFlow(int s, int t) {
        while (true) {
            final Queue<Integer> Q = new ArrayDeque<Integer>();
            Q.add(s);

            for (int i = 0; i < this.n; ++i)
                visited[i] = false;
            visited[s] = true;

            boolean check = false;
            int current;
            while (!Q.isEmpty()) {
                current = Q.peek();
                if (current == t) {
                    check = true;
                    break;
                }
                Q.remove();
                for (int i = 0; i < n; ++i) {
                    if (!visited[i] && capacity[current][i] > flow[current][i]) {
                        visited[i] = true;
                        Q.add(i);
                        parent[i] = current;
                    }
                }
            }
            if (!check)
                break;

            printPath(parent, s, t);

            long temp = capacity[parent[t]][t] - flow[parent[t]][t];
            for (int i = t; i != s; i = parent[i])
                temp = Math.min(temp, (capacity[parent[i]][i] - flow[parent[i]][i]));

            for (int i = t; i != s; i = parent[i]) {
                flow[parent[i]][i] += temp;
                flow[i][parent[i]] -= temp;
            }
        }

        long result = 0;
        for (int i = 0; i < n; ++i)
            result += flow[s][i];
        return result;
    }

    private void printPath(int[] parent, int s, int t) {
        System.out.println("");
        int currentLastIndex = t;
        do {
            System.out.print(currentLastIndex + " ");
            currentLastIndex = parent[currentLastIndex];
        } while(currentLastIndex != s);
        System.out.print(currentLastIndex + "\n");
    }

    public static void main(String[] args) {
        MaxFlowCalculator maxFlowCalculator = new MaxFlowCalculator(12, 19);

        /*maxFlowCalculator.addEdge(0, 1, 2);
        maxFlowCalculator.addEdge(0, 2, 4);
        maxFlowCalculator.addEdge(2, 1, 6);
        maxFlowCalculator.addEdge(1, 3, 4);
        maxFlowCalculator.addEdge(2, 4, 2);
        maxFlowCalculator.addEdge(4, 3, 1);
        maxFlowCalculator.addEdge(3, 2, 5);
        maxFlowCalculator.addEdge(3, 5, 5);
        maxFlowCalculator.addEdge(4, 5, 3);*/

        maxFlowCalculator.addEdge(0, 6, 1);
        maxFlowCalculator.addEdge(0, 7, 1);
        maxFlowCalculator.addEdge(0, 8, 1);
        maxFlowCalculator.addEdge(0, 9, 1);
        maxFlowCalculator.addEdge(0, 10, 1);

        maxFlowCalculator.addEdge(1, 11, 1);
        maxFlowCalculator.addEdge(2, 11, 1);
        maxFlowCalculator.addEdge(3, 11, 1);
        maxFlowCalculator.addEdge(4, 11, 1);
        maxFlowCalculator.addEdge(5, 11, 1);

        maxFlowCalculator.addEdge(6, 1, 1);
        maxFlowCalculator.addEdge(6, 2, 1);
        maxFlowCalculator.addEdge(7, 1, 1);
        maxFlowCalculator.addEdge(8, 2, 1);
        maxFlowCalculator.addEdge(8, 3, 1);
        maxFlowCalculator.addEdge(9, 3, 1);
        maxFlowCalculator.addEdge(9, 4, 1);
        maxFlowCalculator.addEdge(9, 5, 1);
        maxFlowCalculator.addEdge(10, 5, 1);



        long maxFlow = maxFlowCalculator.getMaxFlow(0, 11);
        System.out.println(maxFlow);
    }
}
