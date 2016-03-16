package it.clicktoprofit.email;

import java.util.ArrayList;

/**
 * Created by david on 17/01/2016.
 */
public class PaymentAdded extends AbstractEmail {

    public PaymentAdded(ArrayList<String> arr) {
        this.email_from = "pagamenti@click2profit.it";
        this.email_to = arr.get(0);
        this.subject = "Pagamento aggiunto - Click2profit";
        this.body = "<p>Le è stato aggiunto il seguente pagamento:</p><p>Metodo: " + arr.get(1) + "<br>Importo: " + arr.get(2) +
                ".00 &euro;<br>Data: " + arr.get(3) + "</p><p>Di conseguenza tale importo è stato sottratto dal suo saldo.</p>" +
                "<br>" +
                "<p>Grazie,</p>" +
                "<p><a href=\"http://click2profit.it\" target=\"_blank\">Click2profit</a> Staff</p>";
    }

}
