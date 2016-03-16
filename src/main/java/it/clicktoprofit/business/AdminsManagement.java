package it.clicktoprofit.business;

import it.clicktoprofit.entity.admin.I_Admin;
import it.clicktoprofit.entity.admin.RI_Admin;
import it.clicktoprofit.entity.admin.WI_Admin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Gioele on 31/12/2015.
 */
class AdminsManagement {

    static RI_Admin adminRead = I_Admin
            .getAdminRead();

    static WI_Admin adminsWrite = I_Admin
            .getAdminWrite();

    ArrayList<HashMap<String, String>> getAdmins() throws SQLException {
        ArrayList<ArrayList<String>> array = adminRead.getAdmins();
        ArrayList<HashMap<String, String>> arr0 = new ArrayList<>();
        for (ArrayList<String> currentArray : array) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("id", currentArray.get(0));
            hashMap.put("email", currentArray.get(1));
            hashMap.put("name", currentArray.get(2));
            hashMap.put("surname", currentArray.get(3));
            arr0.add(hashMap);
        }
        return arr0;
    }

    boolean addAdmin(String id) throws SQLException {
        if (adminsWrite.setAdminData(id)) {
            adminsWrite.addAdmin();
            return true;
        } else {
            return false;
        }
    }

    boolean deleteAdmin(String id) throws SQLException {
        if (adminsWrite.setAdminData(id)) {
            adminsWrite.deleteAdmin();
            return true;
        } else {
            return false;
        }
    }

    void updateHomeStrings(ArrayList<String> arr) throws SQLException {
        adminsWrite.updateHomeStrings(arr);
    }

    HashMap<String, String> getHomeStrings() throws SQLException {
        ArrayList<String> array = adminRead.getHomeStrings().get(0);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("header1", array.get(1));
        hashMap.put("header2", array.get(2));
        hashMap.put("chi_siamo", array.get(3));
        hashMap.put("faq1_question", array.get(4));
        hashMap.put("faq1", array.get(5));
        hashMap.put("faq2_question", array.get(6));
        hashMap.put("faq2", array.get(7));
        hashMap.put("faq3_question", array.get(8));
        hashMap.put("faq3", array.get(9));
        hashMap.put("faq4_question", array.get(10));
        hashMap.put("faq4", array.get(11));
        hashMap.put("payments", array.get(12));
        hashMap.put("contacts", array.get(13));
        return hashMap;
    }

}
