package it.clicktoprofit.business;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Gioele on 28/01/2016.
 */
public class BusinessDelegate {

    public boolean addAdmin(String str) throws SQLException {
        return new AdminsManagement().addAdmin(str);
    }

    public ArrayList<HashMap<String, String>> getAdmins() throws SQLException {
        return new AdminsManagement().getAdmins();
    }

    public boolean deleteAdmin(String str) throws SQLException {
        return new AdminsManagement().deleteAdmin(str);
    }

    public void updateHomeStrings(ArrayList<String> arr) throws SQLException {
        new AdminsManagement().updateHomeStrings(arr);
    }

    public HashMap<String, String> getHomeStrings() throws SQLException {
        return new AdminsManagement().getHomeStrings();
    }

    public ArrayList<HashMap<String, String>> getAllCampaigns() throws SQLException {
        return new CampaignsManagement().getAllCampaigns();
    }

    public ArrayList<HashMap<String, String>> getCampaigns() throws SQLException {
        return new CampaignsManagement().getCampaigns();
    }

    public boolean addCampaign(ArrayList<String> arr) throws SQLException {
        return new CampaignsManagement().addCampaign(arr);
    }

    public boolean deleteCampaign(String str) throws SQLException {
        return new CampaignsManagement().deleteCampaign(str);
    }

    public ArrayList<HashMap<String, String>> getPaymentMethods() throws SQLException {
        return new PaymentMethodsManagement().getPaymentMethods();
    }

    public boolean addPaymentMethod(String str) throws SQLException {
        return new PaymentMethodsManagement().addPaymentMethod(str);
    }

    public boolean deletePaymentMethod(String str) throws SQLException {
        return new PaymentMethodsManagement().deletePaymentMethod(str);
    }

    public ArrayList<HashMap<String, String>> getUserPayments(String str) throws SQLException {
        return new PaymentsManagement().getUserPayments(str);
    }

    public boolean addPayment(ArrayList<String> arr) throws SQLException {
        return new PaymentsManagement().addPayment(arr);
    }

    public boolean deletePayment(ArrayList<String> arr) throws SQLException {
        return new PaymentsManagement().deletePayment(arr);
    }

    public ArrayList<HashMap<String, String>> getPaymentsTemp() throws SQLException {
        return new PaymentsTempManagement().getPaymentsTemp();
    }

    public boolean addPaymentTemp(ArrayList<String> arr) throws SQLException {
        return new PaymentsTempManagement().addPaymentTemp(arr);
    }

    public boolean deletePaymentTemp(String str) throws SQLException {
        return new PaymentsTempManagement().deletePaymentTemp(str);
    }

    public String getIdByEmail(String str) throws SQLException {
        return new UsersManagement().getIdByEmail(str);
    }

    public boolean autenticate(ArrayList<String> arr) throws SQLException {
        return new UsersManagement().autenticate(arr);
    }

    public HashMap<String, String> getUserData(String str) throws SQLException {
        return new UsersManagement().getUserData(str);
    }

    public String getUserCurrentBalance(String str) throws SQLException {
        return new UsersManagement().getUserCurrentBalance(str);
    }

    public ArrayList<HashMap<String, String>> getAllUsers() throws SQLException {
        return new UsersManagement().getAllUsers();
    }

    public boolean addUser(ArrayList<String> arr) throws SQLException {
        return new UsersManagement().addUser(arr);
    }

    public boolean updateUserData(ArrayList<String> arr) throws SQLException {
        return new UsersManagement().updateUserData(arr);
    }

    public boolean updateUserPassword(ArrayList<String> arr) throws SQLException {
        return new UsersManagement().updateUserPassword(arr);
    }

    public boolean updateUserCurrentBalance(ArrayList<String> arr) throws SQLException {
        return new UsersManagement().updateUserCurrentBalance(arr);
    }

    public boolean deleteUser(String str) throws SQLException {
        return new UsersManagement().deleteUser(str);
    }

    public ArrayList<HashMap<String, String>> getAllNotAcceptedOperations() throws SQLException {
        return new UsersOperationsManagement().getAllNotAcceptedOperations();
    }

    public ArrayList<HashMap<String, String>> getUserOperations(String str) throws SQLException {
        return new UsersOperationsManagement().getUserOperations(str);
    }

    public boolean addOperation(ArrayList<String> arr) throws SQLException {
        return new UsersOperationsManagement().addOperation(arr);
    }

    public boolean deleteOperation(ArrayList<String> arr) throws SQLException {
        return new UsersOperationsManagement().deleteOperation(arr);
    }

    public boolean acceptOperation(ArrayList<String> arr) throws SQLException {
        return new UsersOperationsManagement().acceptOperation(arr);
    }

    public ArrayList<HashMap<String, String>> getUserPaymentMethods(String str) throws SQLException {
        return new UsersPaymentMethodsManagement().getUserPaymentMethods(str);
    }

    public boolean addUserPaymentMethod(ArrayList<String> arr) throws SQLException {
        return new UsersPaymentMethodsManagement().addUserPaymentMethod(arr);
    }

    public boolean deleteUserPaymentMethod(ArrayList<String> arr) throws SQLException {
        return new UsersPaymentMethodsManagement().deleteUserPaymentMethod(arr);
    }


}
