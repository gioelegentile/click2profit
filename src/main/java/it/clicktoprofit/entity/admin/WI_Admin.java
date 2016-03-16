package it.clicktoprofit.entity.admin;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Gioele on 31/12/2015.
 */
public interface WI_Admin {

    boolean setAdminData(String id);

    void addAdmin() throws SQLException;

    boolean deleteAdmin() throws SQLException;

    void updateHomeStrings(ArrayList<String> arr) throws SQLException;

}
