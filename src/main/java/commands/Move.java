package commands;

import formes.Carre;
import formes.Cercle;
import formes.CompositeFormes;
import formes.Rectangle;
import formes.Triangle;
import persistance.Dao;
import persistance.DaoFactory;
import ui.NonExistentFormeException;

/**
 * Commande de déplacement des formes.
 *
 * @author Mass'
 *
 */
public class Move implements Commande {

  private String name;
  private String type;
  private int x;
  private int y;

  /**
   * Constructeur de Move.
   *
   * @param name de la forme
   * @param type de la forme
   * @param x coordonée position
   * @param y coordonée position
   */
  public Move(String name, String type, int x, int y) {

    this.name = name;
    this.type = type;
    this.x = x;
    this.y = y;
  }

  /**
   * Récupère le type de forme ou le groupes à déplacer.
   */
  @Override
  public void execute() throws NonExistentFormeException {
    switch (type) {
      case "Cercle":
        Dao<Cercle> daoCercle = DaoFactory.getCercleDao();
        Cercle cercle = daoCercle.read(name);
        cercle.move(x, y);
        daoCercle.update(cercle);
        break;
      case "Carre":
        Dao<Carre> daoCarre = DaoFactory.getCarreDao();
        Carre carre = daoCarre.read(name);
        carre.move(x, y);
        daoCarre.update(carre);
        break;
      case "Rectangle":
        Dao<Rectangle> daoRectangle = DaoFactory.getRectangleDao();
        Rectangle rectangle = daoRectangle.read(name);
        rectangle.move(x, y);
        daoRectangle.update(rectangle);
        break;
      case "Triangle":
        Dao<Triangle> daoTriangle = DaoFactory.getTriangleDao();
        Triangle triangle = daoTriangle.read(name);
        triangle.move(x, y);
        daoTriangle.update(triangle);
        break;
      case "CompositeFormes":
        Dao<CompositeFormes> daoComposite = DaoFactory.getCompositeFormeDao();
        CompositeFormes cf = daoComposite.read(name);
        cf.move(x, y);
        daoComposite.update(cf);
        break;
      default:
        throw new NonExistentFormeException();

    }
  }

}
