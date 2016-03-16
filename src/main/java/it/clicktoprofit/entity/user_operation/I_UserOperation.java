package it.clicktoprofit.entity.user_operation;

/**
 * Created by Gioele on 31/12/2015.
 */
public class I_UserOperation {

    static UserOperation userOperation = new UserOperation();

    public static RI_UserOperation getUserOperationRead() {
        return userOperation;
    }

    public static WI_UserOperation getUserOperationWrite() {
        return userOperation;
    }
    
}
