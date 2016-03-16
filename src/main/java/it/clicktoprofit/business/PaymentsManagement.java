package it.clicktoprofit.business;

import it.clicktoprofit.entity.payment.I_Payment;
import it.clicktoprofit.entity.payment.RI_Payment;
import it.clicktoprofit.entity.payment.WI_Payment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Gioele on 31/12/2015.
 */
class PaymentsManagement {

    static RI_Payment PaymentRead = I_Payment
            .getPaymentRead();

    static WI_Payment PaymentsWrite = I_Payment
            .getPaymentWrite();

    ArrayList<HashMap<String, String>> getUserPayments(String id) throws SQLException {
        ArrayList<ArrayList<String>> array = PaymentRead.getUserPayments(id);
        ArrayList<HashMap<String, String>> arr = new ArrayList<>();
        for (ArrayList<String> currentArray : array) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("id_payment_method", currentArray.get(1));
            hashMap.put("method", currentArray.get(2));
            hashMap.put("payment", currentArray.get(3));
            hashMap.put("date", currentArray.get(4));
            arr.add(hashMap);
        }
        return arr;
    }

    boolean addPayment(ArrayList<String> data) throws SQLException {
        if (PaymentsWrite.setPaymentData(data)) {
            PaymentsWrite.addPayment();
            return true;
        } else {
            return false;
        }
    }

    boolean deletePayment(ArrayList<String> arr) throws SQLException {
        return PaymentsWrite.deletePayment(arr.get(0), arr.get(1));
    }
    
}
