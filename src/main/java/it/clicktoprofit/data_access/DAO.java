package it.clicktoprofit.data_access;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Gioele on 30/12/2015.
 */
public class DAO {

    /**
     * The connection db.
     */
    private ConnectionDB connectionDB;

    /**
     * The connection.
     */
    private Connection connection;

    public DAO() {
        this.connectionDB = new ConnectionDB();
        this.connection = connectionDB.connectDB();
    }

    // USERS

    public void addUser(String email, String password, String name, String surname, String birth_date) throws SQLException {

        String query = "INSERT INTO " + DBValues.USERS_TAB
                + " VALUES (0, ?, ?, ?, ?, ?, 0);";

        PreparedStatement statement = connection
                .prepareStatement(query);

        statement.setString(1, email);
        statement.setString(2, password);
        statement.setString(3, name);
        statement.setString(4, surname);
        statement.setString(5, birth_date);
        connectionDB.update(statement);

    }

    public void updateUserData(String id, String email, String name, String surname, String birth_date) throws SQLException {

        String query = "UPDATE " + DBValues.USERS_TAB + " SET "
                + DBValues.USERS_EMAIL + " = ?,"
                + DBValues.USERS_NAME + " = ?,"
                + DBValues.USERS_SURNAME + " = ?,"
                + DBValues.USERS_BIRTH_DATE + " = ? WHERE "
                + DBValues.USERS_ID + " = ?;";

        PreparedStatement statement = connection
                .prepareStatement(query);

        statement.setString(1, email);
        statement.setString(2, name);
        statement.setString(3, surname);
        statement.setString(4, birth_date);
        statement.setString(5, id);
        connectionDB.update(statement);

    }

    public void updateUserPassword(String id, String password) throws SQLException {

        String query = "UPDATE " + DBValues.USERS_TAB + " SET "
                + DBValues.USERS_PASSWORD + " = ? WHERE "
                + DBValues.USERS_ID + " = ?;";

        PreparedStatement statement = connection
                .prepareStatement(query);

        statement.setString(1, password);
        statement.setString(2, id);
        connectionDB.update(statement);

    }

    public void updateUserCurrentBalance(String id, String balance) throws SQLException {

        String query = "UPDATE " + DBValues.USERS_TAB + " SET "
                + DBValues.USERS_CURRENT_BALANCE + " = ? WHERE "
                + DBValues.USERS_ID + " = ?;";


        PreparedStatement statement = connection
                .prepareStatement(query);

        statement.setString(1, balance);
        statement.setString(2, id);
        connectionDB.update(statement);

    }

