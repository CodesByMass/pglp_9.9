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
        Dao<Cercle> DaoCercle = DaoFactory.getCercleDao();
        Cercle cercle = DaoCercle.read(name);
        cercle.move(x, y);
        DaoCercle.update(cercle);
        break;
      case "Carre":
        Dao<Carre> DaoCarre = DaoFactory.getCarreDao();
        Carre carre = DaoCarre.read(name);
        carre.move(x, y);
        DaoCarre.update(carre);
        break;
      case "Rectangle":
        Dao<Rectangle> DaoRectangle = DaoFactory.getRectangleDao();
        Rectangle rectangle = DaoRectangle.read(name);
        rectangle.move(x, y);
        DaoRectangle.update(rectangle);
        break;
      case "Triangle":
        Dao<Triangle> DaoTriangle = DaoFactory.getTriangleDao();
        Triangle triangle = DaoTriangle.read(name);
        triangle.move(x, y);
        DaoTriangle.update(triangle);
        break;
      case "CompositeFormes":
        Dao<CompositeFormes> daoComposite = DaoFactory.getCompositeFormeDao();
        CompositeFormes cf = daoComposite.read(name);
        cf.move(x, y);
        daoComposite.update(cf);
        break;
    }
  }

}
