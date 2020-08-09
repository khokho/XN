package ge.exen.services;

import ge.exen.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:testing-setup.xml" })

public class QueueServiceTest {
    @Qualifier("queueService")
    @Autowired
    private QueueService qservice;

    private User ana,tamta,sandro,shota,vano;

    @BeforeEach
    public void setUp(){
        ana = new User("adavi18@freeuni.edu.ge", "anasparoli",
                "student", "Ana", "Davitashvili");
        ana.setId(1);

        tamta = new User("ttopu18@freeuni.edu.ge", "dzaanmagariparoli",
                "student", "Tamta", "Topuria");
        tamta.setId(2);

        sandro = new User("alkhok18@freeuni.edu.ge", "martlahashivar",
                "student", "Sandro", "Khokhiashvili");
        sandro.setId(3);

        shota = new User("selka18@freeuni.edu.ge", "eddievedder123",
                "student", "Shota", "Elkanishvili");
        shota.setId(4);

        vano = new User("vgame18@freeuni.edu.ge", "damhacketumagarixar",
                "student", "Vano", "Gamezardashvili");
        vano.setId(5);
    }

    @Test
    @DirtiesContext
    public void testIsEmpty(){
        assertTrue(qservice.isEmpty());

        qservice.enqueueCurrentStudent(tamta);
        assertFalse(qservice.isEmpty());
    }

    @Test
    @DirtiesContext
    public void testGetAnticipants(){
        qservice.enqueueCurrentStudent(tamta);
        assertEquals(0, qservice.getAnticipantsCurrentUser(tamta));

        qservice.enqueueCurrentStudent(sandro);
        assertEquals(0, qservice.getAnticipantsCurrentUser(tamta));
        assertEquals(1, qservice.getAnticipantsCurrentUser(sandro));

        qservice.enqueueCurrentStudent(ana);

        assertEquals(1, qservice.getAnticipantsCurrentUser(sandro));
        assertEquals(2, qservice.getAnticipantsCurrentUser(ana));
    }

    @Test
    @DirtiesContext
    public void testGetAnticipantsNotInQueue(){
        assertEquals(0, qservice.getAnticipantsCurrentUser(shota));

        qservice.enqueueCurrentStudent(tamta);
        qservice.enqueueCurrentStudent(sandro);
        qservice.enqueueCurrentStudent(vano);

        assertEquals(3, qservice.getAnticipantsCurrentUser(shota));
    }

    @Test
    @DirtiesContext
    public void testCancelWaiting(){
        qservice.enqueueCurrentStudent(ana);
        qservice.cancelWaitingCurrentStudent(ana);

        assertTrue(qservice.isEmpty());
    }

    @Test
    @DirtiesContext
    public void testCancelWaitingForFirst(){
        qservice.enqueueCurrentStudent(shota);
        qservice.enqueueCurrentStudent(tamta);
        qservice.enqueueCurrentStudent(sandro);
        qservice.enqueueCurrentStudent(vano);

        qservice.cancelWaitingCurrentStudent(shota);
        assertEquals(0, qservice.getAnticipantsCurrentUser(tamta));
        assertEquals(2, qservice.getAnticipantsCurrentUser(vano));
        assertEquals(3, qservice.getAnticipantsCurrentUser(ana));
        assertEquals(3, qservice.getAnticipantsCurrentUser(shota));
    }

    @Test
    @DirtiesContext
    public void testCancelWaitingForLast(){
        qservice.enqueueCurrentStudent(shota);
        qservice.enqueueCurrentStudent(tamta);
        qservice.enqueueCurrentStudent(sandro);
        qservice.enqueueCurrentStudent(vano);

        qservice.cancelWaitingCurrentStudent(vano);
        assertEquals(0, qservice.getAnticipantsCurrentUser(shota));
        assertEquals(2, qservice.getAnticipantsCurrentUser(sandro));
        assertEquals(3, qservice.getAnticipantsCurrentUser(ana));
    }

    @Test
    @DirtiesContext
    public void testCancelWaitingForMiddle(){
        qservice.enqueueCurrentStudent(shota);
        qservice.enqueueCurrentStudent(tamta);
        qservice.enqueueCurrentStudent(sandro);
        qservice.enqueueCurrentStudent(vano);

        qservice.cancelWaitingCurrentStudent(sandro);
        assertEquals(1, qservice.getAnticipantsCurrentUser(tamta));
        assertEquals(2, qservice.getAnticipantsCurrentUser(vano));
        assertEquals(3, qservice.getAnticipantsCurrentUser(ana));
    }

    @Test
    @DirtiesContext
    public void testDequeue(){
        assertNull(qservice.dequeue());

        qservice.enqueueCurrentStudent(shota);
        assertEquals(shota.getId(), qservice.dequeue().getId());
        assertNull(qservice.dequeue());

        qservice.enqueueCurrentStudent(tamta);
        qservice.enqueueCurrentStudent(sandro);
        qservice.enqueueCurrentStudent(vano);
        assertEquals(tamta.getId(), qservice.dequeue().getId());
        assertEquals(sandro.getId(), qservice.dequeue().getId());
        assertEquals(vano.getId(), qservice.dequeue().getId());
        assertNull(qservice.dequeue());
    }

