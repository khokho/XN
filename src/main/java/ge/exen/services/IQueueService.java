package ge.exen.services;

import ge.exen.listeners.IQueueListener;
import ge.exen.models.User;

public interface IQueueService {
    /**
     * adds student in the queue
     */
    void enqueue();

    /**
     * @return returns number of students which are in the queue before current one
     */
    int getAnticipants();

    /**
     * @return true if current user's turn has come,
     * false if the user is still waiting
     */
    boolean checkQueueStatus();

    /**
     * removes student from the queue
     */
    void cancelWaiting();

    /**
     * returns first student in the queue and removes from the queue
     *
     * @return User representing first student
     * null if no students are waiting
     */
    User dequeue();

    /**
     * removes all students from the queue
     * mostly, in the end of an exam
     */
    void clearQueue();

    /**
     * returns whether the queue is empty
     *
     * @return true if no students are waiting, false otherwise
     */
    boolean isEmpty();

    /**
     * adds the QueueListener
     */
    void addListener(IQueueListener listener);

    /**
     * removes the QueueListener
     */
    void removeListener(IQueueListener listener);

    /**
     * creates and keeps track of the enqueue and cancel button states
     */
    void createDisabledStates();

    /**
     * returns whether the current user is in the queue
     *
     * @param queueType String representing the queue
     * @return false if the enqueue button for the current user is not disabled
     *              and cancel button is disabled,
     *          true if the opposite
     */
    Boolean getDisabledState(String queueType);

    /**
     * changes Disabled status of the given queue enqueue and cancel buttons
     *
     * @param queueType String representing the queue
     */
    void changeDisabledState(String queueType);
}
