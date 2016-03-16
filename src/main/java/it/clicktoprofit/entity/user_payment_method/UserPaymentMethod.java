package it.clicktoprofit.entity.user_payment_method;

import it.clicktoprofit.data_access.DAO;
import org.apache.commons.lang3.text.WordUtils;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Gioele on 31/12/2015.
 */
public class UserPaymentMethod implements RI_UserPaymentMethod, WI_UserPaymentMethod {

    private String idUser, idPaymentMethod, notes;

    public boolean setUserPaymentMethodData(ArrayList<String> arr) {

        if (true) {

            idUser = arr.get(0);
            idPaymentMethod = arr.get(1);
            notes = arr.get(2).length() >= 2 ? WordUtils.capitalize(arr.get(2).substring(0,2)) + arr.get(2).substring(2) : arr.get(2).toUpperCase();

            return true;
        } else {
            return false;
        }

    }

    public ArrayList<ArrayList<String>> getUserPaymentMethods(String id) throws SQLException {
        DAO dao = new DAO();
        return dao.getUserPaymentMethods(id);
    }

    public void addUserPaymentMethod() throws SQLException {
        DAO dao = new DAO();
        dao.addUserPaymentMethod(idUser, idPaymentMethod, notes);
    }

    public boolean deleteUserPaymentMethod(String idUser, String idPaymentMethod) throws SQLException {
        DAO dao = new DAO();
        return dao.deleteUserPaymentMethod(idUser, idPaymentMethod);
    }

}
