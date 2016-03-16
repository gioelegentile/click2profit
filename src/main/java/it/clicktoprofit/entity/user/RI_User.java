package it.clicktoprofit.entity.user;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Gioele on 31/12/2015.
 */
public interface RI_User {

    ArrayList<ArrayList<String>> getUserData(String id) throws SQLException;

    ArrayList<ArrayList<String>> getUserCurrentBalance(String id) throws SQLException;

    ArrayList<ArrayList<String>> getAllUsers() throws SQLException;

    ArrayList<ArrayList<String>> getIdByEmail(String email) throws SQLException;

}
