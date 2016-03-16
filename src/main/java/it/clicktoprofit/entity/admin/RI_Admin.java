package it.clicktoprofit.entity.admin;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Gioele on 31/12/2015.
 */
public interface RI_Admin {

    ArrayList<ArrayList<String>> getAdmins() throws SQLException;

    ArrayList<ArrayList<String>> getHomeStrings() throws SQLException;

}
