package it.clicktoprofit.entity.user_operation;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Gioele on 31/12/2015.
 */
public interface RI_UserOperation {

    ArrayList<ArrayList<String>> getUserOperations(String id) throws SQLException;

    ArrayList<ArrayList<String>> getAllNotAcceptedOperations() throws SQLException;

}