    @Test
    @DirtiesContext
    public void testDequeueWithCancelWaiting(){
        qservice.enqueueCurrentStudent(vano);
        qservice.enqueueCurrentStudent(shota);
        qservice.enqueueCurrentStudent(ana);
        assertEquals(vano.getId(), qservice.dequeue().getId());

        qservice.cancelWaitingCurrentStudent(shota);
        assertEquals(ana.getId(), qservice.dequeue().getId());

        qservice.enqueueCurrentStudent(sandro);
        qservice.enqueueCurrentStudent(tamta);
        qservice.cancelWaitingCurrentStudent(tamta);
        assertEquals(sandro.getId(), qservice.dequeue().getId());
    }

    @Test
    @DirtiesContext
    public void testCheckQueueStatus(){
        assertFalse(qservice.checkQueueStatusCurrentStudent(ana));

        qservice.enqueueCurrentStudent(shota);
        qservice.enqueueCurrentStudent(vano);

        User firstInQueue = qservice.dequeue();
        assertEquals(shota.getId(), firstInQueue.getId());
        assertTrue(qservice.checkQueueStatusCurrentStudent(shota));
        assertFalse(qservice.checkQueueStatusCurrentStudent(sandro));
        assertFalse(qservice.checkQueueStatusCurrentStudent(vano));

        firstInQueue = qservice.dequeue();
        assertEquals(vano.getId(), firstInQueue.getId());
        assertTrue(qservice.checkQueueStatusCurrentStudent(vano));
        assertFalse(qservice.checkQueueStatusCurrentStudent(sandro));
        assertFalse(qservice.checkQueueStatusCurrentStudent(tamta));

        qservice.enqueueCurrentStudent(tamta);
        assertTrue(qservice.checkQueueStatusCurrentStudent(vano));
        assertFalse(qservice.checkQueueStatusCurrentStudent(tamta));
    }

    @Test
    @DirtiesContext
    public void testClearQueue(){
        qservice.enqueueCurrentStudent(ana);
        qservice.enqueueCurrentStudent(tamta);
        qservice.enqueueCurrentStudent(shota);
        qservice.enqueueCurrentStudent(vano);
        qservice.enqueueCurrentStudent(sandro);

        User firstInQueue = qservice.dequeue();
        assertEquals(ana.getId(), firstInQueue.getId());
        assertTrue(qservice.checkQueueStatusCurrentStudent(ana));

        qservice.clearQueue();

        assertTrue(qservice.isEmpty());

        assertFalse(qservice.checkQueueStatusCurrentStudent(ana));
        assertFalse(qservice.checkQueueStatusCurrentStudent(tamta));

        assertEquals(0, qservice.getAnticipantsCurrentUser(tamta));
        assertEquals(0, qservice.getAnticipantsCurrentUser(vano));
    }

    @Test
    @DirtiesContext
    public void testGetDisabledState(){
        qservice.enqueueCurrentStudent(ana);
        qservice.enqueueCurrentStudent(tamta);

        assertTrue(qservice.getDisabledStateForCurrentUser(ana));
        assertTrue(qservice.getDisabledStateForCurrentUser(tamta));

        assertFalse(qservice.getDisabledStateForCurrentUser(sandro));
        assertFalse(qservice.getDisabledStateForCurrentUser(vano));
        assertFalse(qservice.getDisabledStateForCurrentUser(sandro));
    }

    @Test
    @DirtiesContext
    public void testGetDisabledStateCancelWaiting(){
        qservice.enqueueCurrentStudent(ana);
        qservice.enqueueCurrentStudent(tamta);
        assertTrue(qservice.getDisabledStateForCurrentUser(tamta));

        qservice.cancelWaitingCurrentStudent(tamta);

        assertTrue(qservice.getDisabledStateForCurrentUser(ana));
        assertFalse(qservice.getDisabledStateForCurrentUser(tamta));
        assertFalse(qservice.getDisabledStateForCurrentUser(shota));
        assertFalse(qservice.getDisabledStateForCurrentUser(vano));
    }

    @Test
    @DirtiesContext
    public void testGetDisabledStateDequeue(){
        qservice.enqueueCurrentStudent(shota);
        qservice.enqueueCurrentStudent(ana);
        qservice.enqueueCurrentStudent(tamta);

        qservice.dequeue();

        assertFalse(qservice.getDisabledStateForCurrentUser(shota));
        assertTrue(qservice.getDisabledStateForCurrentUser(ana));
        assertTrue(qservice.getDisabledStateForCurrentUser(tamta));
        assertFalse(qservice.getDisabledStateForCurrentUser(sandro));
    }
}
