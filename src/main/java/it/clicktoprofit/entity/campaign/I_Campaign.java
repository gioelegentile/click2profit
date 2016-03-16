package it.clicktoprofit.entity.campaign;

/**
 * Created by Gioele on 31/12/2015.
 */
public class I_Campaign {

    static Campaign campaign = new Campaign();

    public static RI_Campaign getCampaignRead() {
        return campaign;
    }

    public static WI_Campaign getCampaignWrite() {
        return campaign;
    }

}
