package persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import formes.Position2D;
import formes.Triangle;
import ui.NonExistentFormeException;

/**
 * Implémentation de DAO avec Derby pour la classe Triangle.
 *
 * @see Triangle
 *
 * @author Mass'
 *
 */
public class DaoTriangle extends Dao<Triangle> {

  /**
   * Chaine de caractere de la base de données.
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
    try (Connection conn = DriverManager.getConnection(dburl)) {
      PreparedStatement prepare =
          conn.prepareStatement("UPDATE Triangle SET x = '?',y = '?' WHERE nom = '?'");
      prepare.setInt(1, t.getPosition().getX());
      prepare.setInt(2, t.getPosition().getY());
      prepare.setString(3, t.getName());
      int result = prepare.executeUpdate();
      if (result != 1) {
        throw new NonExistentFormeException();
      } else {
        System.out.println(" Le triangle a bien été modifié");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  @Override
  public void delete(Triangle t) throws NonExistentFormeException {
    try (Connection conn = DriverManager.getConnection(dburl)) {
      PreparedStatement prepare = conn.prepareStatement("DELETE FROM Triangle WHERE nom = '?'");
      prepare.setString(1, t.getName());
      int result = prepare.executeUpdate();
      if (result == 1) {
        System.out.println("Le triangle été supprimé");
      } else {
        throw new NonExistentFormeException();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  @Override
  public Triangle read(String nom) throws NonExistentFormeException {
    Triangle t = null;
    try (Connection conn = DriverManager.getConnection(dburl)) {
      PreparedStatement prepare = conn.prepareStatement("SELECT * FROM Triangle WHERE nom = '?'");
      prepare.setString(1, nom);
      ResultSet result = prepare.executeQuery();

      if (!result.next()) {
        throw new NonExistentFormeException();
      } else {
        // Crée un triangle.
        t = new Triangle(result.getString("nom"),
            new Position2D(result.getInt("x"), result.getInt("y")), result.getInt("side1"),
            result.getInt("side2"), result.getInt("side3"));
        prepare.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return t;
  }

}
