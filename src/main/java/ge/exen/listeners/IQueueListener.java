package ge.exen.listeners;

public interface IQueueListener {
    /**
     * is called when the queue is updated
     *
     * @param type int representing the type of update
     */
    void fireQueueUpdate(String type);

    /**
     * @param userid user id
     * @param name queue name
     */
    void fireQueuePop(long userid, String name);
}