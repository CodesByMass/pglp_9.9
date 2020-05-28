package persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import formes.Carre;
import formes.Position2D;
import ui.NonExistentFormeException;

/**
 * Implémentation de DAO avec Derby pour la classe Carré.
 *
 * @see Carre
 *
 * @author Mass'
 *
 */
public class DaoCarre extends Dao<Carre> {

  /**
   * Chaine de caractere de la base de données.
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
    try (Connection conn = DriverManager.getConnection(dburl)) {
      PreparedStatement prepare =
          conn.prepareStatement("UPDATE Carre SET x = '?',y = '?' WHERE nom = '?'");
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
    }

  }

  @Override
  public void delete(Carre t) throws NonExistentFormeException {

    try (Connection conn = DriverManager.getConnection(dburl)) {
      PreparedStatement prepare = conn.prepareStatement("DELETE FROM Carre WHERE nom = '?'");
      prepare.setString(1, t.getName());
      int result = prepare.executeUpdate();
      if (result == 1) {
        System.out.println("Le carré été supprimé");
      } else {
        throw new NonExistentFormeException();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  @Override
  public Carre read(String nom) throws NonExistentFormeException {
    Carre c = null;
    try (Connection conn = DriverManager.getConnection(dburl)) {
      PreparedStatement prepare = conn.prepareStatement("SELECT * FROM Carre WHERE nom = '?'");
      prepare.setString(1, nom);
      ResultSet result = prepare.executeQuery();

      if (!result.next()) {
        System.out.println("Le carré recherché n'existe pas");
        throw new NonExistentFormeException();
      } else {
        // Crée un carrée.
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
