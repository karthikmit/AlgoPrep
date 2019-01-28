import com.sun.tools.javac.util.Pair;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by karthik on 24/12/18.
 */
public class PeasantMultiplier {

    public static void multiply(int x, int y) {

        int prod = 0;

        while(x > 0) {
            if(x % 2 == 1) {
                prod += y;
            }

            x = x >> 1;
            y += y;
        }

        System.out.println(prod);
    }

    public static int[] apportion(int[] populationInfo, int leaders) {
        int[] assignedLeaders = new int[populationInfo.length];
        PriorityQueue<Pair<Integer, Double>> priorityQueue = new PriorityQueue<>(new Comparator<Pair<Integer, Double>>() {
            @Override
            public int compare(Pair<Integer, Double> o1, Pair<Integer, Double> o2) {
                return o2.snd.compareTo(o1.snd);
            }
        });

        for(int i = 0; i < populationInfo.length; i++) {
            assignedLeaders[i] = 1;
            priorityQueue.add(Pair.of(i, populationInfo[i] / Math.sqrt(2.0)));
            leaders -= 1;
        }

        while (leaders > 0) {
            Pair<Integer, Double> maxPopulationPair = priorityQueue.poll();
            assignedLeaders[maxPopulationPair.fst] += 1;
            int assignedLeader = assignedLeaders[maxPopulationPair.fst];
            priorityQueue.add(Pair.of(maxPopulationPair.fst, populationInfo[maxPopulationPair.fst] / Math.sqrt(assignedLeader * (assignedLeader + 1))));

            leaders -= 1;
        }

        return assignedLeaders;
    }

    public static void main(String[] args) {
        //PeasantMultiplier.multiply(10, -20);

        //System.out.println(-10 >> 1);
        int[] allocation = apportion(new int[]{1000, 2000, 3000, 4000, 5000}, 15);
        Arrays.stream(allocation).forEach(value -> System.out.println(value));
    }
}
