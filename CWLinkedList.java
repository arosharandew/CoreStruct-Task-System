import java.util.Arrays;

public class CWLinkedList {
    private CWNode head;
    private CWNode tail;
    private int size;

    public CWLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    // add task
    public void add(CWTask task) {
        CWNode newNode = new CWNode(task);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
    }

    // remove task-id
    public boolean removeById(int id) {
        if (head == null) return false;

        if (head.getTask().getId() == id) {
            head = head.getNext();
            if (head == null) {
                tail = null;
            }
            size--;
            return true;
        }

        CWNode current = head;
        while (current.getNext() != null) {
            if (current.getNext().getTask().getId() == id) {
                CWNode toRemove = current.getNext();
                current.setNext(toRemove.getNext());

                if (toRemove == tail) {
                    tail = current;
                }

                size--;
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    // get task
    public CWTask getById(int id) {
        CWNode current = head;
        while (current != null) {
            if (current.getTask().getId() == id) {
                return current.getTask();
            }
            current = current.getNext();
        }
        return null;
    }

    // sorting-bubble
    public void bubbleSortByPriority() {
        if (head == null || head.getNext() == null) return;

        boolean swapped;
        do {
            CWNode current = head;
            CWNode previous = null;
            CWNode next = head.getNext();
            swapped = false;

            while (next != null) {
                if (comparePriority(current.getTask(), next.getTask()) > 0) {
                    swapped = true;
                    current.setNext(next.getNext());
                    next.setNext(current);

                    if (previous == null) {
                        head = next;
                    } else {
                        previous.setNext(next);
                    }

                    previous = next;
                    next = current.getNext();

                    if (next == null) {
                        tail = current;
                    }
                } else {
                    previous = current;
                    current = next;
                    next = next.getNext();
                }
            }
        } while (swapped);
    }

    private int comparePriority(CWTask a, CWTask b) {
        String[] priorityOrder = {"high", "medium", "low"};
        int aPriority = Arrays.asList(priorityOrder).indexOf(a.getPriority().toLowerCase());
        int bPriority = Arrays.asList(priorityOrder).indexOf(b.getPriority().toLowerCase());
        return aPriority - bPriority;
    }

    // listing
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

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }
}