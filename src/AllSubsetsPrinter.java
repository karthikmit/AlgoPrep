import java.util.*;

/**
 * Created by karthik on 01/12/18.
 */
public class AllSubsetsPrinter {


    public List<List<String>> getAllSplits(final String input, Set<String> dictionary, Map<String, List<List<String>>> resultsMemo) {
        if(input.equals("")) return new ArrayList<>();

        if(resultsMemo.containsKey(input)) return resultsMemo.get(input);

        List<List<String>> possibleSplits = new ArrayList<>();
        for(int index = 0; index < input.length(); index++) {
            String prefix = input.substring(0, index + 1);
            if(dictionary.contains(prefix)) {

                String suffix = input.substring(index + 1);
                if(!suffix.equals("")) {
                    List<List<String>> otherSplits = getAllSplits(suffix, dictionary, resultsMemo);
                    if(otherSplits.size() > 0) {
                        // There are possible splits.
                        for(List<String> otherSplit : otherSplits) {
                            otherSplit.add(prefix);
                            possibleSplits.add(otherSplit);
                        }
                    }
                } else {
                    possibleSplits.add(new ArrayList<String>() {{add(prefix);}});
                }
            }
        }

        resultsMemo.put(input, possibleSplits);
        return possibleSplits;
    }


    public void printAllSubSets(int[] elements) {
        Queue<Set> currentSetsQueue = new ArrayDeque<>();

        currentSetsQueue.add(new HashSet<>());
        int level = 0;

        List<Set> currentSets = new ArrayList<>();
        while(true) {
            if(level == elements.length) break;
            currentSets = new ArrayList<>();
            List<Set> newSets = new ArrayList<>();

            while (currentSetsQueue.peek() != null) currentSets.add(currentSetsQueue.poll());

            int newElement = elements[level];
            for(Set currentSet : currentSets) {
                Set newSet = new HashSet<>();
                newSet.addAll(currentSet);

                newSet.add(new HashSet<Integer>() {{add(newElement);}});
                newSets.add(currentSet); newSets.add(newSet);
            }

            for (Set newSet : newSets) currentSetsQueue.add(newSet);
            level++;
        }

        for(Set set : currentSets) {
            Iterator iterator = set.iterator();
            while (iterator.hasNext()) {
                System.out.print(iterator.next() + ",");
            }

            System.out.println("");
        }
    }

    public static void main(String[] args) {
        //new AllSubsetsPrinter().printAllSubSets(new int[] {1, 2, 3, 4});
        List<List<String>> allSplits = new AllSubsetsPrinter().getAllSplits("CATMAT", new HashSet<String>() {{
            add("CAT");
            add("C");
            add("A");
            add("AT");
            add("MAT");
        }}, new HashMap<>());

        for(List<String> split : allSplits) {
            Collections.reverse(split);
            for(String word : split) {
                System.out.print(word + " ");
            }
            System.out.println("");
        }
    }
}
