package algorithms.tads.tree;
public class BST {
    Node root;
    
    public void add(int value){
        if(root == null)
            root = new Node(value);
        else
            add(root, value);
    }

    private void add(Node current, int value) {
        if(current == null)
            current = new Node(value);
        else if(current.value < value)
            add(current.left, value);
        else
            add(current.right, value);
    }

    public static void main(String[] args) {
        BST tree = new BST();
        tree.add(0);
        tree.add(90);
        tree.add(6);
        System.out.println(tree);
    }

    private void inOrder(Node current, StringBuilder sb){
        if(current != null){
            inOrder(current.left, sb);
            sb.append(" ").append(current.value);
            inOrder(current.right, sb);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        inOrder(root, sb);
        return "BST [root=" + sb.toString() + " ]";
    }
}
