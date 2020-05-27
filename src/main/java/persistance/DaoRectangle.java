package persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import formes.Position2D;
import formes.Rectangle;

/**
 * Implémentation de DAO avec Derby pour la classe Rectangle.
 *
 * @see Rectangle
 *
 * @author Mass'
 *
 */
public class DaoRectangle extends Dao<Rectangle> {

  /**
   * Chaine de caractere de la base de données.
   */
  private static String dburl = DbConn.dburl;

  @Override
  void create(Rectangle t) throws Exception {
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
      }
      prepare.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  @Override
  void update(Rectangle t) throws Exception {
    try (Connection conn = DriverManager.getConnection(dburl)) {
      PreparedStatement prepare =
          conn.prepareStatement("UPDATE Rectangle SET x = ?,y = ? WHERE nom = ?");
      prepare.setInt(1, t.getPosition().getX());
      prepare.setInt(2, t.getPosition().getY());
      prepare.setString(3, t.getName());
      int result = prepare.executeUpdate();
      if (result != 1) {
        System.out.println("Ce rectangle n'existe pas");
      } else {
        System.out.println(" Le rectangle a bien été modifié");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  @Override
  void delete(Rectangle t) throws Exception {
    try (Connection conn = DriverManager.getConnection(dburl)) {
      PreparedStatement prepare = conn.prepareStatement("DELETE FROM Rectangle WHERE nom = ?");
      prepare.setString(1, t.getName());
      int result = prepare.executeUpdate();
      if (result == 1) {
        System.out.println("Le rectangle été supprimé");
      } else {
        System.out.println("Un problème est survenu lors de la suppression");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  @Override
  Rectangle read(String nom) throws Exception {
    Rectangle r = null;
    try (Connection conn = DriverManager.getConnection(dburl)) {
      PreparedStatement prepare = conn.prepareStatement("SELECT * FROM Rectangle WHERE nom = ?");
      prepare.setString(1, nom);
      ResultSet result = prepare.executeQuery();

      if (!result.next()) {
        System.out.println("Le rectangle recherché n'existe pas");
      } else {
        // Crée un rectangle.
        r = new Rectangle(result.getString("nom"),
            new Position2D(result.getInt("x"), result.getInt("y")), result.getInt("longueur"),
            result.getInt("largeur"));
        prepare.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return r;
  }

}
