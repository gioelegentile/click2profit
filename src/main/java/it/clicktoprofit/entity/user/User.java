package it.clicktoprofit.entity.user;

import it.clicktoprofit.data_access.DAO;
import org.apache.commons.lang3.text.WordUtils;
import it.clicktoprofit.utility.PasswordEncrypt;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Gioele on 31/12/2015.
 */
public class User implements WI_User, RI_User {

    private String id, email, password, name, surname, birthDate, currentBalance;

    public boolean setUserData(ArrayList<String> arr) {

        if (true) {

            email = arr.get(0);
            password = PasswordEncrypt.encrypt(arr.get(1));
            name = WordUtils.capitalizeFully(arr.get(2));
            surname = WordUtils.capitalizeFully(arr.get(3));
            birthDate = arr.get(4);

            return true;
        } else {
            return false;
        }

    }

    public boolean setUpdateUserData(ArrayList<String> arr) {

        if (true) {

            id = arr.get(0);
            email = arr.get(1);
            name = WordUtils.capitalizeFully(arr.get(2));
            surname = WordUtils.capitalizeFully(arr.get(3));
            birthDate = arr.get(4);

            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean setUpdateUserPasswordData(ArrayList<String> arr) {

        if (true) {
            id = arr.get(0);
            password = PasswordEncrypt.encrypt(arr.get(1));
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean setUpdateUserCurrentBalanceData(ArrayList<String> arr) {

        if (true) {
            id = arr.get(0);
            currentBalance = arr.get(1);
            return true;
        } else {
            return false;
        }

    }

    @Override
    public void updateUserPassword() throws SQLException {
        DAO dao = new DAO();
        dao.updateUserPassword(id, password);
    }

    @Override
    public void updateUserCurrentBalance() throws SQLException {
        DAO dao = new DAO();
        dao.updateUserCurrentBalance(id, currentBalance);
    }

    public ArrayList<ArrayList<String>> getUserData(String id) throws SQLException {
        DAO dao = new DAO();
        return dao.getUserData(id);
    }

    public ArrayList<ArrayList<String>> getUserCurrentBalance(String id) throws SQLException {
        DAO dao = new DAO();
        return dao.getUserCurrentBalance(id);
    }

    public ArrayList<ArrayList<String>> getAllUsers() throws SQLException {
        DAO dao = new DAO();
        return dao.getAllUsers();
    }

    @Override
    public ArrayList<ArrayList<String>> getIdByEmail(String email) throws SQLException {
        DAO dao = new DAO();
        return dao.getIdByEmail(email);
    }

    public void addUser() throws SQLException {
        DAO dao = new DAO();
        dao.addUser(email, password, name, surname, birthDate);
    }

    public void updateUserData() throws SQLException {
        DAO dao = new DAO();
        dao.updateUserData(id, email, name, surname, birthDate);
    }

    public boolean deleteUser(String id) throws SQLException {
        DAO dao = new DAO();
        return dao.deleteUser(id);
    }

}
