package it.clicktoprofit.entity.user_payment_method;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Gioele on 31/12/2015.
 */
public interface WI_UserPaymentMethod {

    boolean setUserPaymentMethodData(ArrayList<String> arr);

    void addUserPaymentMethod() throws SQLException;

    boolean deleteUserPaymentMethod(String isUser, String idPaymentMethod) throws SQLException;

}
