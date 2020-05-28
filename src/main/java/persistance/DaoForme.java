package persistance;

import formes.Forme;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import ui.NonExistentFormeException;

public class DaoForme extends Dao<Forme> {

  /**
   * Chaine de caractere de la base de donn�es.
   */
  private static String dburl = DbConn.dburl;

  @Override
  public void create(Forme t) {
    try (Connection conn = DriverManager.getConnection(dburl)) {
      PreparedStatement prepare = conn.prepareStatement("INSERT INTO Forme VALUES " + "( ?, ?)");
      prepare.setString(1, t.getName());
      prepare.setString(2, t.getClass().getSimpleName().toLowerCase());
      int result = prepare.executeUpdate();
      if (result == 1) {
        System.out.println("L'ajout de la forme s'est bien d�roul�");
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
    PreparedStatement prepare = null;
    Connection conn = null;
    try {
      conn = DriverManager.getConnection(dburl);
      prepare = conn.prepareStatement("DELETE FROM Forme WHERE nom = ?");
      prepare.setString(1, t.getName());
      int result = prepare.executeUpdate();
      if (result == 1) {
        System.out.println("La forme a été supprimé");
      } else {
        System.out.println("La forme n'existe pas");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      if (prepare != null) {
        try {
          prepare.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }

      if (conn != null) {
        try {
          conn.close();
        } catch (SQLException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    }

  }

  @Override
  public Forme read(String nom) throws NonExistentFormeException {
    PreparedStatement prepare = null;
    Connection conn = null;
    ResultSet result = null;
    System.out.println("Le nom est " + nom);
    try {
      conn = DriverManager.getConnection(dburl);
      prepare = conn.prepareStatement("SELECT * FROM Forme WHERE nomForme = ? ");
      prepare.setString(1, nom);
      result = prepare.executeQuery();
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
          default:
            System.out.println("Forme inconnue");
        }
      } else {
        System.out.println("Cette forme n'existe pas");
      }
      prepare.close();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      if (prepare != null) {
        try {
          prepare.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }

      if (conn != null) {
        try {
          conn.close();
        } catch (SQLException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }

      if (result != null) {
        try {
          result.close();
        } catch (SQLException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    }
    return null;
  }
}


