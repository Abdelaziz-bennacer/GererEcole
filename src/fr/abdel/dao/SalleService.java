package fr.abdel.dao;

import fr.abdel.connect.ConnectionUtils;
import fr.abdel.metier.Salle;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalleService implements Idao<Salle> {

    List<Salle> listeSalle = new ArrayList<>();

    public boolean create(Salle salle) {
        try {
            String sql = "Insert into Salle (CODE, LIBELLE)"
                    + "Values (?,?)";
            // Get Connection
            Connection connection = ConnectionUtils.getMyConnection();

            // Create a PreparedStatement object.
            PreparedStatement pstm = connection.prepareStatement(sql);

            // Set value for the first parameter (First '?')

            pstm.setString(1, salle.getCode());
            pstm.setString(2, salle.getLibelle());

            System.out.println("Create OK "+pstm.executeUpdate());
        } catch (ClassNotFoundException | SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Salle salle) {
        try {
            String sql = "DELETE FROM salle where  SALLE_ID= ?";
            // Get Connection
            Connection connection = ConnectionUtils.getMyConnection();

            // Create a PreparedStatement object.
            PreparedStatement pstm = connection.prepareStatement(sql);

            // Set value for the first parameter (First '?')
            System.out.println(salle);
            pstm.setInt(1, salle.getId());
            System.out.println("Delete OK "+ pstm.executeUpdate());
        } catch (ClassNotFoundException | SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Salle salle) {
        try {
            String sql = "UPDATE SALLE SET CODE=? ,LIBELLE=?  where SALLE_ID=?";
            // Get Connection
            Connection connection = ConnectionUtils.getMyConnection();
            // Create a PreparedStatement object.
            PreparedStatement pstm = connection.prepareStatement(sql);
            // Set value for the first parameter (First '?')

            pstm.setString(1, salle.getCode());
            pstm.setString(2, salle.getLibelle());
            pstm.setInt(3, salle.getId());
            System.out.println("Update OK "+ pstm.executeUpdate());
        } catch (ClassNotFoundException | SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Salle findById(int id) {
        Salle result = new Salle();
        try {

            String sql = "select  SALLE_ID ,CODE, LIBELLE from salle  where SALLE_ID = ? ";
            // Get Connection
            Connection connection = ConnectionUtils.getMyConnection();

            // Create a PreparedStatement object.
            PreparedStatement pstm = connection.prepareStatement(sql);

            // Set value for the first parameter (First '?')
            pstm.setInt(1, id);

            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int idSalle = rs.getInt(1);
                String code = rs.getString(2);
                String libelle = rs.getString(3);
                result = new Salle( idSalle,code, libelle) ;
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
    public List<Salle> findAll(){

        List<Salle> result = new ArrayList<Salle>() ;
        // Get Connection
        Connection connection;
        try {
            connection = ConnectionUtils.getMyConnection();

            Statement statement = connection.createStatement();
            String sql = "Select SALLE_ID, CODE, LIBELLE from SALLE";
            // Execute SQL statement returns a ResultSet object.
            ResultSet rs = statement.executeQuery(sql);
            // Fetch on the ResultSet
            // Move the cursor to the next record.
            while (rs.next()) {
                Salle a = new Salle(rs.getInt(1), rs.getString(2), rs.getString(3)) ;
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




}
