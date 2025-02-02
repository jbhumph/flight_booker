package pQueue;

public class PQueue<T> {
    private static class node<T> {
        T data;
        node<T> next;
        int priority;

        public node(T data) {
            this.data = data;
            this.next = null;
            this.priority = 0;
        }
    }

    private node<T> front;
    private node<T> rear;
    private int size;

    public PQueue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    public void enqueue(T data) {
        node<T> newNode = new node<>(data);
        if (rear == null) {
            front = newNode;
            rear = newNode;
            size++;
            return;
        }
        rear.next = newNode;
        rear = newNode;
        size++;
    }

    public void enqueuePriority(T data) {
        node<T> newNode = new node<>(data);
        if (front == null) {
            front = newNode;
            rear = newNode;
            size++;
            return;
        }
        if (front.priority < newNode.priority) {
            newNode.next = front;
            front = newNode;
            size++;
            return;
        } else {
            node<T> current = front;
            while (current.next != null && current.next.priority >= newNode.priority) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
            size++;
        }
    }

    public void dequeue() {
        if (front == null) {
            return ;
        }
        front = front.next;
        size--;
    }

    public T peek() {
        if (front == null) {
            return null;
        }
        return front.data;
    }

    public int getSize() {
        return size;
    }




}
