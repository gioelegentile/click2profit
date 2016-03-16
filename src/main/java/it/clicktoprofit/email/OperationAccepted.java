package it.clicktoprofit.email;

import java.util.ArrayList;

/**
 * Created by david on 17/01/2016.
 */
public class OperationAccepted extends AbstractEmail {

    public OperationAccepted(ArrayList<String> arr) {
        this.subject = "Operazione accettata - Click2profit";
        this.email_from = "no-reply@click2profit.it";
        this.email_to = arr.get(0);
        this.body = "<p>La seguente operazione da lei eseguita:</p><p>Data: " + arr.get(5) + "<br>Ora: " + arr.get(6) +
                "<br>Azienda: " + arr.get(1) + "<br>Tipo: " + arr.get(2) + "<br>Link: " +
                arr.get(3) + "</p><p>E' stata accettata! Sono stati aggiunti " + arr.get(4) + "&euro; al suo saldo.</p>" +
                "<br>" +
                "<p>Grazie,</p>" +
                "<p><a href=\"http://click2profit.it\" target=\"_blank\">Click2profit</a> Staff</p>" +
                "<br><hr>" +
                "<p><b>Non rispondere a questa email!</b> " +
                "E' un servizio automatico di Click2profit.it</p>";
    }

}
