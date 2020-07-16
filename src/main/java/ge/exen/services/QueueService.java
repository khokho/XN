package ge.exen.services;

import ge.exen.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class QueueService implements IQueueService {
    private User firstInQueue;
    private ArrayList<User> waitingStudents;

    @Autowired
    UserService userService;

    public QueueService() {
        waitingStudents = new ArrayList<>();
    }

    @Override
    public void enqueue() {
        User currentStudent = userService.getCurrentUser();
        waitingStudents.add(currentStudent);
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

    @Override
    public boolean checkQueueStatus() {
        User currentStudent = userService.getCurrentUser();
        return currentStudent.getId() == firstInQueue.getId();
    }

    @Override
    public void cancelWaiting() {
        User currentStudent = userService.getCurrentUser();
        for (int i = 0; i < waitingStudents.size(); i++) {
            User student = waitingStudents.get(i);
            if (student.getId() == currentStudent.getId())
                waitingStudents.remove(i);
        }
    }

    @Override
    public User dequeue() {
        if (waitingStudents.size() != 0) {
            firstInQueue = waitingStudents.get(0);
            waitingStudents.remove(0);
            return firstInQueue;
        }
        return null;
    }

    @Override
    public void clearQueue() {
        for (int i = waitingStudents.size() - 1; i >= 0; i--) {
            User student = waitingStudents.get(i);
            waitingStudents.remove(i);
        }
    }
}
