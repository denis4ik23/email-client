package ru.ifmo.email.client.EClient;

import ru.ifmo.email.client.EmailClient;
import ru.ifmo.email.client.exception.EmailClientException;
import ru.ifmo.email.model.Message;

import java.util.List;

public class EClient implements EmailClient {
    @Override
    public void registerMyEmail(String email) throws EmailClientException {

    }

    @Override
    public void send(Message message, String recipient) throws EmailClientException {

    }

    @Override
    public List<Message> loadEmails() throws EmailClientException {
        return null;
    }
}
