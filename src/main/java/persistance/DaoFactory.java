package persistance;

import formes.Carre;
import formes.Cercle;
import formes.CompositeFormes;
import formes.Forme;
import formes.Rectangle;
import formes.Triangle;

/**
 * La Factory.
 *
 * @author Mass'
 *
 */
public class DaoFactory {


  public static Dao<Forme> getFormeDao() {
    return new DaoForme();
  }

  public static Dao<Carre> getCarreDao() {
    return new DaoCarre();
  }

  public static Dao<Cercle> getCercleDao() {
    return new DaoCercle();
  }

  public static Dao<Rectangle> getRectangleDao() {
    return new DaoRectangle();
  }

  public static Dao<Triangle> getTriangleDao() {
    return new DaoTriangle();
  }


  public static Dao<CompositeFormes> getCompositeFormeDao() {
    return new DaoComposite();
  }
}
