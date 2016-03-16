package it.clicktoprofit.entity.payment;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Gioele on 31/12/2015.
 */
public interface WI_Payment {

    boolean setPaymentData(ArrayList<String> arr);

    void addPayment() throws SQLException;

    boolean deletePayment(String idUser, String idCampaign) throws SQLException;

}
