/**
 * Created by karthik on 02/11/18.
 */
public class TreePrinter {

    /*public void prettyPrintTree(BTree tree) {
        List<List<TreeNode>> levelsStore = new ArrayList<>();

        doLevelTraversalAndStore(tree, levelsStore);
        Map<Integer, Integer> levelSpacingHolder = calculateSpacingPerLevel(levelsStore);
        int levels = levelsStore.size();

        for(int index = 0; index < levels; index++) {
            int spacing = levelsSpacingHolder.get(index);
            int firstNumberSpacing = spacing >> 1;

            List<TreeNode> levelNumbers = levelsStore.get(index);
            for(int index = 0; index < levelNumbers.size(); index++) {
                StringBuilder levelStringBuilder = new StringBuilder();
                if(index == 0) {
                    for(int sindex = 0; sindex < firstNumberSpacing; sindex) {
                        levelStringBuilder.add(" ");
                    }

                    levelStringBuilder.add(levelNumbers.get(0).getValue());
                } else {
                    for(int sindex = 0; sindex < spacing; sindex) {
                        levelStringBuilder.add(" ");
                    }
                    levelStringBuilder.add(levelNumbers.get(index).getValue());
                }
            }
        }
    }

    private void calculateSpacingPerLevel(List<List<TreeNode>> levelsStore) {
        Map<Integer, Integer> levelsSpacingHolder = new HashMap<>();

        int length = levelsStore.size();
        int count = 1;
        for(int index = length - 1; index <= 0; index--) {
            levelsSpacingHolder.put(index, (1 << count) - 1);
            count++;
        }

        return levelsSpacingHolder;
    }

    private void doLevelTraversalAndStore(tree, levelsStore) {
        Queue<Integer> levelQueue = new ArrayDeque<>();

        levelQueue.add(tree.getRoot());

        while(levelQueue.peek() != null) {

            List<TreeNode> currentLevelElements = new ArrayList<>();
            while(levelQueue.peek() != null) {
                currentLevelElements.add(levelQueue.poll());
            }
            levelsStore.add(currentLevelElements);
            for(TreeNode treeNode : currentLevelElements) {
                if(treeNode.getLeft() == null && treeNode.getRight() == null) continue;

                if(treeNode.getLeft() != null) {
                    levelQueue.add(treeNode.getLeft());
                } else {
                }
                if(treeNode.getRight() != null) {
                    levelQueue.add(treeNode.getRight());
                } else {
                }

            }
        }
    }*/
}
