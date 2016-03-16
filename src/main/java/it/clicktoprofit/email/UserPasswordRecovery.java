package it.clicktoprofit.email;

import java.util.ArrayList;

/**
 * Created by david on 17/01/2016.
 */
public class UserPasswordRecovery extends AbstractEmail {

    public UserPasswordRecovery(ArrayList<String> arr) {
        this.email_to = arr.get(0);
        this.email_from = "no-reply@click2profit.it";
        this.subject = "Recupero password - Click2profit";
        this.body = "<p>La password provvisoria è <b>" + arr.get(1) + "</b>. E' altamente consigliata una veloce " +
                "modifica di quest'ultima.</p>" +
                "<br>" +
                "<p>Grazie,</p>" +
                "<p><a href=\"http://click2profit.it\" target=\"_blank\">Click2profit</a> Staff</p>" +
                "<br><hr>" +
                "<p><b>Non rispondere a questa email!</b> " +
                "E' un servizio automatico di Click2profit.it</p>";
    }

}
