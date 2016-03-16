package it.clicktoprofit.business;

import it.clicktoprofit.entity.payments_temp.I_PaymentsTemp;
import it.clicktoprofit.entity.payments_temp.RI_PaymentsTemp;
import it.clicktoprofit.entity.payments_temp.WI_PaymentsTemp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Gioele on 07/02/2016.
 */
class PaymentsTempManagement {

    static RI_PaymentsTemp PaymentRead = I_PaymentsTemp
            .getPaymentRead();

    static WI_PaymentsTemp PaymentsWrite = I_PaymentsTemp
            .getPaymentWrite();

    ArrayList<HashMap<String, String>> getPaymentsTemp() throws SQLException {
        ArrayList<ArrayList<String>> array = PaymentRead.getPaymentsTemp();
        ArrayList<HashMap<String, String>> arr = new ArrayList<>();
        for (ArrayList<String> currentArray : array) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("name", currentArray.get(0));
            hashMap.put("surname", currentArray.get(1));
            hashMap.put("payment_method", currentArray.get(2));
            hashMap.put("notes", currentArray.get(3));
            hashMap.put("payment", currentArray.get(4));
            hashMap.put("id_user", currentArray.get(5));
            arr.add(hashMap);
        }
        return arr;
    }

    boolean addPaymentTemp(ArrayList<String> data) throws SQLException {
        if (PaymentsWrite.setPaymentTempData(data)) {
            PaymentsWrite.addPaymentTemp();
            return true;
        } else {
            return false;
        }
    }

    boolean deletePaymentTemp(String str) throws SQLException {
        return PaymentsWrite.deletePaymentTemp(str);
    }

}
