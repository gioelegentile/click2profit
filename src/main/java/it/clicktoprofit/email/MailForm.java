package it.clicktoprofit.email;

/**
 * Created by david on 17/01/2016.
 */
public class MailForm extends AbstractEmail{

    public MailForm(String subject, String body, String email_from) {
        this.email_from = "no-reply@click2profit.it";
        this.email_to = "info@click2profit.it";
        this.subject = "Richiesta informazioni - Click2profit";
        this.body = "<p>Da: " + email_from + "</p><p>Oggetto: " + subject + "</p><p>" + body + "</p>"
                + "<br><hr>" +
                "<p><b>Non rispondere a questa email!</b> Clicca sull'indirizzo email quì sopra per rispondere. " +
                "E' un servizio automatico di Click2profit.it</p>";
    }

}
