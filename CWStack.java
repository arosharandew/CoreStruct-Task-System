public class CWStack {
    private final CWLinkedList list;

    public CWStack() {
        list = new CWLinkedList();
    }

    // push-add
    public void push(CWTask task) {
        list.add(task);
    }

    // pop-remove
    public CWTask pop() {
        if (list.isEmpty()) return null;

        // last task
        CWTask[] tasks = list.getAllTasks();
        CWTask task = tasks[tasks.length - 1];
        list.removeById(task.getId());
        return task;
    }

    //peek-last value
    public CWTask peek() {
        if (list.isEmpty()) return null;

        CWTask[] tasks = list.getAllTasks();
        return tasks[tasks.length - 1];
    }

    public boolean isEmpty() { return list.isEmpty(); }
    public int size() { return list.size(); }
}