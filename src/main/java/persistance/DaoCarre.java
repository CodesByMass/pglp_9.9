package persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import formes.Carre;
import formes.Position2D;

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
  void create(Carre t) throws Exception {
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
        System.out.println("L'ajout du carr� s'est bien d�roul�");
      }
      prepare.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @Override
  void update(Carre t) throws Exception {
    try (Connection conn = DriverManager.getConnection(dburl)) {
      PreparedStatement prepare =
          conn.prepareStatement("UPDATE Carre SET x = ?,y = ? WHERE nom = ?");
      prepare.setInt(1, t.getPosition().getX());
      prepare.setInt(2, t.getPosition().getY());
      prepare.setString(3, t.getName());
      int result = prepare.executeUpdate();
      if (result != 1) {
        System.out.println("Ce carr� n'existe pas");
      } else {
        System.out.println(" Le carr� a bien �t� modifi�");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  @Override
  void delete(Carre t) throws Exception {

    try (Connection conn = DriverManager.getConnection(dburl)) {
      PreparedStatement prepare = conn.prepareStatement("DELETE FROM Carre WHERE nom = ?");
      prepare.setString(1, t.getName());
      int result = prepare.executeUpdate();
      if (result == 1) {
        System.out.println("Le carr� �t� supprim�");
      } else {
        System.out.println("Un probl�me est survenu lors de la suppression");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  @Override
  Carre read(String nom) throws Exception {
    Carre c = null;
    try (Connection conn = DriverManager.getConnection(dburl)) {
      PreparedStatement prepare = conn.prepareStatement("SELECT * FROM Carre WHERE nom = ?");
      prepare.setString(1, nom);
      ResultSet result = prepare.executeQuery();

      if (!result.next()) {
        System.out.println("Le carr� recherch� n'existe pas");
      } else {
        // Cr�e un carr�e.
        c = new Carre(result.getString("nom"),
            new Position2D(result.getInt("x"), result.getInt("y")), result.getInt("side"));
        prepare.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return c;
  }

}
