/**
 * leetcode 652: Find Duplicate Subtrees
 * 
 * @author Thomas Neuenschwander
 * @since 30/05/2024
 * 
 *        [GitHub](https://github.com/thomneuenschwander)
 */
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FindDuplicateSubtrees_652 {

    static List<Node> findDuplicateSubtrees(Node root) {
        List<Node> duplicates = new LinkedList<>();
        traversal(root, new HashMap<>(), duplicates);
        return duplicates;
    }

    static String traversal(Node current, Map<String, Integer> map, List<Node> duplicates) {
        if(current == null)
            return ".";
        
        String left = traversal(current.left, map, duplicates);
        String right = traversal(current.right, map, duplicates);

        String serial = current.val + "^" + left + "^" + right;
        
        map.put(serial, map.getOrDefault(serial, 0) + 1);

        if(map.get(serial) == 2)
            duplicates.add(current);

        return serial;
    }

    public static void main(String[] args) {
        Node root = buildExemple03Tree();

        List<Node> duplicates = findDuplicateSubtrees(root);

        duplicates.forEach(node -> {
            System.out.println(node.val);
        });
    }

    static Node buildExemple01Tree() {
        Node root = new Node(1);
        root.left = new Node(2);
        root.left.left = new Node(4);
        root.right = new Node(3);
        root.right.left = new Node(2);
        root.right.left.left = new Node(4);
        root.right.right = new Node(4);
        return root;
    }

    static Node buildExemple02Tree() {
        Node root = new Node(2);
        root.left = new Node(1);
        root.right = new Node(1);
        return root;
    }

    static Node buildExemple03Tree() {
        Node root = new Node(2);
        root.left = new Node(2);
        root.left.left = new Node(3);
        root.right = new Node(1);
        root.right.left = new Node(3);
        return root;
    }
}

class Node {
    int val;
    Node left;
    Node right;

    Node() {
    }
    Node(int val) {
        this.val = val;
    }

    Node(int val, Node left, Node right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
 
