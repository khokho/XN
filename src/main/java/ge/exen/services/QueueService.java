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

    public static final String ATTR_NAME = "Queue Type";
    public static final String BTNS_ATTR_NAME = "Buttons";

    public static final int POP = 0;
    public static final int PUSH = 1;
    public static final int CANCEL = 2;
    public static final int CLEAR = 3;

    public static final boolean NOT_IN_QUEUE = false;
    public static final boolean IN_QUEUE = true;

    private User firstInQueue;
    private ArrayList<User> waitingStudents;
    private ArrayList<IQueueListener> listeners;
    private HashMap<Long, Boolean> disabledStates;

    @Autowired
    UserService userService;

    @Autowired
    HttpSession session;


    public QueueService() {
        waitingStudents = new ArrayList<>();
        listeners = new ArrayList<>();
        disabledStates = new HashMap<>();
    }

    @Override
    public synchronized void setDisabledState(User user, boolean state) {
        Long currentId = user.getId();
        disabledStates.put(currentId, state);
    }

    @Override
    public synchronized Boolean getDisabledState() {
        User currentUser = userService.getCurrentUser();
        if (!disabledStates.containsKey(currentUser.getId())) {
            setDisabledState(currentUser, NOT_IN_QUEUE);
        }
        return disabledStates.get(currentUser.getId());
    }

    protected Boolean getDisabledStateForCurrentUser(User currentUser) {
        if (!disabledStates.containsKey(currentUser.getId())) {
            setDisabledState(currentUser, NOT_IN_QUEUE);
        }
        return disabledStates.get(currentUser.getId());
    }

    @Override
    public synchronized void enqueue() {
        User currentStudent = userService.getCurrentUser();
        waitingStudents.add(currentStudent);
        setDisabledState(currentStudent, IN_QUEUE);
        notifyListeners(PUSH);
    }

    protected void enqueueCurrentStudent(User currentStudent) {
        waitingStudents.add(currentStudent);
        setDisabledState(currentStudent, IN_QUEUE);
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

    protected int getAnticipantsCurrentUser(User currentStudent) {
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

    protected boolean checkQueueStatusCurrentStudent(User currentStudent) {
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
        setDisabledState(currentStudent, NOT_IN_QUEUE);
        notifyListeners(CANCEL);
        //changeDisabledState(getType());
    }

    protected void cancelWaitingCurrentStudent(User currentStudent) {
        for (int i = 0; i < waitingStudents.size(); i++) {
            User student = waitingStudents.get(i);
            if (student.getId() == currentStudent.getId()) {
                waitingStudents.remove(i);
                break;
            }
        }
        setDisabledState(currentStudent, NOT_IN_QUEUE);
        notifyListeners(CANCEL);
    }


    @Override
    public synchronized User dequeue() {
        if (waitingStudents.size() != 0) {
            firstInQueue = waitingStudents.get(0);
            waitingStudents.remove(0);
            notifyListeners(POP);
            notifyPop(firstInQueue.getId());
            setDisabledState(firstInQueue, NOT_IN_QUEUE);
            return firstInQueue;
        }
        return null;
    }

    @Override
    public synchronized void clearQueue() {
        waitingStudents.clear();
        disabledStates.replaceAll((key, oldValue) -> NOT_IN_QUEUE);
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

    /**
     * notifys all listeners when queue is updated
     */
    private void notifyListeners(int type) {
        System.out.println("notifyiiing");
        for (IQueueListener listener : listeners) {
            System.out.println(getType());
            listener.fireQueueUpdate(getType());
        }
    }

    /**
     * notifys all listeners when queue is updated
     */
    private void notifyPop(long userid) {
        System.out.println("notifyiiing");
        for (IQueueListener listener : listeners) {
            System.out.println(getType());
            listener.fireQueuePop(userid, getType());
        }
    }



    public String getType() {
        return null;
    }
}
