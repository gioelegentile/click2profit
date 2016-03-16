package it.clicktoprofit.entity.admin;

/**
 * Created by Gioele on 31/12/2015.
 */
public class I_Admin {

    static Admin admin = new Admin();

    public static RI_Admin getAdminRead() {
        return admin;
    }

    public static WI_Admin getAdminWrite() {
        return admin;
    }

}
