package ge.exen.listeners;

import ge.exen.models.Message;

public interface IChatListener {
    void messageReceived(Message msg);
}