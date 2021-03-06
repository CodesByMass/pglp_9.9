package persistance;

import formes.Position2D;
import formes.Triangle;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import ui.NonExistentFormeException;

/**
 * Impl�mentation de DAO avec Derby pour la classe Triangle.
 *
 * @see Triangle
 *
 * @author Mass'
 *
 */
public class DaoTriangle extends Dao<Triangle> {

  /**
   * Chaine de caractere de la base de donn�es.
   */
  private static String dburl = DbConn.dburl;

  @Override
  public void create(Triangle t) throws NonExistentFormeException {
    try (Connection conn = DriverManager.getConnection(dburl)) {
      PreparedStatement prepare =
          conn.prepareStatement("INSERT INTO Triangle VALUES " + "( ?, ? , ? , ?, ?, ? )");
      prepare.setString(1, t.getName());
      prepare.setInt(2, t.getPosition().getX());
      prepare.setInt(3, t.getPosition().getY());
      prepare.setInt(4, t.getSide1());
      prepare.setInt(5, t.getSide2());
      prepare.setInt(6, t.getSide3());
      int result = prepare.executeUpdate();
      if (result == 1) {
        System.out.println("L'ajout du triangle s'est bien déroulé");
        DaoFactory.getFormeDao().create(t);
      }
      prepare.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  @Override
  public void update(Triangle t) throws NonExistentFormeException {
    PreparedStatement prepare = null;
    Connection conn = null;
    try {
      conn = DriverManager.getConnection(dburl);
      prepare = conn.prepareStatement("UPDATE Triangle SET x = ?,y = ? WHERE nom = ?");
      prepare.setInt(1, t.getPosition().getX());
      prepare.setInt(2, t.getPosition().getY());
      prepare.setString(3, t.getName());
      int result = prepare.executeUpdate();
      if (result != 1) {
        System.out.println("Le triangle n'existe pas");
      } else {
        System.out.println(" Le triangle a bien été modifié");
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
  public void delete(Triangle t) throws NonExistentFormeException {
    PreparedStatement prepare = null;
    Connection conn = null;
    try {
      conn = DriverManager.getConnection(dburl);
      prepare = conn.prepareStatement("DELETE FROM Triangle WHERE nom = ?");
      prepare.setString(1, t.getName());
      int result = prepare.executeUpdate();
      if (result == 1) {
        System.out.println("Le triangle été supprimé");
        DaoFactory.getFormeDao().delete(t);
      } else {
        System.out.println("Le triangle n'existe pas");
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
  public Triangle read(String nom) throws NonExistentFormeException {
    Triangle t = null;
    PreparedStatement prepare = null;
    Connection conn = null;
    ResultSet result = null;
    try {
      conn = DriverManager.getConnection(dburl);
      prepare = conn.prepareStatement("SELECT * FROM Triangle WHERE nom = ?");
      prepare.setString(1, nom);
      result = prepare.executeQuery();

      if (!result.next()) {
        System.out.println("Ce triangle n'existe pas");
      } else {
        // Cr�e un triangle.
        t = new Triangle(result.getString("nom"),
            new Position2D(result.getInt("x"), result.getInt("y")), result.getInt("side1"),
            result.getInt("side2"), result.getInt("side3"));
        prepare.close();
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
    return t;
  }

}
