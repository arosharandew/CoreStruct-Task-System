public class CWQueue {
    private CWNode head;
    private CWNode tail;
    private int size;

    public CWQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    // add to back
    public void enqueue(CWTask task) {
        CWNode newNode = new CWNode(task);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
    }

    // remove from front
    public CWTask dequeue() {
        if (head == null) {
            return null;
        }

        CWTask task = head.getTask();
        head = head.getNext();

        if (head == null) {
            tail = null;
        }

        size--;
        return task;
    }

    // peek
    public CWTask peek() {
        if (head == null) return null;
        return head.getTask();
    }

    public boolean isEmpty() { return size == 0; }
    public int size() { return size; }

    // added order
    public CWTask[] getAllTasks() {
        CWTask[] tasks = new CWTask[size];
        CWNode current = head;
        int index = 0;
        while (current != null) {
            tasks[index++] = current.getTask();
            current = current.getNext();
        }
        return tasks;
    }
}