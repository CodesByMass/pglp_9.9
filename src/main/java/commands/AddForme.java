package commands;

import formes.CompositeFormes;
import formes.Forme;
import persistance.DaoFactory;
import ui.NonExistentFormeException;

public class AddForme implements Commande {
  private String nomGroupe;
  private String nomForme;

  public AddForme(String nomGroupe, String nomForme) {
    this.nomGroupe = nomGroupe;
    this.nomForme = nomForme;
  }

  @Override
  public void execute() throws NonExistentFormeException {
    CompositeFormes g = DaoFactory.getCompositeFormeDao().read(nomGroupe);
    Forme f = DaoFactory.getFormeDao().read(nomForme);
    g.add(f);
    // Je supprime puis rajoute car l'update de groupe modifie les positions des formes.
    DaoFactory.getCompositeFormeDao().delete(g);
    DaoFactory.getCompositeFormeDao().create(g);



  }

}
