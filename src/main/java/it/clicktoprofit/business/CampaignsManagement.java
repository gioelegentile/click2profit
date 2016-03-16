package it.clicktoprofit.business;

import it.clicktoprofit.entity.campaign.I_Campaign;
import it.clicktoprofit.entity.campaign.RI_Campaign;
import it.clicktoprofit.entity.campaign.WI_Campaign;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Gioele on 31/12/2015.
 */
class CampaignsManagement {

    static RI_Campaign campaignRead = I_Campaign
            .getCampaignRead();

    static WI_Campaign campaignsWrite = I_Campaign
            .getCampaignWrite();

    ArrayList<HashMap<String, String>> getAllCampaigns() throws SQLException {
        ArrayList<ArrayList<String>> arr = campaignRead.getCampaigns();
        ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
        for (ArrayList<String> currArr : arr) {
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("id_campaign", currArr.get(0));
            hashMap.put("company", currArr.get(1));
            hashMap.put("type", currArr.get(2));
            hashMap.put("notes", currArr.get(3));
            hashMap.put("link", currArr.get(4));
            hashMap.put("profit", currArr.get(5));
            hashMap.put("deadline", currArr.get(6));
            arrayList.add(hashMap);
        }
        return arrayList;
    }

    ArrayList<HashMap<String, String>> getCampaigns() throws SQLException {
        ArrayList<ArrayList<String>> arr = campaignRead.getCampaigns();
        ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();
        for (ArrayList<String> currArr : arr) {
            Date today = new Date();
            String string_date = currArr.get(6);
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            Date d = new Date();
            try {
                d = f.parse(string_date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (d.after(today)) {
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("id_campaign", currArr.get(0));
                hashMap.put("company", currArr.get(1));
                hashMap.put("type", currArr.get(2));
                hashMap.put("notes", currArr.get(3));
                hashMap.put("link", currArr.get(4));
                hashMap.put("profit", currArr.get(5));
                hashMap.put("deadline", currArr.get(6));
                arrayList.add(hashMap);
            }
        }
        return arrayList;
    }

    boolean addCampaign(ArrayList<String> data) throws SQLException {
        if (campaignsWrite.setCampaignData(data)) {
            campaignsWrite.addCampaign();
            return true;
        } else {
            return false;
        }
    }

    boolean deleteCampaign(String id) throws SQLException {
        return campaignsWrite.deleteCampaign(id);
    }

}
