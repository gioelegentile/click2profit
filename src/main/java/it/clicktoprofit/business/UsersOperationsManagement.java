package it.clicktoprofit.business;

import it.clicktoprofit.entity.user_operation.I_UserOperation;
import it.clicktoprofit.entity.user_operation.RI_UserOperation;
import it.clicktoprofit.entity.user_operation.WI_UserOperation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Gioele on 31/12/2015.
 */
class UsersOperationsManagement {

    static RI_UserOperation UserOperationRead = I_UserOperation
            .getUserOperationRead();

    static WI_UserOperation UserOperationsWrite = I_UserOperation
            .getUserOperationWrite();

    ArrayList<HashMap<String, String>> getUserOperations(String id) throws SQLException {
        ArrayList<ArrayList<String>> arr = UserOperationRead.getUserOperations(id);
        ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
        for (ArrayList<String> currArr : arr) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("id_campaign", currArr.get(0));
            hashMap.put("profit", currArr.get(1));
            hashMap.put("date", currArr.get(2));
            hashMap.put("time", currArr.get(3));
            hashMap.put("company", currArr.get(4));
            hashMap.put("type", currArr.get(5));
            hashMap.put("link", currArr.get(6));
            arrayList.add(hashMap);
        }
        return arrayList;
    }

    ArrayList<HashMap<String, String>> getAllNotAcceptedOperations() throws SQLException {
        ArrayList<ArrayList<String>> arr = UserOperationRead.getAllNotAcceptedOperations();
        ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
        for (ArrayList<String> currArr : arr) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("id_user", currArr.get(0));
            hashMap.put("email", currArr.get(1));
            hashMap.put("name", currArr.get(2));
            hashMap.put("surname", currArr.get(3));
            hashMap.put("id_campaign", currArr.get(4));
            hashMap.put("profit", currArr.get(5));
            hashMap.put("date", currArr.get(6));
            hashMap.put("time", currArr.get(7));
            hashMap.put("company", currArr.get(8));
            hashMap.put("type", currArr.get(9));
            hashMap.put("link", currArr.get(10));
            arrayList.add(hashMap);
        }
        return arrayList;
    }

    boolean addOperation(ArrayList<String> data) throws SQLException {
        if (UserOperationsWrite.setUserOperationData(data)) {
            UserOperationsWrite.addOperation();
            return true;
        } else {
            return false;
        }
    }

    boolean deleteOperation(ArrayList<String> data) throws SQLException {
        return UserOperationsWrite.deleteOperation(data.get(0), data.get(1));
    }

    boolean acceptOperation(ArrayList<String> data) throws SQLException {
        return UserOperationsWrite.acceptOperation(data.get(0), data.get(1));
    }

}
