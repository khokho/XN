package ge.exen.services;

import ge.exen.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class QueueService implements IQueueService {
    private User firstInQueue;
    private ArrayList<User> waitingStudents;
    private ArrayList<QueueListener> listeners;

    public static final int POP = 0;
    public static final int PUSH = 1;
    public static final int CANCEL = 2;
    public static final int CLEAR = 3;

    @Autowired
    UserService userService;

    public QueueService() {
        waitingStudents = new ArrayList<>();
        listeners = new ArrayList<>();
    }

    @Override
    public void enqueue() {
        User currentStudent = userService.getCurrentUser();
        waitingStudents.add(currentStudent);
        notifyListeners(PUSH);
    }

    public void enqueueCurrentStudent(User currentStudent) {
        waitingStudents.add(currentStudent);
        notifyListeners(PUSH);
    }

    @Override
    public int getAnticipants() {
        int aheadOfCurrent = 0;
        User currentStudent = userService.getCurrentUser();
        for (User student : waitingStudents) {
            if (student.getId() == currentStudent.getId()) break;
            aheadOfCurrent++;
        }
        return aheadOfCurrent;
    }

    public int getAnticipantsCurrentUser(User currentStudent) {
        int aheadOfCurrent = 0;
        for (User student : waitingStudents) {
            if (student.getId() == currentStudent.getId()) break;
            aheadOfCurrent++;
        }
        return aheadOfCurrent;
    }

    @Override
    public boolean checkQueueStatus() {
        User currentStudent = userService.getCurrentUser();
        return firstInQueue != null && currentStudent.getId() == firstInQueue.getId();
    }

    public boolean checkQueueStatusCurrentStudent(User currentStudent) {
        return firstInQueue != null && currentStudent.getId() == firstInQueue.getId();
    }


    @Override
    public void cancelWaiting() {
        User currentStudent = userService.getCurrentUser();
        for (int i = 0; i < waitingStudents.size(); i++) {
            User student = waitingStudents.get(i);
            if (student.getId() == currentStudent.getId()) {
                waitingStudents.remove(i);
                break;
            }
        }
        notifyListeners(CANCEL);
    }

    public void cancelWaitingCurrentStudent(User currentStudent) {
        for (int i = 0; i < waitingStudents.size(); i++) {
            User student = waitingStudents.get(i);
            if (student.getId() == currentStudent.getId()) {
                waitingStudents.remove(i);
                break;
            }
        }
        notifyListeners(CANCEL);
    }


    @Override
    public User dequeue() {
        if (waitingStudents.size() != 0) {
            firstInQueue = waitingStudents.get(0);
            waitingStudents.remove(0);
            notifyListeners(POP);
            return firstInQueue;
        }
        return null;
    }

    @Override
    public void clearQueue() {
        waitingStudents.clear();
        notifyListeners(CLEAR);
        firstInQueue = null;
    }

    @Override
    public boolean isEmpty() {
        return waitingStudents.size() == 0;
    }

    @Override
    public void addListener(QueueListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(QueueListener listener) {
        for (int i = 0; i < listeners.size(); i++) {
            QueueListener current = listeners.get(i);
            if (current.equals(listener)) {
                listeners.remove(i);
                break;
            }
        }
    }

    /*
     * notifys all listeners when queue is updated
     */
    private void notifyListeners(int type) {
        for (QueueListener listener : listeners) {
            listener.fireQueueUpdate(type);
        }
    }
}
