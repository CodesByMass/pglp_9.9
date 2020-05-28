package persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import formes.Forme;
import ui.NonExistentFormeException;

public class DaoForme extends Dao<Forme> {

  /**
   * Chaine de caractere de la base de données.
   */
  private static String dburl = DbConn.dburl;

  @Override
  public void create(Forme t) {
    try (Connection conn = DriverManager.getConnection(dburl)) {
      PreparedStatement prepare = conn.prepareStatement("INSERT INTO Forme VALUES " + "( ?, ?)");
      prepare.setString(1, t.getName());
      prepare.setString(2, t.getClass().getSimpleName());
      int result = prepare.executeUpdate();
      if (result == 1) {
        System.out.println("L'ajout de la forme s'est bien déroulé");
      }
      prepare.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  @Override
  public void update(Forme t) {
    System.out.println("Il n y a rien à modifier !");

  }

  @Override
  public void delete(Forme t) throws NonExistentFormeException {
    try (Connection conn = DriverManager.getConnection(dburl)) {
      PreparedStatement prepare = conn.prepareStatement("DELETE FROM Forme WHERE nom = '?'");
      prepare.setString(1, t.getName());
      int result = prepare.executeUpdate();
      if (result == 1) {
        System.out.println("La forme a été supprimé");
      } else {
        throw new NonExistentFormeException();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  @Override
  public Forme read(String nom) throws NonExistentFormeException {
    try (Connection conn = DriverManager.getConnection(dburl)) {
      PreparedStatement prepare = conn.prepareStatement("SELECT FROM Forme WHERE nom = '?' ");
      prepare.setString(1, nom);
      ResultSet result = prepare.executeQuery();
      if (result.next()) {
        switch (result.getString("typeForme")) {
          case "cercle":
            return new DaoCercle().read(nom);
          case "carre":
            return new DaoCarre().read(nom);
          case "rectangle":
            return new DaoRectangle().read(nom);
          case "triangle":
            return new DaoTriangle().read(nom);
          case "composite":
            return new DaoComposite().read(nom);
        }
      } else {
        throw new NonExistentFormeException();
      }
      prepare.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
}


