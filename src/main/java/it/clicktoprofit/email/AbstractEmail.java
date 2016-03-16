package it.clicktoprofit.email;

import java.util.ArrayList;

public abstract class AbstractEmail extends Thread {

    protected String email_from, email_to, subject, body;

    @Override
    public void run() {
        send();
    }

    protected void send() {
        ArrayList<String> data = new ArrayList<>();
        data.add(email_from);
        data.add(email_to);
        data.add(subject);
        data.add(body);
        Email.send(data);
    }

}