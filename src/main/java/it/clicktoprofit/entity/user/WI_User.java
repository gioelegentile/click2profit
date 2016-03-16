package it.clicktoprofit.entity.user;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Gioele on 31/12/2015.
 */
public interface WI_User {

    boolean setUserData(ArrayList<String> arr);

    boolean setUpdateUserData(ArrayList<String> arr);

    boolean setUpdateUserPasswordData(ArrayList<String> arr);

    boolean setUpdateUserCurrentBalanceData(ArrayList<String> arr);

    void updateUserPassword() throws SQLException;

    void updateUserCurrentBalance() throws SQLException;

    void addUser() throws SQLException;

    void updateUserData() throws SQLException;

    boolean deleteUser(String id) throws SQLException;

}
