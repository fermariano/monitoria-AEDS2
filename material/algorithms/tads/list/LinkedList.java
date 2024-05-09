package algorithms.tads.list;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements List<T> {
    private int size;
    private Node<T> head, tail;

    public LinkedList() {
        head = new Node<>();
        tail = head;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public boolean contains(Object o) {
        for (Node<T> i = head.next, j = tail; i != j; i = i.next, j = j.prev) {
            if (i.data.equals(o) || j.data.equals(o))
                return true;
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = head.next;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (current == null)
                    throw new NoSuchElementException();

                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int i = 0;
        for (Node<T> j = head.next; j != null; j = j.next)
            array[i++] = j.data;

        return array;
    }

    @SuppressWarnings({ "unchecked", "hiding" })
    @Override
    public <T> T[] toArray(T[] a) {

        if (a.length < size) 
            a = (T[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
        

        int i = 0;
        Object[] result = a; 
        for (Node<T> x = (Node<T>) head.next; x != null; x = x.next) 
            result[i++] = x.data; 
        
        if (i < a.length) 
            a[i] = null; 
        
        return a;
    }

    @Override
    public boolean add(T e) {
        tail.next = new Node<>(e);
        tail.next.prev = tail;
        tail = tail.next;
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        Node<T> node = findNode(o);
        if (node == null)
            return false;
        remove(node);
        return true;
    }

    private Node<T> findNode(Object o) {
        if (isEmpty())
            return null;

        for (Node<T> i = head.next, j = tail; i != j; i = i.next, j = j.prev) {
            if (i.data.equals(o))
                return i;
            if (j.data.equals(o))
                return j;
        }
        return null;
    }

    private void remove(Node<T> toRemove) {
        toRemove.prev.next = toRemove.next;
        toRemove.next.prev = toRemove.prev;
        toRemove.prev = toRemove.next = toRemove = null;
        size--;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object e : c) {
            if (!contains(e))
                return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean modified = false;
        for (T e : c) {
            if (add(e))
                modified = true;
        }
        return modified;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
        }

        if (c.isEmpty()) {
            return false;
        }

        Node<T> currentNode = (index == size) ? null : findNode(index);
        Node<T> prevNode = (index == 0) ? head : currentNode.prev;

        for (T element : c) {
            Node<T> newNode = new Node<>(element, prevNode, null);
            if (prevNode != null) {
                prevNode.next = newNode;
            } else {
                head.next = newNode;
            }
            prevNode = newNode;
        }

        if (currentNode != null) {
            prevNode.next = currentNode;
            currentNode.prev = prevNode;
        } else {
            tail = prevNode; // If currentNode is null, we are adding at the end
        }

        size += c.size();
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean modified = false;
        Node<T> current = head.next;

        while (current != null) {
            if (c.contains(current.data)) {
                Node<T> next = current.next;
                removeNode(current);
                current = next;
                modified = true;
            } else {
                current = current.next;
            }
        }

        return modified;
    }

    private void removeNode(Node<T> node) {
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            head.next = node.next;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            tail = node.prev;
        }
        size--;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean modified = false;
        Node<T> current = head.next;

        while (current != null) {
            if (!c.contains(current.data)) {
                Node<T> next = current.next;
                removeNode(current);
                current = next;
                modified = true;
            } else {
                current = current.next;
            }
        }

        return modified;
    }

    @Override
    public void clear() {
        head.next = null;
        tail = null;
        size = 0;
    }

    @Override
    public T get(int index) {
        return findNode(index).data;
    }

    @Override
    public T set(int index, T element) {
        Node<T> node = findNode(index);
        T oldData = node.data;
        node.data = element;
        return oldData;
    }

    private Node<T> findNode(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);

        Node<T> node = head;
        for (int i = 0; i < index; i++, node = node.next)
            ;
        return node;
    }

    @Override
    public void add(int index, T element) {
        if (index == 0)
            addToBeginning(element);
        else if (index == size)
            add(element);
        else {
            Node<T> i = findNode(index);
            Node<T> tmp = new Node<>(element);
            tmp.prev = i;
            tmp.next = i.next;
            tmp.prev.next = tmp.next.prev = tmp;
            tmp = i = null;
        }
    }

    private void addToBeginning(T element) {
        Node<T> tmp = new Node<>(element);
        tmp.prev = head;
        tmp.next = head.next;
        head.next = tmp;
        if (head == null)
            tail = tmp;
        else
            tmp.next.prev = tmp;
        tmp = null;
        size++;
    }

    @Override
    public T remove(int index) {
        if (index == 0)
            return removeFromBeginning();
        if (index == size)
            return removeFromEnd();

        Node<T> remove = findNode(index);
        T res = remove.data;
        remove(remove);
        return res;
    }

    private T removeFromEnd() {
        if (isEmpty())
            throw new NoSuchElementException("Attempt to remove from an empty list");
        T res = tail.data;
        tail = tail.prev;
        tail.next.prev = null;
        tail.next = null;
        size--;
        return res;
    }

    private T removeFromBeginning() {
        if (isEmpty())
            throw new NoSuchElementException("Attempt to remove from an empty list");

        Node<T> tmp = head;
        head = head.next;
        T res = head.data;
        tmp.next = head.prev = null;
        tmp = null;
        size--;
        return res;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null)
            throw new IllegalArgumentException("Cannot search for null value");

        int startIndex = 0, endIndex = size;
        for (Node<T> i = head.next, j = tail; i != j; i = i.next, startIndex++, j = j.prev, endIndex--) {
            if (i.data.equals(o))
                return startIndex;
            if (j.data.equals(o))
                return endIndex;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null)
            throw new IllegalArgumentException("Cannot search for null value");

        int index = size - 1;
        for (Node<T> x = tail; x != null; x = x.prev, index--) {
            if (o.equals(x.data))
                return index;
        }
        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        return listIterator(0);
    }

    @Override
    public ListIterator<T> listIterator(final int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: " + index);

        return new ListIterator<T>() {
            private Node<T> lastReturned = null;
            private Node<T> next = (index == size) ? null : findNode(index);
            private int nextIndex = index;

            @Override
            public boolean hasNext() {
                return nextIndex < size;
            }

            @Override
            public T next() {
                if (!hasNext())
                    throw new NoSuchElementException();
                lastReturned = next;
                next = next.next;
                nextIndex++;
                return lastReturned.data;
            }

            @Override
            public boolean hasPrevious() {
                return nextIndex > 0;
            }

            @Override
            public T previous() {
                if (!hasPrevious())
                    throw new NoSuchElementException();
                next = (next == null) ? tail : next.prev;
                lastReturned = next;
                nextIndex--;
                return lastReturned.data;
            }

            @Override
            public int nextIndex() {
                return nextIndex;
            }

            @Override
            public int previousIndex() {
                return nextIndex - 1;
            }

            @Override
            public void remove() {
                if (lastReturned == null)
                    throw new IllegalStateException();
                Node<T> nextNode = lastReturned.next;
                Node<T> prevNode = lastReturned.prev;

                if (prevNode != null)
                    prevNode.next = nextNode;
                else
                    head.next = nextNode;

                if (nextNode != null)
                    nextNode.prev = prevNode;
                else
                    tail = prevNode;

                if (next == lastReturned)
                    next = nextNode;
                else
                    nextIndex--;

                lastReturned = null;
                size--;
            }

            @Override
            public void set(T e) {
                if (lastReturned == null)
                    throw new IllegalStateException();
                lastReturned.data = e;
            }

            @Override
            public void add(T e) {
                Node<T> newNode = new Node<>(e, next.prev, next);
                if (next.prev != null)
                    next.prev.next = newNode;
                else
                    head.next = newNode;

                if (next != null)
                    next.prev = newNode;
                else
                    tail = newNode;

                lastReturned = null;
                nextIndex++;
                size++;
            }
        };
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > size || fromIndex > toIndex)
            throw new IndexOutOfBoundsException("fromIndex: " + fromIndex + ", toIndex: " + toIndex);

        LinkedList<T> subList = new LinkedList<>();
        Node<T> startNode = findNode(fromIndex);
        for (int i = fromIndex; i < toIndex; i++) {
            subList.add(startNode.data);
            startNode = startNode.next;
        }
        return subList;
    }
    
    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();
        list.add(2);
        list.add(10);
        list.add(4);

        list.stream().forEach(System.out::println);
    }
}
