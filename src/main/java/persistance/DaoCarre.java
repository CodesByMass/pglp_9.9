package persistance;

import formes.Carre;
import formes.Position2D;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import ui.NonExistentFormeException;

/**
 * Impl�mentation de DAO avec Derby pour la classe Carr�.
 *
 * @see Carre
 *
 * @author Mass'
 *
 */
public class DaoCarre extends Dao<Carre> {

  /**
   * Chaine de caractere de la base de donn�es.
   */
  private static String dburl = DbConn.dburl;

  @Override
  public void create(Carre t) throws NonExistentFormeException {
    // Avec le try with resources, pas besoin de fermeture de connexion manuelle
    try (Connection conn = DriverManager.getConnection(dburl)) {
      PreparedStatement prepare =
          conn.prepareStatement("INSERT INTO Carre VALUES " + "( ?, ? , ? , ? )");
      prepare.setString(1, t.getName());
      prepare.setInt(2, t.getPosition().getX());
      prepare.setInt(3, t.getPosition().getY());
      prepare.setInt(4, t.getSide());
      int result = prepare.executeUpdate();
      if (result == 1) {
        System.out.println("L'ajout du carré s'est bien déroulé");
        DaoFactory.getFormeDao().create(t);
      }
      prepare.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void update(Carre t) throws NonExistentFormeException {
    PreparedStatement prepare = null;
    Connection conn = null;
    try {
      conn = DriverManager.getConnection(dburl);
      prepare = conn.prepareStatement("UPDATE Carre SET x = ?,y = ? WHERE nom = ?");
      prepare.setInt(1, t.getPosition().getX());
      prepare.setInt(2, t.getPosition().getY());
      prepare.setString(3, t.getName());
      int result = prepare.executeUpdate();
      if (result != 1) {
        throw new NonExistentFormeException();
      } else {
        System.out.println(" Le carré a bien été modifié");
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
  public void delete(Carre t) throws NonExistentFormeException {

    PreparedStatement prepare = null;
    Connection conn = null;
    try {
      conn = DriverManager.getConnection(dburl);
      prepare = conn.prepareStatement("DELETE FROM Carre WHERE nom = ?");
      prepare.setString(1, t.getName());
      int result = prepare.executeUpdate();
      if (result == 1) {
        System.out.println("Le carré a été supprimé");
        DaoFactory.getFormeDao().delete(t);
      } else {
        throw new NonExistentFormeException();
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
  public Carre read(String nom) throws NonExistentFormeException {
    Carre c = null;
    PreparedStatement prepare = null;
    Connection conn = null;
    ResultSet result = null;
    try {
      conn = DriverManager.getConnection(dburl);
      prepare = conn.prepareStatement("SELECT * FROM Carre WHERE nom = ?");
      prepare.setString(1, nom);
      result = prepare.executeQuery();

      if (!result.next()) {
        System.out.println("Le carré recherché n'existe pas");

      } else {
        // Crée un carrée
        c = new Carre(result.getString("nom"),
            new Position2D(result.getInt("x"), result.getInt("y")), result.getInt("side"));
        result.close();
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
      if (result != null) {
        try {
          result.close();
        } catch (SQLException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    }

    return c;
  }

}
