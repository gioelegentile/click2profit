package it.clicktoprofit.entity.user;

/**
 * Created by Gioele on 31/12/2015.
 */
public class I_User {

    static User user = new User();

    public static RI_User getUserRead() {
        return user;
    }

    public static WI_User getUserWrite() {
        return user;
    }
    
}
