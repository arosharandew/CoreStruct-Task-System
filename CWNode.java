public class CWNode {
    CWTask task;
    CWNode next;

    public CWNode(CWTask task) {
        this.task = task;
        this.next = null;
    }

    public CWTask getTask() {
        return task;
    }
    public CWNode getNext() {
        return next;
    }
    public void setNext(CWNode next) {
        this.next = next;
    }
}