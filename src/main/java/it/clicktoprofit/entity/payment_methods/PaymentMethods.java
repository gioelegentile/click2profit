package it.clicktoprofit.entity.payment_methods;

import it.clicktoprofit.data_access.DAO;
import org.apache.commons.lang3.text.WordUtils;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Gioele on 31/12/2015.
 */
public class PaymentMethods implements RI_PaymentMethods, WI_PaymentMethods{

    private String method;

    public boolean setPaymentMethodData(String str) {

        if(true) {

            method = WordUtils.capitalizeFully(str);

            return true;
        } else {
            return false;
        }

    }

    public ArrayList<ArrayList<String>> getPaymentMethods() throws SQLException {
        DAO dao = new DAO();
        return dao.getPaymentMethods();
    }

    public void addPaymentMethod() throws SQLException {
        DAO dao = new DAO();
        dao.addPaymentMethod(method);
    }

    public boolean deletePaymentMethod(String id) throws SQLException {
        DAO dao = new DAO();
        return dao.deletePaymentMethod(id);
    }

}
