package ge.exen.services;

import ge.exen.listeners.IQueueListener;
import ge.exen.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class QueueService implements IQueueService {
    private User firstInQueue;
    private final ArrayList<User> waitingStudents;
    private final ArrayList<IQueueListener> listeners;

    public static final String ATTR_NAME = "Queue Type";
    public static final String BTNS_ATTR_NAME = "Buttons";
    public static final int POP = 0;
    public static final int PUSH = 1;
    public static final int CANCEL = 2;
    public static final int CLEAR = 3;


    @Autowired
    UserService userService;

    @Autowired
    HttpSession session;


    public QueueService() {
        waitingStudents = new ArrayList<>();
        listeners = new ArrayList<>();
    }

    @Override
    public void createDisabledStates() {
        HashMap<String, Boolean> buttons = new HashMap<>();
        buttons.put(BlankPaperQueueService.TYPE,  false);
        buttons.put(CallExamerQueueService.TYPE,  false);
        buttons.put(WCQueueService.TYPE,  false);

        session.setAttribute(BTNS_ATTR_NAME, buttons);
    }

    @Override
    public Boolean getDisabledState(String queueType) {
        HashMap<String, Boolean> buttons = (HashMap<String, Boolean>) session.getAttribute(BTNS_ATTR_NAME);
        return buttons.get(queueType);
    }

    @Override
    public void changeDisabledState(String queueType){
        HashMap<String, Boolean> buttons = (HashMap<String, Boolean>) session.getAttribute(BTNS_ATTR_NAME);
        boolean currentStatus = buttons.get(queueType);
        buttons.put(queueType, !currentStatus);
        session.setAttribute(BTNS_ATTR_NAME, buttons);
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
    public synchronized int getAnticipants() {
        int aheadOfCurrent = 0;
        User currentStudent = userService.getCurrentUser();

        if (!currentStudent.getStatus().equals("student"))
            return waitingStudents.size();

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
    public synchronized boolean checkQueueStatus() {
        User currentStudent = userService.getCurrentUser();
        return firstInQueue != null && currentStudent.getId() == firstInQueue.getId();
    }

    public boolean checkQueueStatusCurrentStudent(User currentStudent) {
        return firstInQueue != null && currentStudent.getId() == firstInQueue.getId();
    }


    @Override
    public synchronized void cancelWaiting() {
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
    public synchronized User dequeue() {
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
    public void addListener(IQueueListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(IQueueListener listener) {
        for (int i = 0; i < listeners.size(); i++) {
            IQueueListener current = listeners.get(i);
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
        for (IQueueListener listener : listeners) {
            listener.fireQueueUpdate(getType());
        }
    }

    public String getType() {
        return null;
    }
}
