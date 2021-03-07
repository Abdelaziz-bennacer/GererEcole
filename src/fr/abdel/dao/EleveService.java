package fr.abdel.dao;

import fr.abdel.connect.ConnectionUtils;
import fr.abdel.metier.Adresse;
import fr.abdel.metier.Eleve;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EleveService implements Idao<Eleve> {


    @Override
    public boolean create(Eleve eleve) {
        try {
            String sql = "Insert into ELEVE (NOM, PRENOM, DATENAISSANCE,AGE,ADRESSE )"
                    + "Values (?,?,?,?,?)";
            // Get Connection
            Connection connection = ConnectionUtils.getMyConnection();
            // Create a PreparedStatement object.
            PreparedStatement pstm = connection.prepareStatement(sql);
            // Set value for the first parameter (First '?')
            pstm.setString(1, eleve.getNom());
            pstm.setString(2, eleve.getPrenom());

            pstm.setDate(3, convertToDateViaSqlDate( eleve.getDateNaissance()));

            pstm.setInt(4, eleve.getAge());
            pstm.setInt(5, eleve.getAdresse().getIdAdresse());

            System.out.println("Create OK "+pstm.executeUpdate());
        } catch (ClassNotFoundException | SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return false;
        }
        return true;
    }

    public Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }

    @Override
    public boolean delete(Eleve eleve) {
        try {
            String sql = "DELETE FROM Eleve where  ELEVE_ID= ?";
            // Get Connection
            Connection connection = ConnectionUtils.getMyConnection();

            // Create a PreparedStatement object.
            PreparedStatement pstm = connection.prepareStatement(sql);

            // Set value for the first parameter (First '?')
            System.out.println(eleve);
            pstm.setInt(1, eleve.getId());
            System.out.println("Delete OK "+ pstm.executeUpdate());
        } catch (ClassNotFoundException | SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Eleve eleve) {
        try {
            String sql = "UPDATE ELEVE SET NOM=? ,PRENOM=?,DATE_NAISSANCE=?,AGE=?,ADRESSE=?  where ELEVE_ID=?";
            // Get Connection
            Connection connection = ConnectionUtils.getMyConnection();
            // Create a PreparedStatement object.
            PreparedStatement pstm = connection.prepareStatement(sql);
            // Set value for the first parameter (First '?')

            pstm.setString(1, eleve.getNom());
            pstm.setString(2, eleve.getPrenom());
            pstm.setDate(3, convertToDateViaSqlDate(eleve.getDateNaissance()));
            pstm.setInt(4, eleve.getAge());
            pstm.setInt(5, eleve.getAdresse().getIdAdresse());
            pstm.setInt(6, eleve.getId());
            System.out.println("Update OK "+ pstm.executeUpdate());
        } catch (ClassNotFoundException | SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Eleve findById(int id) {
        Eleve result = new Eleve();
        try {

            String sql = "select  ELEVE_ID ,NOM, PRENOM,DATE_NAISSANCE,AGE,ADRESSE from eleve  where ELEVE_ID = ? ";
            // Get Connection
            Connection connection = ConnectionUtils.getMyConnection();

            // Create a PreparedStatement object.
            PreparedStatement pstm = connection.prepareStatement(sql);

            // Set value for the first parameter (First '?')
            pstm.setInt(1, id);

            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int idEleve = rs.getInt(1);
                String nom = rs.getString(2);
                String prenom = rs.getString(3);
                LocalDate dateNaissance = rs.getDate(4 ).toLocalDate();
                int age = rs.getInt(5);
                int IdAdresse = rs.getInt(6);
                AdresseService adresseService = new AdresseService();
                // chercher 1 adresse avec son id
                Adresse adresse = adresseService.findById(IdAdresse);
                result = new Eleve( id, nom, prenom, dateNaissance, age, adresse );
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
    public List<Eleve> findAll() {
        List<Eleve> result = new ArrayList<Eleve>() ;
        // Get Connection
        Connection connection;
        try {
            connection = ConnectionUtils.getMyConnection();
            Statement statement = connection.createStatement();
            String sql = "Select ELEVE_ID, NOM, PRENOM,DATE_NAISSANCE, AGE, ADRESSE from eleve";
            // Execute SQL statement returns a ResultSet object.
            ResultSet rs = statement.executeQuery(sql);
            // Fetch on the ResultSet
            // Move the cursor to the next record.
            while (rs.next()) {
                AdresseService myAdresseService = new AdresseService() ;
                Adresse adresse = myAdresseService.findById(rs.getInt(6)) ;
                Eleve a = new Eleve(rs.getInt(1), rs.getString(2), rs.getString(3) , rs.getDate(4).toLocalDate() ,rs.getInt(5),adresse) ;
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
