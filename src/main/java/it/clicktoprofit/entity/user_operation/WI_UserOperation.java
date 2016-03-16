package it.clicktoprofit.entity.user_operation;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Gioele on 31/12/2015.
 */
public interface WI_UserOperation {

    boolean setUserOperationData(ArrayList<String> arr);

    void addOperation() throws SQLException;

    boolean acceptOperation(String idUser, String idCampaign) throws SQLException;

    boolean deleteOperation(String idUser, String idCampaign) throws SQLException;

}
