import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by karthik on 26/11/18.
 */
public class MazeJumper {


    public static class Pair {
        public int first;
        public int second;
        public int level;

        public Pair(int first, int second, int level) {
            this.first = first;
            this.second = second;
            this.level = level;
        }
    }

    public int findMinJumps(int[][] maze, int m, int n, int startX, int startY) {
        if(maze == null) return 0;

        if(checkIfInEdge(maze, startX, startY, m, n)) {
            return 1;
        }

        if(startX >= 0 && startX < m && startY >= 0 && startY < n) {
            Queue<Pair> bfsQueue = new ArrayDeque<>();
            Pair startNode = new Pair(startX, startY, 0);
            bfsQueue.add(startNode);

            while (bfsQueue.peek() != null) {
                Pair coordinates = bfsQueue.poll();

                int currentX = coordinates.first;
                int currentY = coordinates.second;

                if(currentX >= 0 && currentX < m && currentY >= 0 && currentY < n) {
                    if(checkIfInEdge(maze, currentX, currentY, m, n)) {
                        if(maze[currentX][currentY] == 0) {
                            //TODO: Return the level here ..
                            return coordinates.level + 1;
                        }
                    }

                    // find all the possible coordinates.
                    Pair left = new Pair(currentX - 1, currentY, coordinates.level + 1);
                    Pair right = new Pair(currentX + 1, currentY, coordinates.level + 1);
                    Pair top = new Pair(currentX, currentY + 1, coordinates.level + 1);
                    Pair bottom = new Pair(currentX, currentY - 1, coordinates.level + 1);

                    addToQueue(maze[coordinates.first][coordinates.second], bfsQueue, left);
                    addToQueue(maze[coordinates.first][coordinates.second], bfsQueue, right);
                    addToQueue(maze[coordinates.first][coordinates.second], bfsQueue, top);
                    addToQueue(maze[coordinates.first][coordinates.second], bfsQueue, bottom);
                }

                maze[currentX][currentY] = 2;
            }

        } else {
            return 0;
        }

        return Integer.MAX_VALUE;
    }

    private boolean checkIfInEdge(int[][] maze, int x, int y, int rowCount, int colCount) {
        if(x == 0 || y == 0 || x == (rowCount - 1) || y == (colCount - 1)) {
            return true;
        }
        return false;
    }

    private void addToQueue(int i, Queue<Pair> bfsQueue, Pair left) {
        if(i == 0) {
            bfsQueue.add(left);
        }
    }

    public void findMajority() {
        int[] elements = new int[] {1, 1, 10, 1, 1, 1, 8, 6, 1, 8};

        int majorityElement = 0; int counter = 0;

        for(int index = 0; index < elements.length; index++) {
            if(counter == 0) {
                majorityElement = elements[index];
                counter = 1;
            } else if(elements[index] == majorityElement) {
                counter += 1;
            } else
                counter -= 1;
        }

        System.out.println("Majority Element :: " + majorityElement);
    }
}
