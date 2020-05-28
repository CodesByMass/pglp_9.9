package commands;

import formes.Carre;
import formes.Cercle;
import formes.Position2D;
import formes.Rectangle;
import formes.Triangle;
import java.util.ArrayList;
import persistance.DaoCarre;
import persistance.DaoCercle;
import persistance.DaoRectangle;
import persistance.DaoTriangle;

/**
 * Cr�e une forme, invoqu�e par DrawingTui.
 *
 * @see ui.DrawingTui#create(String)
 *
 * @author Mass'
 *
 */
public class Create implements Commande {

  String type;
  String name;
  int x;
  int y;
  ArrayList<Integer> params;

  /**
   * Constructeur.
   *
   * @param type de la forme
   * @param name de la forme
   * @param x coordonnée
   * @param y coordonnée
   * @param params spécifiques à chaque forme
   */
  public Create(String type, String name, int x, int y, ArrayList<Integer> params) {
    this.type = type;
    this.name = name;
    this.x = x;
    this.y = y;
    this.params = params;
  }

  @Override
  public void execute() {
    try {
      switch (type) {
        case "cercle":
          Cercle cercle = new Cercle(name, new Position2D(x, y), params.get(0).intValue());
          new DaoCercle().create(cercle);
          break;
        case "carre":
          Carre carre = new Carre(name, new Position2D(x, y), params.get(0).intValue());
          new DaoCarre().create(carre);
          break;
        case "rectangle":
          Rectangle rectangle = new Rectangle(name, new Position2D(x, y), params.get(0).intValue(),
              params.get(1).intValue());
          new DaoRectangle().create(rectangle);
          break;
        case "triangle":
          Triangle triangle =
              new Triangle(name, new Position2D(x, y), params.get(0), params.get(1), params.get(2));
          new DaoTriangle().create(triangle);
          break;
        default:
          System.out.println("Cette forme est incorrecte");

      }
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}
