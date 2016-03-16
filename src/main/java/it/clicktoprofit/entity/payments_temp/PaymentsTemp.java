package it.clicktoprofit.entity.payments_temp;

import it.clicktoprofit.data_access.DAO;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Gioele on 07/02/2016.
 */
public class PaymentsTemp implements RI_PaymentsTemp, WI_PaymentsTemp {

    private String idUser, paymentMethod, notes, payment;

    @Override
    public ArrayList<ArrayList<String>> getPaymentsTemp() throws SQLException {
        DAO dao = new DAO();
        return dao.getPaymentsTemp();
    }

    @Override
    public boolean setPaymentTempData(ArrayList<String> arr) {

        if(true) {
            paymentMethod = arr.get(0);
            notes = arr.get(1);
            payment = arr.get(2);
            idUser = arr.get(3);
            return true;
        } else {
            return false;
        }

    }

    @Override
    public void addPaymentTemp() throws SQLException {
        DAO dao = new DAO();
        dao.addPaymentTemp(paymentMethod, notes, payment, idUser);
    }

    @Override
    public boolean deletePaymentTemp(String idUser) throws SQLException {
        DAO dao = new DAO();
        return dao.deletePaymentTemp(idUser);
    }

}
