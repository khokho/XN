package ge.exen.services;

public interface QueueListener {
    /**
     * is called when the queue is updated
     *
     * @param type int representing the type of update
     */
    void fireQueueUpdate(int type);
}
