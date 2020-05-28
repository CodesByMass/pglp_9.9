package persistance;

import formes.Cercle;
import formes.Position2D;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import ui.NonExistentFormeException;

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
  public void create(Cercle t) throws NonExistentFormeException {
    PreparedStatement prepare = null;
    Connection conn = null;
    try {
      conn = DriverManager.getConnection(dburl);
      prepare = conn.prepareStatement("INSERT INTO Cercle VALUES " + "( ?, ? , ? , ? )");
      prepare.setString(1, t.getName());
      prepare.setInt(2, t.getPosition().getX());
      prepare.setInt(3, t.getPosition().getY());
      prepare.setInt(4, t.getRayon());
      int result = prepare.executeUpdate();
      if (result == 1) {
        System.out.println("L'ajout du cercle s'est bien d�roul�");
        DaoFactory.getFormeDao().create(t);
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
    }
  }

  @Override
  public void update(Cercle t) throws NonExistentFormeException {
    PreparedStatement prepare = null;
    Connection conn = null;
    try {
      conn = DriverManager.getConnection(dburl);
      prepare = conn.prepareStatement("UPDATE Cercle SET x = ?,y = ? WHERE nom = ?");
      prepare.setInt(1, t.getPosition().getX());
      prepare.setInt(2, t.getPosition().getY());
      prepare.setString(3, t.getName());
      int result = prepare.executeUpdate();
      if (result != 1) {
        System.out.println("Le cercle n'existe pas dans la base");
      } else {
        System.out.println(" Le cercle a bien été modifié");
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
  public void delete(Cercle t) throws NonExistentFormeException {
    PreparedStatement prepare = null;
    Connection conn = null;
    try {
      conn = DriverManager.getConnection(dburl);
      prepare = conn.prepareStatement("DELETE FROM Cercle WHERE nom = ?");
      prepare.setString(1, t.getName());
      int result = prepare.executeUpdate();
      if (result == 1) {
        System.out.println("Le cercle été supprimé");
        DaoFactory.getFormeDao().delete(t);
      } else {
        System.out.println("Le cercle n'est pas dans cette base de données");
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
  public Cercle read(String nom) throws NonExistentFormeException {
    Cercle c = null;
    PreparedStatement prepare = null;
    Connection conn = null;
    ResultSet result = null;
    try {
      conn = DriverManager.getConnection(dburl);
      prepare = conn.prepareStatement("SELECT * FROM Cercle WHERE nom = ?");
      prepare.setString(1, nom);
      result = prepare.executeQuery();

      if (!result.next()) {
        System.out.println("Le cercle recherché n'existe pas");
      } else {
        // Cr�e un cerclee.
        c = new Cercle(result.getString("nom"),
            new Position2D(result.getInt("x"), result.getInt("y")), result.getInt("rayon"));
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

    return c;
  }

}
