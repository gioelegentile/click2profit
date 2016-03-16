package it.clicktoprofit.entity.campaign;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Gioele on 31/12/2015.
 */
public interface RI_Campaign {

    ArrayList<ArrayList<String>> getCampaigns() throws SQLException;

}
