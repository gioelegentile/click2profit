package it.clicktoprofit.entity.payment;

import it.clicktoprofit.data_access.DAO;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Gioele on 31/12/2015.
 */
public class Payment implements RI_Payment, WI_Payment {

    private String idPaymentMethod, idUser, payment, date;

    public boolean setPaymentData(ArrayList<String> arr) {

        if(true) {

            idPaymentMethod = arr.get(0);
            idUser = arr.get(1);
            payment = arr.get(2);
            date = arr.get(3);

            return true;
        } else {
            return false;
        }

    }

    public ArrayList<ArrayList<String>> getUserPayments(String idUser) throws SQLException {
        DAO dao = new DAO();
        return dao.getUserPayments(idUser);
    }

    public void addPayment() throws SQLException {
        DAO dao = new DAO();
        dao.addPayment(idPaymentMethod, idUser, payment, date);
    }

    public boolean deletePayment(String idUser, String idCampaign) throws SQLException {
        DAO dao = new DAO();
        return dao.deletePayment(idUser, idCampaign);
    }

}
