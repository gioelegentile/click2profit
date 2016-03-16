package it.clicktoprofit.business;

import it.clicktoprofit.entity.payment_methods.I_PaymentMethods;
import it.clicktoprofit.entity.payment_methods.RI_PaymentMethods;
import it.clicktoprofit.entity.payment_methods.WI_PaymentMethods;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Gioele on 31/12/2015.
 */
class PaymentMethodsManagement {

    static RI_PaymentMethods PaymentMethodsRead = I_PaymentMethods
            .getPaymentMethodsRead();

    static WI_PaymentMethods PaymentMethodssWrite = I_PaymentMethods
            .getPaymentMethodsWrite();

    ArrayList<HashMap<String, String>> getPaymentMethods() throws SQLException {
        ArrayList<ArrayList<String>> arr = PaymentMethodsRead.getPaymentMethods();
        ArrayList<HashMap<String, String>> arr0 = new ArrayList<>();
        for (ArrayList<String> currArr : arr) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("id_payment_method", currArr.get(0));
            hashMap.put("method", currArr.get(1));
            arr0.add(hashMap);
        }
        return arr0;
    }

    boolean addPaymentMethod(String data) throws SQLException {
        if (PaymentMethodssWrite.setPaymentMethodData(data)) {
            PaymentMethodssWrite.addPaymentMethod();
            return true;
        } else {
            return false;
        }
    }

    boolean deletePaymentMethod(String id) throws SQLException {
        return PaymentMethodssWrite.deletePaymentMethod(id);
    }

}
