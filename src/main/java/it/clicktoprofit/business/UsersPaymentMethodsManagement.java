package it.clicktoprofit.business;

import it.clicktoprofit.entity.user_payment_method.I_UserPaymentMethod;
import it.clicktoprofit.entity.user_payment_method.RI_UserPaymentMethod;
import it.clicktoprofit.entity.user_payment_method.WI_UserPaymentMethod;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Gioele on 31/12/2015.
 */
class UsersPaymentMethodsManagement {

    static RI_UserPaymentMethod UserPaymentMethodRead = I_UserPaymentMethod
            .getUserPaymentMethodRead();

    static WI_UserPaymentMethod UserPaymentMethodsWrite = I_UserPaymentMethod
            .getUserPaymentMethodWrite();

    ArrayList<HashMap<String, String>> getUserPaymentMethods(String id) throws SQLException {
        ArrayList<ArrayList<String>> arr = UserPaymentMethodRead.getUserPaymentMethods(id);
        ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
        for (ArrayList<String> currArr : arr) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("id_payment_method", currArr.get(0));
            hashMap.put("notes", currArr.get(1));
            hashMap.put("method", currArr.get(2));
            arrayList.add(hashMap);
        }
        return arrayList;
    }

    boolean addUserPaymentMethod(ArrayList<String> data) throws SQLException {
        if (UserPaymentMethodsWrite.setUserPaymentMethodData(data)) {
            UserPaymentMethodsWrite.addUserPaymentMethod();
            return true;
        } else {
            return false;
        }
    }

    boolean deleteUserPaymentMethod(ArrayList<String> arr) throws SQLException {
        return UserPaymentMethodsWrite.deleteUserPaymentMethod(arr.get(0), arr.get(1));
    }
    
}
