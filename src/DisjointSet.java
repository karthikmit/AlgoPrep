import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Disjoint Set.
 */
public class DisjointSet {

    private final Map<Object, Object> parentsHolder;
    private final Map<Object, Integer> ranksHolder;

    public DisjointSet() {
        this.parentsHolder = new HashMap<>();
        this.ranksHolder = new HashMap<>();
    }

    public void makeSet(Object element) {
        this.parentsHolder.put(element, element);
    }
    public Object find(Object element) {
        Object parentElement = this.parentsHolder.get(element);
        if(parentElement != element) {
            return find(parentElement);
        }

        return parentElement;
    }
    public void union(Object first, Object second) {
        Object firstParent = find(first);
        Object secondParent = find(second);

        if(firstParent != secondParent) {
            this.parentsHolder.put(secondParent, firstParent);
        }
    }

    public static void main(String[] args) {
        DisjointSet set = new DisjointSet();
        List<Integer> nodesList = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).collect(Collectors.toList());

        nodesList.forEach(element -> set.makeSet(element));
        set.union(nodesList.get(0), nodesList.get(1));
        set.union(nodesList.get(1), nodesList.get(2));
        set.union(nodesList.get(2), nodesList.get(3));

        System.out.println(set.find(nodesList.get(2)));
    }
}
