package persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import formes.Carre;
import formes.Cercle;
import formes.CompositeFormes;
import formes.Forme;
import formes.Rectangle;
import formes.Triangle;

/**
 * Implémentation de la persistance pour les groupes de forme.
 *
 * @see Dao
 * @see CompositeFormes
 *
 * @author Mass'
 *
 */
public class DaoComposite extends Dao<CompositeFormes> {

  /**
   * Chaine de caractere de la base de données.
   */
  private static String dburl = DbConn.dburl;

  @Override
  void create(CompositeFormes t) throws Exception {
    try (Connection conn = DriverManager.getConnection(dburl)) {
      PreparedStatement prepare = conn.prepareStatement("INSERT INTO Groupe VALUES (?)");
      prepare.setString(1, t.getName());
      prepare.executeUpdate();
      prepare.close();
      // Ajouter les membres du groupe
      ArrayList<Forme> listeFormes = t.getChildsFormes();
      for (Forme temp : listeFormes) {
        // Vérifier que la forme est dans la base
        Dao forme = (Dao) new Object();
        if (forme.read((temp.getName())) == null) {
          forme.create(temp);
        }
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
  void update(CompositeFormes t) throws Exception {
    try (Connection conn = DriverManager.getConnection(dburl)) {
      PreparedStatement prepare =
          conn.prepareStatement("SELECT * FROM FaitPartie WHERE nomGroupe = ?");
      prepare.setString(1, t.getName());
      ResultSet result = prepare.executeQuery();
      ArrayList<Forme> listeForme = t.getChildsFormes();
      DaoCercle cercle = new DaoCercle();
      DaoCarre carre = new DaoCarre();
      DaoRectangle rectangle = new DaoRectangle();
      DaoTriangle triangle = new DaoTriangle();
      Cercle cercle1 = null;
      Carre carre1 = null;
      Rectangle rectangle1 = null;
      Triangle triangle1 = null;
      int i = 0;
      while (result.next()) {
        switch (result.getString("typeForme")) {
          case "Carre":
            carre1 = (Carre) listeForme.get(i);
            carre.update(carre1);
            i++;
            break;
          case "Cercle":
            cercle1 = (Cercle) listeForme.get(i);
            cercle.update(cercle1);
            i++;
            break;
          case "Triangle":
            triangle1 = (Triangle) listeForme.get(i);
            triangle.update(triangle1);
            i++;
            break;
          case "Rectangle":
            rectangle1 = (Rectangle) listeForme.get(i);
            rectangle.update(rectangle1);
            i++;
            break;
          default:
            System.out.println("Il y a une erreur");
            break;
        }
      }
    } catch (

    SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Grace au ON DELETE CASCADE, la table FaitPartie sera vidée de toutes les références vers ce
   * groupe.
   */
  @Override
  void delete(CompositeFormes t) throws Exception {
    try (Connection conn = DriverManager.getConnection(dburl)) {
      PreparedStatement prepare = conn.prepareStatement("DELETE FROM Groupe WHERE nom = ?");
      prepare.setString(1, t.getName());
      prepare.executeUpdate();
      prepare.close();

    } catch (SQLException e) {
      e.printStackTrace();
    }

  }

  @Override
  CompositeFormes read(String nom) throws Exception {
    CompositeFormes groupe = null;
    try (Connection conn = DriverManager.getConnection(dburl)) {
      PreparedStatement prepare =
          conn.prepareStatement("SELECT FROM FaitPartie WHERE nomGroupe = ?");
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
      }
      groupe = new CompositeFormes(nom, listeForme);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return groupe;
  }
}


