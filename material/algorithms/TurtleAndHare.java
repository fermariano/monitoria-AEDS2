package algorithms;
/** 
 * Turtle and Hare - AEDS II
 * 
 * @author Thomas Neuenschwander
 * @since 27/04/2024
 * 
 * [GitHub](https://github.com/thomneuenschwander)
 */

class Node {
    int data;
    Node next;

    Node() {
        this(0);
    }

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class LinkedList {
    Node head;

    LinkedList() {
        head = new Node();
    }

    void append(int data) {
        Node current = head;
        while (current.next != null)
            current = current.next;

        current.next = new Node(data);
    }

    /**
     * @complexity Tempo: O(n/2) => O(n)
     */
    Node findMiddle() {
        if (head.next == null)
            return null;

        Node turtle = head.next;
        Node hare = head.next;

        while (hare != null && hare.next != null) {
            turtle = turtle.next; // Avança uma etapa
            hare = hare.next.next; // Avança duas etapas
        }

        return turtle;
    }
}

public class TurtleAndHare {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();

        list.append(1);
        list.append(2);
        list.append(3);
        list.append(4);
        list.append(5);

        Node middle = list.findMiddle();

        if (middle != null)
            System.out.println("O meio da lista é: " + middle.data);
        else
            System.out.println("A lista está vazia.");
    }
}
