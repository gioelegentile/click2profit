package it.clicktoprofit.entity.campaign;

import it.clicktoprofit.data_access.DAO;
import org.apache.commons.lang3.text.WordUtils;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Gioele on 31/12/2015.
 */
public class Campaign implements RI_Campaign, WI_Campaign{

    private String company, type, notes, link, profit, deadline;

    public boolean setCampaignData(ArrayList<String> array) {

        if(true) {

            company = WordUtils.capitalizeFully(array.get(0));
            type = WordUtils.capitalizeFully(array.get(1));
            notes = array.get(2).length() >= 2 ? WordUtils.capitalize(array.get(2).substring(0,2)) + array.get(2).substring(2) : array.get(2).toUpperCase();
            link = array.get(3);
            profit = array.get(4);
            deadline = array.get(5);

            return true;
        } else {
            return false;
        }

    }

    public ArrayList<ArrayList<String>> getCampaigns() throws SQLException {
        DAO dao = new DAO();
        return dao.getCampaigns();
    }

    public void addCampaign() throws SQLException {
        DAO dao = new DAO();
        dao.addCampaign(company, type, notes, link, profit, deadline);
    }

    public boolean deleteCampaign(String id) throws SQLException {
        DAO dao = new DAO();
        return dao.deleteCampaign(id);
    }

}
