package fr.abdel.dao;

import fr.abdel.connect.ConnectionUtils;
import fr.abdel.metier.Adresse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdresseService implements Idao<Adresse> {


    @Override
    public boolean create(Adresse adresse) {
        try {
            String sql = "Insert into ADRESSE (NUMRUE, NOMRUE, CODEPOSTALE,VILLE,PAYS )"
                    + "Values (?,?,?,?,?)";
            // Get Connection
            Connection connection = ConnectionUtils.getMyConnection();

            // Create a PreparedStatement object.
            PreparedStatement pstm = connection.prepareStatement(sql);

            // Set value for the first parameter (First '?')
            pstm.setInt(1, adresse.getNumRue());
            pstm.setString(2, adresse.getNomRue());
            pstm.setInt(3, adresse.getCodePostale());
            pstm.setString(4, adresse.getVille());
            pstm.setString(5, adresse.getPays());
            System.out.println("Create OK "+pstm.executeUpdate());
        } catch (ClassNotFoundException | SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Adresse adresse) {
        try {
            String sql = "DELETE FROM adresse where ADRESSE_ID= ?";
            // Get Connection
            Connection connection = ConnectionUtils.getMyConnection();

            // Create a PreparedStatement object.
            PreparedStatement pstm = connection.prepareStatement(sql);

            // Set value for the first parameter (First '?')
            pstm.setInt(1, adresse.getIdAdresse());
            System.out.println("Delete OK "+ pstm.executeUpdate());
        } catch (ClassNotFoundException | SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Adresse adresse) {
        try {
            String sql = "UPDATE ADRESSE SET NUMRUE=? ,NOMRUE=? ,CODEPOSTALE=? , VILLE= ?, PAYS = ?   where ADRESSE_ID=?";
            // Get Connection
            Connection connection = ConnectionUtils.getMyConnection();
            // Create a PreparedStatement object.
            PreparedStatement pstm = connection.prepareStatement(sql);
            // Set value for the first parameter (First '?')
            pstm.setInt(1, adresse.getNumRue());
            pstm.setString(2, adresse.getNomRue());
            pstm.setInt(3, adresse.getCodePostale());
            pstm.setString(4, adresse.getVille());
            pstm.setString(5, adresse.getPays());
            pstm.setInt(6, adresse.getIdAdresse());
            System.out.println("Update OK "+ pstm.executeUpdate());
        } catch (ClassNotFoundException | SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Adresse findById(int id) {
        Adresse result = new Adresse();
        try {
            String sql = "select ADRESSE_ID, NUMRUE, NOMRUE, CODEPOSTALE,VILLE,PAYS from adresse where ADRESSE_ID = ? ";
            // Get Connection
            Connection connection = ConnectionUtils.getMyConnection();

            // Create a PreparedStatement object.
            PreparedStatement pstm = connection.prepareStatement(sql);

            // Set value for the first parameter (First '?')
            pstm.setInt(1, id);

            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int idadresse = rs.getInt(1);
                int numRue = rs.getInt(2);
                String nomRue = rs.getString(3);
                int codePostale = rs.getInt(4);
                String ville = rs.getString(5);
                String pays = rs.getString("PAYS");
                result = new Adresse(idadresse , numRue, nomRue, codePostale, ville, pays) ;
            }

            System.out.println("Update OK "+rs.getRow());
        } catch (ClassNotFoundException | SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return null;
        }
        return result;
    }

    @Override
    public List<Adresse> findAll() {
        List<Adresse> result = new ArrayList<>() ;
        // Get Connection
        Connection connection;
        try {
            connection = ConnectionUtils.getMyConnection();
            // Create statement
            Statement statement = connection.createStatement();
            String sql = "Select ADRESSE_ID, NUMRUE, NOMRUE,CODEPOSTALE, VILLE , PAYS from ADRESSE";
            // Execute SQL statement returns a ResultSet object.
            ResultSet rs = statement.executeQuery(sql);
            // Fetch on the ResultSet
            // Move the cursor to the next record.
            while (rs.next()) {
                Adresse a = new Adresse(rs.getInt(1), rs.getInt(2), rs.getString(3),
                        rs.getInt(4), rs.getString(5), rs.getString(6)) ;
                result.add(a) ;
            }

            // Close connection.
            connection.close();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result ;
    }

    public Adresse findTheAdress(Adresse adr) {
        for(Adresse e: findAll()) {
            if(e.equals(adr)) {
                return e;
            }
        }
        return null;
    }

}
