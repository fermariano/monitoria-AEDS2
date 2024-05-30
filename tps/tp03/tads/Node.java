package tps.tp03.tads;

public class Node<T> {
    protected T data;        
    protected Node<T> prev, next;  

    public Node(){
    }

    public Node(T data) {
        this(data, null, null);
    }

    public Node(T data, Node<T> prev, Node<T> next) {
        this.data = data;
        this.prev = prev;
        this.next = next;
    }

}

