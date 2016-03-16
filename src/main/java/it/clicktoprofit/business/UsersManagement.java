package it.clicktoprofit.business;

import it.clicktoprofit.entity.user.I_User;
import it.clicktoprofit.entity.user.RI_User;
import it.clicktoprofit.entity.user.WI_User;

import java.io.IOException;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by Gioele on 31/12/2015.
 */
class UsersManagement {

    static RI_User UserRead = I_User
            .getUserRead();

    static WI_User UsersWrite = I_User
            .getUserWrite();

    HashMap<String, String> getUserData(String id) throws SQLException {
        ArrayList<String> array = UserRead.getUserData(id).get(0);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("email", array.get(0));
        hashMap.put("password", array.get(1));
        hashMap.put("name", array.get(2));
        hashMap.put("surname", array.get(3));
        hashMap.put("birthdate", array.get(4));
        return hashMap;
    }

    String getUserCurrentBalance(String id) throws SQLException {
        return UserRead.getUserCurrentBalance(id).get(0).get(0);
    }

    ArrayList<HashMap<String, String>> getAllUsers() throws SQLException {
        ArrayList<ArrayList<String>> array = UserRead.getAllUsers();
        ArrayList<HashMap<String, String>> hashMap = new ArrayList<>();
        for (ArrayList<String> currentArray : array) {
            HashMap<String, String> hashMap1 = new HashMap<>();
            hashMap1.put("id_user", currentArray.get(0));
            hashMap1.put("email", currentArray.get(1));
            hashMap1.put("password", currentArray.get(2));
            hashMap1.put("name", currentArray.get(3));
            hashMap1.put("surname", currentArray.get(4));
            hashMap1.put("birthdate", currentArray.get(5));
            hashMap1.put("current_balance", currentArray.get(6));
            hashMap.add(hashMap1);
        }
        return hashMap;
    }

    boolean addUser(ArrayList<String> data) throws SQLException {
        if (UsersWrite.setUserData(data)) {
            UsersWrite.addUser();
            return true;
        } else {
            return false;
        }
    }

    boolean autenticate(ArrayList<String> data) throws SQLException {
        ArrayList<HashMap<String, String>> users = getAllUsers();
        for (HashMap<String, String> h : users) {
            if ((h.get("email")).equalsIgnoreCase(data.get(0)) && (h.get("password")).equals(data.get(1))) {
                return true;
            }
        }
        return false;
    }

    boolean updateUserPassword(ArrayList<String> data) throws SQLException {
        if (UsersWrite.setUpdateUserPasswordData(data)) {
            UsersWrite.updateUserPassword();
            return true;
        } else {
            return false;
        }
    }

    boolean updateUserCurrentBalance(ArrayList<String> data) throws SQLException {
        if (UsersWrite.setUpdateUserCurrentBalanceData(data)) {
            UsersWrite.updateUserCurrentBalance();
            return true;
        } else {
            return false;
        }
    }

    boolean updateUserData(ArrayList<String> data) throws SQLException {
        if (UsersWrite.setUpdateUserData(data)) {
            UsersWrite.updateUserData();
            return true;
        } else {
            return false;
        }
    }

    boolean deleteUser(String id) throws SQLException {
        return UsersWrite.deleteUser(id);
    }

    String getIdByEmail(String email) throws SQLException {
        return UserRead.getIdByEmail(email).get(0).get(0);
    }

}
