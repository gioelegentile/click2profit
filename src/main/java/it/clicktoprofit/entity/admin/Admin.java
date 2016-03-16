package it.clicktoprofit.entity.admin;

import it.clicktoprofit.data_access.DAO;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Gioele on 31/12/2015.
 */
public class Admin implements RI_Admin, WI_Admin {

    private String id;

    public boolean setAdminData(String id) {

        if (true) {
            this.id = id;
            return true;
        } else {
            return false;
        }

    }

    public ArrayList<ArrayList<String>> getAdmins() throws SQLException {
        DAO dao = new DAO();
        return dao.getAdmins();
    }

    @Override
    public ArrayList<ArrayList<String>> getHomeStrings() throws SQLException {
        DAO dao = new DAO();
        return dao.getHomeStrings();
    }

    public void addAdmin() throws SQLException {
        DAO dao = new DAO();
        dao.addAdmin(id);
    }

    public boolean deleteAdmin() throws SQLException {
        DAO dao = new DAO();
        return dao.deleteAdmin(id);
    }

    @Override
    public void updateHomeStrings(ArrayList<String> a) throws SQLException {
        DAO dao = new DAO();
        dao.updateHomeStrings(a.get(0), a.get(1), a.get(2), a.get(3), a.get(4), a.get(5), a.get(6), a.get(7), a.get(8), a.get(9), a.get(10), a.get(11), a.get(12));
    }

}
