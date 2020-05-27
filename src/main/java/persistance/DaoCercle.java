package persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import formes.Cercle;
import formes.Position2D;

/**
 * Impl�mentation de DAO avec Derby pour la classe Cercle.
 *
 * @see Cercle
 *
 * @author Mass'
 *
 */
public class DaoCercle extends Dao<Cercle> {

  /**
   * Chaine de caractere de la base de donn�es.
   */
  private static String dburl = DbConn.dburl;

  @Override
  void create(Cercle t) throws Exception {
    try (Connection conn = DriverManager.getConnection(dburl)) {
      PreparedStatement prepare =
          conn.prepareStatement("INSERT INTO Cercle VALUES " + "( ?, ? , ? , ? )");
      prepare.setString(1, t.getName());
      prepare.setInt(2, t.getPosition().getX());
      prepare.setInt(3, t.getPosition().getY());
      prepare.setInt(4, t.getRayon());
      int result = prepare.executeUpdate();
      if (result == 1) {
        System.out.println("L'ajout du cercle s'est bien d�roul�");
      }
      prepare.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  void update(Cercle t) throws Exception {
    try (Connection conn = DriverManager.getConnection(dburl)) {
      PreparedStatement prepare =
          conn.prepareStatement("UPDATE Cercle SET x = ?,y = ? WHERE nom = ?");
      prepare.setInt(1, t.getPosition().getX());
      prepare.setInt(2, t.getPosition().getY());
      prepare.setString(3, t.getName());
      int result = prepare.executeUpdate();
      if (result != 1) {
        System.out.println("Ce cercle n'existe pas");
      } else {
        System.out.println(" Le cercle a bien �t� modifi�");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  @Override
  void delete(Cercle t) throws Exception {
    try (Connection conn = DriverManager.getConnection(dburl)) {
      PreparedStatement prepare = conn.prepareStatement("DELETE FROM Cercle WHERE nom = ?");
      prepare.setString(1, t.getName());
      int result = prepare.executeUpdate();
      if (result == 1) {
        System.out.println("Le cercle �t� supprim�");
      } else {
        System.out.println("Un probl�me est survenu lors de la suppression");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  @Override
  Cercle read(String nom) throws Exception {
    Cercle c = null;
    try (Connection conn = DriverManager.getConnection(dburl)) {
      PreparedStatement prepare = conn.prepareStatement("SELECT * FROM Cercle WHERE nom = ?");
      prepare.setString(1, nom);
      ResultSet result = prepare.executeQuery();

      if (!result.next()) {
        System.out.println("Le cercle recherch� n'existe pas");
      } else {
        // Cr�e un cerclee.
        c = new Cercle(result.getString("nom"),
            new Position2D(result.getInt("x"), result.getInt("y")), result.getInt("rayon"));
        prepare.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return c;
  }

}
