package algorithms.tads.tree;

public class Node {
    protected int value;
    protected Node right, left;

    public Node(int value){
        this(value, null, null);
    }

    public Node(int value, Node right, Node left) {
        this.value = value;
        this.right = right;
        this.left = left;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }
}
