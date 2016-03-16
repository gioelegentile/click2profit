package it.clicktoprofit.entity.payments_temp;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Gioele on 07/02/2016.
 */
public interface WI_PaymentsTemp {

    boolean setPaymentTempData(ArrayList<String> arr);

    void addPaymentTemp() throws SQLException;

    boolean deletePaymentTemp(String idUser) throws SQLException;

}
