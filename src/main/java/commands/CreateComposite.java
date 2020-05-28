package commands;

import formes.CompositeFormes;
import formes.Forme;
import java.util.ArrayList;
import persistance.DaoFactory;
import ui.NonExistentFormeException;

/**
 * Cr�e un groupe avec les formes d�j� existantes.
 *
 * @see ui.DrawingTui#createComposite(String)
 *
 * @author Mass'
 *
 */
public class CreateComposite implements Commande {

  private String name;
  /**
   * Liste des formes.
   */
  private ArrayList<String> liste;

  public CreateComposite(String name, ArrayList<String> liste) {
    this.name = name;
    this.liste = liste;
  }

  @Override
  public void execute() throws NonExistentFormeException {
    CompositeFormes cf = new CompositeFormes(name);
    for (int i = 0; i < liste.size(); i++) {
      Forme temp = DaoFactory.getFormeDao().read(liste.get(i));
      cf.add(temp);
    }
    DaoFactory.getCompositeFormeDao().create(cf);
  }

}
