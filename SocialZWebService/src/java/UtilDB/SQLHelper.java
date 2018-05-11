package UtilDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLHelper {

    private static final Logger LOGGER = Logger.getLogger(SQLHelper.class.getName());
    static final String NOME_CLASSE_DRIVER_DB = "org.sqlite.JDBC";
    static final String DRIVER_DB = "jdbc:sqlite:";
    static final String PATH_DB = "/home/farhan/Desktop/SocialZWebService/";
    static final String NOME_DB = "SocialDB.db";
    static final String USER_DB = "";
    static final String PWD_DB = "";
    static final String URL_DB = DRIVER_DB + PATH_DB + NOME_DB;

    static Connection conn = null;

    public static Connection getConnection() {
        if (conn == null) {
            try {
                Class.forName(NOME_CLASSE_DRIVER_DB);
                conn = DriverManager.getConnection(URL_DB, USER_DB, PWD_DB);
            } catch (ClassNotFoundException | SQLException ex) {
                LOGGER.log(Level.SEVERE, ex.toString(), ex);
            }
        }
        return conn;
    }

    public static ArrayList<String> getHobbies() {
        ArrayList<String> ris = new ArrayList<>();
        String query = "SELECT NomeHobby FROM tblHOBBY";
        try {
            PreparedStatement ps = getConnection().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ris.add(rs.getString("NomeHobby"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQLHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ris;
    }

    public static ArrayList<String> getAppassionati(String nomeHobby) {
        ArrayList<String> ris = new ArrayList<>();
        String query = "SELECT Email FROM tblPERSONA, tbrPASSIONE, tblHOBBY "
                + "WHERE tblHOBBY.NomeHobby=? AND "
                + "tblHobby.IdHobby=tbrPASSIONE.IdHobby AND "
                + "tbrPASSIONE.IdUtente=tblPERSONA.IdPersona";
        try {
            PreparedStatement ps = getConnection().prepareStatement(query);
            ps.setString(1, nomeHobby);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ris.add(rs.getString("Email"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SQLHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ris;
    }

//    public static void main(String[] args) {
//        System.out.println(getHobbies());
//        System.out.println(getAppassionati("calcio"));
//    }

}
