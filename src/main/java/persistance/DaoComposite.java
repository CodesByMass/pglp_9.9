package persistance;

import formes.Carre;
import formes.Cercle;
import formes.CompositeFormes;
import formes.Forme;
import formes.Rectangle;
import formes.Triangle;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import ui.NonExistentFormeException;

/**
 * Impl�mentation de la persistance pour les groupes de forme.
 *
 * @see Dao
 * @see CompositeFormes
 *
 * @author Mass'
 *
 */
public class DaoComposite extends Dao<CompositeFormes> {

  /**
   * Chaine de caractere de la base de donn�es.
   */
  private static String dburl = DbConn.dburl;

  @Override
  public void create(CompositeFormes t) throws NonExistentFormeException {
    try (Connection conn = DriverManager.getConnection(dburl)) {
      PreparedStatement prepare = conn.prepareStatement("INSERT INTO Groupe VALUES (?)");
      prepare.setString(1, t.getName());
      prepare.executeUpdate();
      prepare.close();
      DaoFactory.getFormeDao().create(t);
      // Ajouter les membres du groupe
      ArrayList<Forme> listeFormes = t.getChildsFormes();
      for (Forme temp : listeFormes) {
        prepare = conn.prepareStatement("INSERT INTO FaitPartie VALUES (?, ?, ?)");
        prepare.setString(1, t.getName());
        prepare.setString(2, temp.getName());
        prepare.setString(3, temp.getClass().getSimpleName());
        prepare.executeUpdate();
        prepare.close();
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  @Override
  public void update(CompositeFormes t) throws NonExistentFormeException {
    PreparedStatement prepare = null;
    Connection conn = null;
    ResultSet result = null;
    try {
      conn = DriverManager.getConnection(dburl);
      prepare = conn.prepareStatement("SELECT * FROM FaitPartie WHERE nomGroupe = ?");
      prepare.setString(1, t.getName());
      result = prepare.executeQuery();
      ArrayList<Forme> listeForme = t.getChildsFormes();
      DaoCercle cercleDao = new DaoCercle();
      DaoCarre carreDao = new DaoCarre();
      DaoRectangle rectangleDao = new DaoRectangle();
      DaoTriangle triangleDao = new DaoTriangle();
      Cercle cercle = null;
      Carre carre = null;
      Rectangle rectangle = null;
      Triangle triangle = null;
      int i = 0;
      while (result.next()) {
        switch (result.getString("typeForme")) {
          case "Carre":
            carre = (Carre) listeForme.get(i);
            carreDao.update(carre);
            i++;
            break;
          case "Cercle":
            cercle = (Cercle) listeForme.get(i);
            cercleDao.update(cercle);
            i++;
            break;
          case "Triangle":
            triangle = (Triangle) listeForme.get(i);
            triangleDao.update(triangle);
            i++;
            break;
          case "Rectangle":
            rectangle = (Rectangle) listeForme.get(i);
            rectangleDao.update(rectangle);
            i++;
            break;
          default:
            System.out.println("Il y a une erreur");
            break;
        }
      }
      result.close();
    } catch (

    SQLException e) {
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
  }

  /**
   * Grace au ON DELETE CASCADE, la table FaitPartie sera vidée de toutes les références vers ce
   * groupe.
   *
   * @throws NonExistentFormeException si le groupe n'existe pas.
   */
  @Override
  public void delete(CompositeFormes t) throws NonExistentFormeException {
    try (Connection conn = DriverManager.getConnection(dburl)) {
      PreparedStatement prepare = conn.prepareStatement("DELETE FROM Groupe WHERE nom = ?");
      prepare.setString(1, t.getName());
      prepare.executeUpdate();
      prepare.close();
      DaoFactory.getFormeDao().delete(t);

    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  @Override
  public CompositeFormes read(String nom) throws NonExistentFormeException {
    CompositeFormes groupe = null;
    PreparedStatement prepare = null;
    Connection conn = null;
    try {
      conn = DriverManager.getConnection(dburl);
      prepare = conn.prepareStatement("SELECT FROM FaitPartie WHERE nomGroupe = ?");
      prepare.setString(1, nom);

      ResultSet result = prepare.executeQuery();
      ArrayList<Forme> listeForme = new ArrayList<Forme>();
      DaoCercle cercle = new DaoCercle();
      DaoCarre carre = new DaoCarre();
      DaoRectangle rectangle = new DaoRectangle();
      DaoTriangle triangle = new DaoTriangle();
      while (result.next()) {
        switch (result.getString("typeForme")) {
          case "Carre":
            listeForme.add(carre.read(result.getString("nomForme")));
            break;
          case "Cercle":
            listeForme.add(cercle.read(result.getString("nomForme")));
            break;
          case "Triangle":
            listeForme.add(triangle.read(result.getString("nomForme")));
            break;
          case "Rectangle":
            listeForme.add(rectangle.read(result.getString("nomForme")));
            break;
          default:
            System.out.println("Il y a une erreur");
            break;
        }
        result.close();
      }
      groupe = new CompositeFormes(nom, listeForme);
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
    return groupe;
  }
}


