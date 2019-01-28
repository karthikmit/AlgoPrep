import java.util.*;

/**
 * Created by karthik on 29/10/18.
 */
public class BinarySearchTree <T extends Comparable, U> {

    private TreeNode<T, U> root;

    public void addValue(T key, U value) {
        if(root == null) {
            root = new TreeNode().setKey(key).setValue(value);
        } else {
            addInternal(key, value, this.root);
        }
    }

    private void addInternal(T key, U value, TreeNode node) {
        if(node.getKey().compareTo(key) < 0) {
            // Node key is less than given key.
            if(node.getRight() == null) {
                node.setRight(new TreeNode().setKey(key).setValue(value).setParent(node));
            } else {
                addInternal(key, value, node.getRight());
            }
        } else if(node.getKey().compareTo(key) > 0) {
            // Node key is greater than given key.
            if(node.getLeft() == null) {
                node.setLeft(new TreeNode().setKey(key).setValue(value).setParent(node));
            } else {
                addInternal(key, value, node.getLeft());
            }
        } else {
            // Duplicate.
            // Ignore.
        }
    }

    public void printInOrder() {
        printInOrderInternal(this.root);
    }

    private void printInOrderInternal(TreeNode node) {
        if(node == null) return;
        printInOrderInternal(node.getLeft());

        System.out.println(node.value);

        printInOrderInternal(node.getRight());
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer, Integer> integerTree = new BinarySearchTree<>();

        Integer toFindSuccessorOf = populateWithRandom(integerTree);

        // 10, 15, 11, 13, 14
        /*integerTree.addValue(10, 10);
        integerTree.addValue(15, 15);
        integerTree.addValue(11, 11);
        integerTree.addValue(13, 13);
        integerTree.addValue(14, 14);
        Integer toFindSuccessorOf = 14;*/

        //integerTree.printInOrder();
        integerTree.printLevelOrder();
        /*System.out.println("--------");
        Integer successor = integerTree.getSuccessor(toFindSuccessorOf);
        System.out.println("Successor of :: " + toFindSuccessorOf + " :: " + successor);*/
    }

    private void printLevelOrder() {
        Queue<TreeNode> values = new ArrayDeque<>();
        values.add(this.root);

        while (values.peek() != null) {
            List<TreeNode> currentLevelNodes = new ArrayList<>();
            while (values.peek() != null) {
                currentLevelNodes.add(values.poll());
            }

            for(TreeNode treeNode : currentLevelNodes) {
                System.out.print(treeNode.value + " ");

                if(treeNode.left != null)
                    values.add(treeNode.left);
                if(treeNode.right != null)
                    values.add(treeNode.right);
            }
            System.out.println("");
        }
    }

    private static Integer populateWithRandom(BinarySearchTree<Integer, Integer> integerTree) {
        Random random = new Random(new Date().getTime());
        Integer toFindSuccessorOf = null;
        for(int index = 0; index < 20; index++) {
            int nextValue = random.nextInt(100);

            if(toFindSuccessorOf == null) toFindSuccessorOf = nextValue;

            integerTree.addValue(nextValue, nextValue);
        }
        return toFindSuccessorOf;
    }

    private U getSuccessor(T key) {
        TreeNode keyNode = findNode(key);
        return getSuccessorInternal(keyNode);
    }

    private U getSuccessorInternal(TreeNode keyNode) {
        if(keyNode.getRight() != null) {
            // Get the left most child of right tree.
            TreeNode<T, U> correctNode = keyNode.getRight();
            while(correctNode.getLeft() != null) {
                correctNode = correctNode.getLeft();
            }

            return correctNode.getValue();
        } else {
            // Reach out to first parent at the left side.
            while(keyNode.parent != null && keyNode.parent.left != keyNode) {
                keyNode = keyNode.parent;
            }
            if(keyNode.parent == null) return null;

            return (U) keyNode.parent.value;
        }
    }

    private TreeNode findNode(T key) {
        return findNodeInternal(key, root);
    }

    private TreeNode findNodeInternal(T key, TreeNode node) {
        if(node.getKey().compareTo(key) == 0) return node;

        else if(node.getKey().compareTo(key) < 0) {
            return findNodeInternal(key, node.getRight());
        } else {
            return findNodeInternal(key, node.getLeft());
        }
    }

    public static class TreeNode<T extends Comparable, U> {
        private TreeNode<T, U> left;
        private TreeNode<T, U> right;
        private TreeNode<T, U> parent;

        private T key;
        private U value;

        public TreeNode getLeft() {
            return left;
        }

        public TreeNode<T, U> setLeft(TreeNode left) {
            this.left = left;
            return this;
        }

        public TreeNode getRight() {
            return right;
        }

        public TreeNode<T, U> setRight(TreeNode right) {
            this.right = right;
            return this;
        }

        public TreeNode getParent() {
            return parent;
        }

        public TreeNode<T, U> setParent(TreeNode parent) {
            this.parent = parent;
            return this;
        }

        public T getKey() {
            return key;
        }

        public TreeNode<T, U> setKey(T key) {
            this.key = key;
            return this;
        }

        public U getValue() {
            return value;
        }

        public TreeNode<T, U> setValue(U value) {
            this.value = value;
            return this;
        }
    }
}

