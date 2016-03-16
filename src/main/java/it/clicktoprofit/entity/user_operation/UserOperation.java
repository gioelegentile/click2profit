package it.clicktoprofit.entity.user_operation;

import it.clicktoprofit.data_access.DAO;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Gioele on 31/12/2015.
 */
public class UserOperation implements RI_UserOperation, WI_UserOperation {

    private String idUser, idCampaign, profit, date, time;

    public boolean setUserOperationData(ArrayList<String> arr) {

        if (true) {

            idUser = arr.get(0);
            idCampaign = arr.get(1);
            profit = arr.get(2);
            date = arr.get(3);
            time = arr.get(4);

            return true;
        } else {
            return false;
        }

    }

    public ArrayList<ArrayList<String>> getUserOperations(String id) throws SQLException {
        DAO dao = new DAO();
        return dao.getUserOperations(id);
    }

    @Override
    public ArrayList<ArrayList<String>> getAllNotAcceptedOperations() throws SQLException {
        DAO dao = new DAO();
        return dao.getAllNotAcceptedOperations();
    }

    public void addOperation() throws SQLException {
        DAO dao = new DAO();
        dao.addOperation(idUser, idCampaign, profit, date, time);
    }

    @Override
    public boolean acceptOperation(String idUser, String idCampaign) throws SQLException {
        DAO dao = new DAO();
        return dao.acceptOperation(idUser, idCampaign);
    }

    public boolean deleteOperation(String idUser, String idCampaign) throws SQLException {
        DAO dao = new DAO();
        return dao.deleteOperation(idUser, idCampaign);
    }

}
