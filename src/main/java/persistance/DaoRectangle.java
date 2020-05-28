package persistance;

import formes.Position2D;
import formes.Rectangle;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import ui.NonExistentFormeException;

/**
 * Impl�mentation de DAO avec Derby pour la classe Rectangle.
 *
 * @see Rectangle
 *
 * @author Mass'
 *
 */
public class DaoRectangle extends Dao<Rectangle> {

  /**
   * Chaine de caractere de la base de donn�es.
   */
  private static String dburl = DbConn.dburl;

  @Override
  public void create(Rectangle t) throws NonExistentFormeException {
    try (Connection conn = DriverManager.getConnection(dburl)) {
      PreparedStatement prepare =
          conn.prepareStatement("INSERT INTO Rectangle VALUES " + "( ?, ? , ? , ?, ? )");
      prepare.setString(1, t.getName());
      prepare.setInt(2, t.getPosition().getX());
      prepare.setInt(3, t.getPosition().getY());
      prepare.setInt(4, t.getLongueur());
      prepare.setInt(5, t.getLargeur());
      int result = prepare.executeUpdate();
      if (result == 1) {
        System.out.println("L'ajout du rectangle s'est bien déroulé");
        DaoFactory.getFormeDao().create(t);
      }
      prepare.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  @Override
  public void update(Rectangle t) throws NonExistentFormeException {
    PreparedStatement prepare = null;
    Connection conn = null;
    try {
      conn = DriverManager.getConnection(dburl);
      prepare = conn.prepareStatement("UPDATE Rectangle SET x = ?,y = ? WHERE nom = ?");
      prepare.setInt(1, t.getPosition().getX());
      prepare.setInt(2, t.getPosition().getY());
      prepare.setString(3, t.getName());
      int result = prepare.executeUpdate();
      if (result != 1) {
        System.out.println("Le rectangle n'existe pas");
      } else {
        System.out.println(" Le rectangle a bien été modifié");
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
  public void delete(Rectangle t) throws NonExistentFormeException {
    PreparedStatement prepare = null;
    Connection conn = null;
    try {
      conn = DriverManager.getConnection(dburl);
      prepare = conn.prepareStatement("DELETE FROM Rectangle WHERE nom = ?");
      prepare.setString(1, t.getName());
      int result = prepare.executeUpdate();
      if (result == 1) {
        System.out.println("Le rectangle a été supprim�");
        DaoFactory.getFormeDao().delete(t);
      } else {
        System.out.println("Le rectangle n'existe pas");
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
  public Rectangle read(String nom) throws NonExistentFormeException {
    Rectangle r = null;
    PreparedStatement prepare = null;
    Connection conn = null;
    ResultSet result = null;
    try {
      conn = DriverManager.getConnection(dburl);
      prepare = conn.prepareStatement("SELECT * FROM Rectangle WHERE nom = ?");
      prepare.setString(1, nom);
      result = prepare.executeQuery();

      if (!result.next()) {
        System.out.println("Le rectangle n'existe pas");
      } else {
        // Cr�e un rectangle.
        r = new Rectangle(result.getString("nom"),
            new Position2D(result.getInt("x"), result.getInt("y")), result.getInt("longueur"),
            result.getInt("largeur"));
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

    return r;
  }

}
