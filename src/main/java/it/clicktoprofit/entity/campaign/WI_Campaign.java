package it.clicktoprofit.entity.campaign;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Gioele on 31/12/2015.
 */
public interface WI_Campaign {

    boolean setCampaignData(ArrayList<String> arr);

    void addCampaign() throws SQLException;

    boolean deleteCampaign(String id) throws SQLException;

}
