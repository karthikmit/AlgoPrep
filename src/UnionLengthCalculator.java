import com.sun.tools.javac.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by karthik on 07/12/18.
 */
public class UnionLengthCalculator {

    public int unionLengthCalculator(List<Pair<Integer, Integer>> intervals) {
        Collections.sort(intervals, (o1, o2) -> o1.fst.compareTo(o2.fst));

        int totalLength = 0;
        int lastEndTime = -1;
        for(Pair<Integer, Integer> interval : intervals) {
            if(interval.fst >= lastEndTime) {
                totalLength += interval.snd - interval.fst;
                lastEndTime = interval.snd;
            }
            else if(interval.snd > lastEndTime) {
                totalLength += interval.snd - lastEndTime;
                lastEndTime = interval.snd;
            } else {
                // Don't calculate.
            }
        }

        return totalLength;
    }

    public static void main(String[] args) {
        List<Pair<Integer, Integer>> intervals = new ArrayList<Pair<Integer, Integer>>() {{
            add(Pair.of(10, 11));
            add(Pair.of(8, 10));
            add(Pair.of(12, 14));
            add(Pair.of(14, 17));
            add(Pair.of(10, 12));
        }};
        int length = new UnionLengthCalculator().unionLengthCalculator(intervals);
        System.out.println(length);
    }
}
