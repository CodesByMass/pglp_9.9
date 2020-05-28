package commands;

import formes.Carre;
import formes.Cercle;
import formes.CompositeFormes;
import formes.Forme;
import formes.Rectangle;
import formes.Triangle;
import persistance.DaoFactory;
import ui.NonExistentFormeException;

/**
 * Supprime une forme ou un groupe.
 *
 * @author Mass'
 *
 */
public class Delete implements Commande {

  private Forme forme;

  public Delete(Forme forme) {
    this.forme = forme;
  }

  /**
   * Récupère le type de la forme puis supprime de la base de données.
   */
  @Override
  public void execute() throws NonExistentFormeException {

    String type = forme.getClass().getSimpleName().toLowerCase();
    switch (type) {
      case "cercle":
        DaoFactory.getCercleDao().delete((Cercle) forme);
        break;
      case "carre":
        DaoFactory.getCarreDao().delete((Carre) forme);
        break;
      case "rectangle":
        DaoFactory.getRectangleDao().delete((Rectangle) forme);
        break;
      case "triangle":
        DaoFactory.getTriangleDao().delete((Triangle) forme);
        break;
      case "compositeformes":
        DaoFactory.getCompositeFormeDao().delete((CompositeFormes) forme);
        break;
      default:
        System.out.println("Ca ne risque pas d'arriver");

    }

  }


}
