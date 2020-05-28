package persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * Connexion � la database.
 *
 * @author Mass'
 *
 */
public class DbConn {

  /**
   * Chaine de connexion.
   */
  public static final String dburl = "jdbc:derby:pglp;create=true";


  private static final String user = "Mass";

  private static final String password = "feu-rouge";


  /**
   * Constructeur.
   */
  public DbConn() {
    Properties connectionProps = new Properties();
    connectionProps.put("user", user);
    connectionProps.put("password", password);
  }

  /**
   * Création des tables, si non cr��es.
   *
   * @throws SQLException if something happens
   */
  public final void createTables() throws SQLException {
    Connection conn = null;
    Statement state = null;
    ResultSet resultSet = null;
    try {
      conn = DriverManager.getConnection(dburl);
      state = conn.createStatement();
      /*
       * resultSet = databaseMetadata.getTables(null, null, "FaitPartie", null);
       *
       * if (resultSet.next()) { state.addBatch("DROP TABLE FaitPartie"); } else {
       * System.out.println("Ca ne marche pas"); } resultSet = databaseMetadata.getTables(null,
       * null, "Groupe", null);
       *
       * if (resultSet.next()) { state.addBatch("DROP TABLE Groupe "); } resultSet =
       * databaseMetadata.getTables(null, null, "Carre", null);
       *
       * if (resultSet.next()) { state.addBatch("DROP TABLE Carre"); } resultSet =
       * databaseMetadata.getTables(null, null, "Rectangle", null); if (resultSet.next()) {
       * state.addBatch("DROP TABLE Rectangle"); } resultSet = databaseMetadata.getTables(null,
       * null, "Triangle", null);
       *
       * if (resultSet.next()) { state.addBatch("DROP TABLE Triangle "); } resultSet =
       * databaseMetadata.getTables(null, null, "Cercle", null);
       *
       * if (resultSet.next()) { state.addBatch("DROP TABLE Cercle "); }
       *
       */
      // Carre
      state.addBatch("CREATE TABLE Carre (" + "nom VARCHAR(255) NOT NULL , " + "x int NOT NULL , "
          + "y int NOT NULL , " + "side int NOT NULL, " + "PRIMARY KEY (nom))");
      // Rectangle
      state.addBatch("CREATE TABLE Rectangle (" + "nom VARCHAR(255) NOT NULL , "
          + "x int NOT NULL , " + "y int NOT NULL , " + "longueur int NOT NULL, "
          + "largeur int NOT NULL, " + "PRIMARY KEY (nom))");
      // Cercle
      state.addBatch("CREATE TABLE Cercle (" + "nom VARCHAR(255) NOT NULL , " + "x int NOT NULL , "
          + "y int NOT NULL , " + "rayon int NOT NULL, " + "PRIMARY KEY (nom))");
      // Triangle
      state.addBatch("CREATE TABLE Triangle (" + "nom VARCHAR(255) NOT NULL , "
          + "x int NOT NULL , " + "y int NOT NULL , " + "side1 int NOT NULL, "
          + "side2 int NOT NULL, " + "side3 int NOT NULL, " + "PRIMARY KEY (nom))");
      // Groupe
      state.addBatch("CREATE TABLE Groupe(nomGroupe " + "VARCHAR(255) NOT NULL , "
          + "PRIMARY KEY (NomGroupe))");
      // Forme
      state.addBatch(
          "CREATE TABLE Forme(nomForme VARCHAR(255) NOT NULL , typeForme VARCHAR(255) NOT NULL ,"
              + "PRIMARY KEY (NomForme))");
      // Personnel rattach� � un groupe.
      state.addBatch("CREATE TABLE  FaitPartie( nomGroupe varchar(255) NOT NULL ,"
          + "nomForme VARCHAR(255) NOT NULL , " + "typeForme VARCHAR(255) NOT NULL , "
          + "PRIMARY KEY (nomGroupe,nomForme), "
          + " CONSTRAINT fk_groupe FOREIGN KEY (nomGroupe) REFERENCES Groupe(NomGroupe) "
          + "ON DELETE CASCADE )");

      state.executeBatch();
      state.close();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      if (conn != null) {
        try {
          conn.close();
        } catch (SQLException e) {

          e.printStackTrace();
        }
        if (state != null) {
          state.close();
        }
        /*
         * if (resultSet != null) { resultSet.close();
         */

      }
    }
  }


}