    public ArrayList<ArrayList<String>> getUserData(String id) throws SQLException {

        String query = "SELECT "
                + DBValues.USERS_EMAIL + ", "
                + DBValues.USERS_PASSWORD + ", "
                + DBValues.USERS_NAME + ", "
                + DBValues.USERS_SURNAME + ", "
                + DBValues.USERS_BIRTH_DATE +
                " FROM " + DBValues.USERS_TAB +
                " WHERE " + DBValues.USERS_ID + " = ?;";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, id);
        return connectionDB.select(statement);


    }

    public ArrayList<ArrayList<String>> getUserCurrentBalance(String id) throws SQLException {

        String query = "SELECT "
                + DBValues.USERS_CURRENT_BALANCE +
                " FROM " + DBValues.USERS_TAB +
                " WHERE " + DBValues.USERS_ID + " = ?;";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, id);
        return connectionDB.select(statement);


    }

    public ArrayList<ArrayList<String>> getAllUsers() throws SQLException {
        String query = "SELECT "
                + DBValues.USERS_ID + ", "
                + DBValues.USERS_EMAIL + ", "
                + DBValues.USERS_PASSWORD + ", "
                + DBValues.USERS_NAME + ", "
                + DBValues.USERS_SURNAME + ", "
                + DBValues.USERS_BIRTH_DATE + ", "
                + DBValues.USERS_CURRENT_BALANCE +
                " FROM " + DBValues.USERS_TAB + ";";
        PreparedStatement statement = connection.prepareStatement(query);
        return connectionDB.select(statement);
    }

    public boolean deleteUser(String id) throws SQLException {

        String query = "DELETE FROM " + DBValues.USERS_TAB +
                " WHERE " + DBValues.USERS_ID + " = ?;";

        PreparedStatement statement = connection
                .prepareStatement(query);

        statement.setString(1, id);
        connectionDB.update(statement);
        return true;


    }

    public ArrayList<ArrayList<String>> getIdByEmail(String email) throws SQLException {

        String query = "SELECT "
                + DBValues.USERS_ID +
                " FROM " + DBValues.USERS_TAB +
                " WHERE " + DBValues.USERS_EMAIL + " =?;";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, email);
        return connectionDB.select(statement);


    }

    // USERS OPERATIONS

    public boolean acceptOperation(String idUser, String idCampaign) throws SQLException {

        String payment = getCampaignPayment(idCampaign);
        increaseBalance(idUser, payment);

        String query = "UPDATE " + DBValues.USERS_OPERATIONS_TAB +
                " SET " + DBValues.USERS_OPERATIONS_IS_ACCEPTED + " = ?" +
                " WHERE " + DBValues.USERS_OPERATIONS_ID_USER + " = ? " +
                " AND " + DBValues.USERS_OPERATIONS_ID_CAMPAIGN + " = ?;";

        PreparedStatement statement = connection
                .prepareStatement(query);
        statement.setBoolean(1, true);
        statement.setString(2, idUser);
        statement.setString(3, idCampaign);
        connectionDB.update(statement);
        return true;

    }

    public void addOperation(String idUser, String idCampaign, String profit, String date, String time) throws SQLException {

        String query = "INSERT INTO " + DBValues.USERS_OPERATIONS_TAB
                + " VALUES (?, ?, ?, ?, ?, 0);";

        PreparedStatement statement = connection
                .prepareStatement(query);

        statement.setString(1, idUser);
        statement.setString(2, idCampaign);
        statement.setString(3, profit);
        statement.setString(4, date);
        statement.setString(5, time);
        connectionDB.update(statement);


    }

    public ArrayList<ArrayList<String>> getUserOperations(String id) throws SQLException {

        String query = "SELECT "
                + DBValues.USERS_OPERATIONS_TAB + "." + DBValues.USERS_OPERATIONS_ID_CAMPAIGN + ", "
                + DBValues.USERS_OPERATIONS_TAB + "." + DBValues.USERS_OPERATIONS_PROFIT + ", "
                + DBValues.USERS_OPERATIONS_TAB + "." + DBValues.USERS_OPERATIONS_DATE + ", "
                + DBValues.USERS_OPERATIONS_TAB + "." + DBValues.USERS_OPERATIONS_TIME + ", "
                + DBValues.CAMPAIGNS_TAB + "." + DBValues.CAMPAIGNS_COMPANY + ", "
                + DBValues.CAMPAIGNS_TAB + "." + DBValues.CAMPAIGNS_TYPE + ", "
                + DBValues.CAMPAIGNS_TAB + "." + DBValues.CAMPAIGNS_LINK +
                " FROM " + DBValues.USERS_OPERATIONS_TAB +
                " JOIN " + DBValues.CAMPAIGNS_TAB +
                " ON " + DBValues.USERS_OPERATIONS_TAB + "." + DBValues.USERS_OPERATIONS_ID_CAMPAIGN +
                " = " + DBValues.CAMPAIGNS_TAB + "." + DBValues.CAMPAIGNS_ID +
                " WHERE " + DBValues.USERS_OPERATIONS_ID_USER + " = ? " +
                " AND " + DBValues.USERS_OPERATIONS_IS_ACCEPTED + " = ?;";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, id);
        statement.setBoolean(2, true);
        return connectionDB.select(statement);


    }

    public ArrayList<ArrayList<String>> getAllNotAcceptedOperations() throws SQLException {

        String query = "SELECT "
                + DBValues.USERS_TAB + "." + DBValues.USERS_ID + ", "
                + DBValues.USERS_TAB + "." + DBValues.USERS_EMAIL + ", "
                + DBValues.USERS_TAB + "." + DBValues.USERS_NAME + ", "
                + DBValues.USERS_TAB + "." + DBValues.USERS_SURNAME + ", "
                + DBValues.USERS_OPERATIONS_TAB + "." + DBValues.USERS_OPERATIONS_ID_CAMPAIGN + ", "
                + DBValues.USERS_OPERATIONS_TAB + "." + DBValues.USERS_OPERATIONS_PROFIT + ", "
                + DBValues.USERS_OPERATIONS_TAB + "." + DBValues.USERS_OPERATIONS_DATE + ", "
                + DBValues.USERS_OPERATIONS_TAB + "." + DBValues.USERS_OPERATIONS_TIME + ", "
                + DBValues.CAMPAIGNS_TAB + "." + DBValues.CAMPAIGNS_COMPANY + ", "
                + DBValues.CAMPAIGNS_TAB + "." + DBValues.CAMPAIGNS_TYPE + ", "
                + DBValues.CAMPAIGNS_TAB + "." + DBValues.CAMPAIGNS_LINK +
                " FROM " + DBValues.USERS_OPERATIONS_TAB +
                " INNER JOIN " + DBValues.USERS_TAB +
                " ON " + DBValues.USERS_TAB + "." + DBValues.USERS_ID +
                " = " + DBValues.USERS_OPERATIONS_TAB + "." + DBValues.USERS_OPERATIONS_ID_USER +
                " INNER JOIN " + DBValues.CAMPAIGNS_TAB +
                " ON " + DBValues.CAMPAIGNS_TAB + "." + DBValues.CAMPAIGNS_ID +
                " = " + DBValues.USERS_OPERATIONS_TAB + "." + DBValues.USERS_OPERATIONS_ID_CAMPAIGN + "" +
                " WHERE " + DBValues.USERS_OPERATIONS_IS_ACCEPTED + " = ?;";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setBoolean(1, false);
        return connectionDB.select(statement);


    }

    public boolean deleteOperation(String idUser, String idCampaign) throws SQLException {

        String query = "DELETE FROM " + DBValues.USERS_OPERATIONS_TAB +
                " WHERE " + DBValues.USERS_OPERATIONS_ID_USER + " = ? " +
                " AND " + DBValues.USERS_OPERATIONS_ID_CAMPAIGN + " = ?;";


        PreparedStatement statement = connection
                .prepareStatement(query);

        statement.setString(1, idUser);
        statement.setString(2, idCampaign);
        connectionDB.update(statement);
        return true;


    }

    // USERS PAYMENT METHODS

    public ArrayList<ArrayList<String>> getUserPaymentMethods(String id) throws SQLException {

        String query = "SELECT "
                + DBValues.USERS_PAYMENT_METHODS_ID_PAYMENT_METHOD + ", "
                + DBValues.USERS_PAYMENT_METHODS_NOTES + ", "
                + DBValues.PAYMENT_METHOD_METHOD +
                " FROM " + DBValues.USERS_PAYMENT_METHODS_TAB +
                " NATURAL JOIN " + DBValues.PAYMENT_METHODS_TAB +
                " WHERE " + DBValues.USERS_PAYMENT_METHODS_ID_USER + " = ?;";


        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, id);
        return connectionDB.select(statement);


    }

    public void addUserPaymentMethod(String isUser, String idPaymentMethod, String notes) throws SQLException {

        String query = "INSERT INTO " + DBValues.USERS_PAYMENT_METHODS_TAB
                + " VALUES (? ,?, ?);";


        PreparedStatement statement = connection
                .prepareStatement(query);

        statement.setString(1, isUser);
        statement.setString(2, idPaymentMethod);
        statement.setString(3, notes);
        connectionDB.update(statement);


    }

    public boolean deleteUserPaymentMethod(String isUser, String idPaymentMethod) throws SQLException {

        String query = "DELETE FROM " + DBValues.USERS_PAYMENT_METHODS_TAB
                + " WHERE " + DBValues.USERS_PAYMENT_METHODS_ID_USER + " = ?" +
                " AND " + DBValues.USERS_PAYMENT_METHODS_ID_PAYMENT_METHOD + " = ?;";

        PreparedStatement statement = connection
                .prepareStatement(query);

        statement.setString(1, isUser);
        statement.setString(2, idPaymentMethod);
        connectionDB.update(statement);
        return true;


    }

    // ADMINS

    public void addAdmin(String id) throws SQLException {

        String query = "INSERT INTO " + DBValues.ADMINS_TAB
                + " VALUES (?);";

        PreparedStatement statement = connection
                .prepareStatement(query);

        statement.setString(1, id);
        connectionDB.update(statement);


    }

    public ArrayList<ArrayList<String>> getAdmins() throws SQLException {

        String query = "SELECT " + DBValues.ADMINS_ID + ", "
                + DBValues.USERS_EMAIL + ", "
                + DBValues.USERS_NAME + ", "
                + DBValues.USERS_SURNAME
                + " FROM " + DBValues.ADMINS_TAB
                + " NATURAL JOIN " + DBValues.USERS_TAB + ";";

        PreparedStatement statement = connection.prepareStatement(query);
        return connectionDB.select(statement);

    }

    public boolean deleteAdmin(String id) throws SQLException {

        String query = "DELETE FROM " + DBValues.ADMINS_TAB +
                " WHERE " + DBValues.ADMINS_ID + " = ? ";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, id);
        connectionDB.update(statement);
        return true;


    }

    // CAMPAIGNS

    public void addCampaign(String company, String type, String notes, String link, String profit, String
            deadline) throws SQLException {

        String query = "INSERT INTO " + DBValues.CAMPAIGNS_TAB
                + " VALUES (0, ?, ?, ?, ?, ?, ?);";


        PreparedStatement statement = connection
                .prepareStatement(query);

        statement.setString(1, company);
        statement.setString(2, type);
        statement.setString(3, notes);
        statement.setString(4, link);
        statement.setString(5, profit);
        statement.setString(6, deadline);
        connectionDB.update(statement);


    }

    public ArrayList<ArrayList<String>> getCampaigns() throws SQLException {

        String query = "SELECT "
                + DBValues.CAMPAIGNS_ID + ", "
                + DBValues.CAMPAIGNS_COMPANY + ", "
                + DBValues.CAMPAIGNS_TYPE + ", "
                + DBValues.CAMPAIGNS_NOTES + ", "
                + DBValues.CAMPAIGNS_LINK + ", "
                + DBValues.CAMPAIGNS_PROFIT + ", "
                + DBValues.CAMPAIGNS_DEADLINE +
                " FROM " + DBValues.CAMPAIGNS_TAB + ";";

        PreparedStatement statement = connection.prepareStatement(query);
        return connectionDB.select(statement);


    }

    public boolean deleteCampaign(String id) throws SQLException {

        String query = "DELETE FROM " + DBValues.CAMPAIGNS_TAB +
                " WHERE " + DBValues.CAMPAIGNS_ID + " = ? ";


        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, id);
        connectionDB.update(statement);
        return true;


    }

    // PAYMENT METHODS

    public void addPaymentMethod(String paymentMethod) throws SQLException {

        String query = "INSERT INTO " + DBValues.PAYMENT_METHODS_TAB
                + " VALUES (0, ?);";


        PreparedStatement statement = connection
                .prepareStatement(query);

        statement.setString(1, paymentMethod);
        connectionDB.update(statement);


    }

    public boolean deletePaymentMethod(String id) throws SQLException {

        String query = "DELETE FROM " + DBValues.PAYMENT_METHODS_TAB
                + " WHERE " + DBValues.PAYMENT_METHOD_ID + " = ?;";


        PreparedStatement statement = connection
                .prepareStatement(query);

        statement.setString(1, id);
        connectionDB.update(statement);
        return true;

    }

    public ArrayList<ArrayList<String>> getPaymentMethods() throws SQLException {

        String query = "SELECT "
                + DBValues.PAYMENT_METHOD_ID + ", "
                + DBValues.PAYMENT_METHOD_METHOD +
                " FROM " + DBValues.PAYMENT_METHODS_TAB + ";";


        PreparedStatement statement = connection.prepareStatement(query);
        return connectionDB.select(statement);


    }

    // PAYMENTS

    public void addPayment(String idPaymentMethod, String idUser, String payment, String date) throws SQLException {

        decreaseBalance(idUser, payment);

        String query = "INSERT INTO " + DBValues.PAYMENTS_TAB
                + " VALUES (0, ?, ?, ?, ?);";


        PreparedStatement statement = connection
                .prepareStatement(query);

        statement.setString(1, idPaymentMethod);
        statement.setString(2, idUser);
        statement.setString(3, payment);
        statement.setString(4, date);
        connectionDB.update(statement);


    }

    public ArrayList<ArrayList<String>> getUserPayments(String idUser) throws SQLException {

        String query = "SELECT "
                + DBValues.PAYMENTS_ID_PAYMENT + ", "
                + DBValues.PAYMENTS_ID_PAYMENT_METHOD + ", "
                + DBValues.PAYMENT_METHOD_METHOD + ", "
                + DBValues.PAYMENTS_PAYMENT + ", "
                + DBValues.PAYMENTS_DATE +
                " FROM " + DBValues.PAYMENTS_TAB +
                " NATURAL JOIN " + DBValues.PAYMENT_METHODS_TAB +
                " WHERE " + DBValues.PAYMENTS_ID_USER + " = ?;";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, idUser);
        return connectionDB.select(statement);


    }

    public boolean deletePayment(String idUser, String idPayment) throws SQLException {

        String query = "DELETE FROM " + DBValues.PAYMENTS_TAB +
                " WHERE " + DBValues.PAYMENTS_ID_USER + " = ? " +
                " AND " + DBValues.PAYMENTS_ID_PAYMENT + " = ?;";


        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, idUser);
        statement.setString(2, idPayment);
        connectionDB.update(statement);
        return true;


    }

    // HOME STRINGS

    public void updateHomeStrings(String header1, String header2, String chisiamo, String faq1question, String faq1, String faq2question, String faq2, String faq3question, String faq3, String faq4question, String faq4, String payments, String contacts) throws SQLException {

        String query = "UPDATE "
                + DBValues.STRINGS_TAB + " SET "
                + DBValues.STRINGS_HEADER1 + " = ?, "
                + DBValues.STRINGS_HEADER2 + " = ?, "
                + DBValues.STRINGS_CHI_SIAMO + " = ?, "
                + DBValues.STRINGS_FAQ1_QUESTION + " = ?, "
                + DBValues.STRINGS_FAQ1 + " = ?, "
                + DBValues.STRINGS_FAQ2_QUESTION + " = ?, "
                + DBValues.STRINGS_FAQ2 + " = ?, "
                + DBValues.STRINGS_FAQ3_QUESTION + " = ?, "
                + DBValues.STRINGS_FAQ3 + " = ?, "
                + DBValues.STRINGS_FAQ4_QUESTION + " = ?, "
                + DBValues.STRINGS_FAQ4 + " = ?, "
                + DBValues.STRINGS_PAYMENTS + " = ?, "
                + DBValues.STRINGS_CONTACTS + " = ? "
                + " WHERE " + DBValues.STRINGS_ID + " = 1;";

        PreparedStatement statement = connection
                .prepareStatement(query);

        statement.setString(1, header1);
        statement.setString(2, header2);
        statement.setString(3, chisiamo);
        statement.setString(4, faq1question);
        statement.setString(5, faq1);
        statement.setString(6, faq2question);
        statement.setString(7, faq2);
        statement.setString(8, faq3question);
        statement.setString(9, faq3);
        statement.setString(10, faq4question);
        statement.setString(11, faq4);
        statement.setString(12, payments);
        statement.setString(13, contacts);
        System.out.print(query);
        System.out.print(statement.toString());
        connectionDB.update(statement);

    }

    public ArrayList<ArrayList<String>> getHomeStrings() throws SQLException {

        String query = "SELECT * FROM " + DBValues.STRINGS_TAB
                + " WHERE " + DBValues.STRINGS_ID + " = 1;";

        PreparedStatement statement = connection
                .prepareStatement(query);

        return connectionDB.select(statement);

    }

    // PAYMENTS TEMP

    public void addPaymentTemp(String paymentMethod, String notes, String payment, String idUser) throws SQLException {

        String query = "INSERT INTO " + DBValues.PAYMENTS_TEMP_TAB
                + " VALUES (?, ?, ?, ?);";

        PreparedStatement statement = connection
                .prepareStatement(query);

        statement.setString(1, paymentMethod);
        statement.setString(2, notes);
        statement.setString(3, payment);
        statement.setString(4, idUser);
        connectionDB.update(statement);

    }

    public ArrayList<ArrayList<String>> getPaymentsTemp() throws SQLException {

        String query = "SELECT " +
                DBValues.USERS_NAME + ", " +
                DBValues.USERS_SURNAME + ", " +
                DBValues.PAYMENTS_TEMP_PAYMENT_METHOD + ", " +
                DBValues.PAYMENTS_TEMP_NOTES + ", " +
                DBValues.PAYMENTS_TEMP_PAYMENT + ", "+
                DBValues.PAYMENTS_TEMP_ID_USER +
                " FROM " + DBValues.PAYMENTS_TEMP_TAB +
                " NATURAL JOIN " + DBValues.USERS_TAB + ";";

        PreparedStatement statement = connection.prepareStatement(query);
        return connectionDB.select(statement);

    }

    public boolean deletePaymentTemp(String idUser) throws SQLException {

        String query = "DELETE FROM " + DBValues.PAYMENTS_TEMP_TAB +
                " WHERE " + DBValues.PAYMENTS_TEMP_ID_USER + " = ?;";

        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, idUser);
        connectionDB.update(statement);
        return true;

    }

    // privates

    private String getCampaignPayment(String idCampaign) throws SQLException {

        DAO dao = new DAO();

        String query = "SELECT " + DBValues.CAMPAIGNS_PROFIT + " FROM "
                + DBValues.CAMPAIGNS_TAB + " WHERE "
                + DBValues.CAMPAIGNS_ID + " = ?;";


        PreparedStatement statement = connection
                .prepareStatement(query);
        statement.setString(1, idCampaign);
        return dao.connectionDB.select(statement).get(0).get(0);

    }

    private void decreaseBalance(String idUser, String payment) throws SQLException {

        DAO dao = new DAO();

        String query = "UPDATE " + DBValues.USERS_TAB + " SET "
                + DBValues.USERS_CURRENT_BALANCE + " = "
                + DBValues.USERS_CURRENT_BALANCE + " - ? WHERE "
                + DBValues.USERS_ID + " = ?;";


        PreparedStatement statement = connection
                .prepareStatement(query);

        statement.setString(1, payment);
        statement.setString(2, idUser);
        dao.connectionDB.update(statement);

    }

    private void increaseBalance(String idUser, String payment) throws SQLException {

        DAO dao = new DAO();

        String query = "UPDATE " + DBValues.USERS_TAB + " SET "
                + DBValues.USERS_CURRENT_BALANCE + " = "
                + DBValues.USERS_CURRENT_BALANCE + " + ? WHERE "
                + DBValues.USERS_ID + " = ?;";

        PreparedStatement statement = connection
                .prepareStatement(query);

        statement.setString(1, payment);
        statement.setString(2, idUser);
        dao.connectionDB.update(statement);

    }

}