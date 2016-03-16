package it.clicktoprofit.data_access;

import it.clicktoprofit.utility.Log;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Gioele on 30/12/2015.
 */
class ConnectionDB {

    /**
     * The db.
     */
    private Connection db = null; // Connessione al Database

    /**
     * The path.
     */
    private String path;

    /**
     * The name.
     */
    private String name; // Nome del Database

    /**
     * The username.
     */
    private String username; // Nome utente per la connessione al Database

    /**
     * The password.
     */
    private String password; // Password usata per la connessione al Database

    /**
     * The connection.
     */
    private boolean connection;

    /**
     * Instantiates a new connection db.
     */
    ConnectionDB() {

        if (!isConnected()) {
//            this.path = "localhost:3306";
//            this.name = "clicktoprofit";
//            this.username = "root";
//            this.password = "";
//            this.connection = false;
            this.path = "localhost:3306";
            this.name = "click2profit";
            this.username = "root";
            this.password = "c2p2016";
            this.connection = false;
        }

    }

    /**
     * Connect db.
     *
     * @return the connection
     */
    Connection connectDB() {

        if (!isConnected()) {
            try {
                new com.mysql.jdbc.Driver();
                db = DriverManager.getConnection("jdbc:mysql://" + path + "/"
                        + name, username, password);
                connection = true;
            } catch (SQLException e) {
                connection = false;
                Log.setMessage(e.toString());
                e.printStackTrace();
            }
        }

        return db;

    }

    /**
     * Disconnect.
     */
    void disconnect() {

        if (isConnected()) {
            connection = false;

            try {
                db.close();
            } catch (SQLException e) {
                Log.setMessage(e.toString());
                e.printStackTrace();
            }
        }

    }

    /**
     * Checks if is connected.
     *
     * @return true, if is connected
     */
    boolean isConnected() {
        return connection;
    }

    /**
     * Update.
     *
     * @param stat the stat
     */
    void update(PreparedStatement stat) throws SQLException {
        if (isConnected()) {
            try {
                stat.executeUpdate();
                stat.close();
                disconnect();
            } catch (SQLException e) {
                Log.setMessage(e.toString());
                throw new SQLException(e);
            }
        }
    }

    /**
     * Select.
     *
     * @param stmt the stmt
     * @return the array list
     */
    public ArrayList<ArrayList<String>> select(PreparedStatement stmt) throws SQLException {

        if (isConnected()) {
            ArrayList<ArrayList<String>> v;
            ArrayList<String> record;
            int colonne;

            try {
                ResultSet rs = stmt.executeQuery();
                v = new ArrayList<>();
                ResultSetMetaData rsmd = rs.getMetaData();
                colonne = rsmd.getColumnCount();

                while (rs.next()) {
                    record = new ArrayList<>();
                    for (int i = 0; i < colonne; i++)
                        record.add(rs.getString(i + 1));
                    v.add(record);
                }

                rs.close();
                disconnect();
                return v;

            } catch (SQLException e) {
                Log.setMessage(e.toString());
                throw new SQLException(e);
            }
        }

        return null;

    }

}
