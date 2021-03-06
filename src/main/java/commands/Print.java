package commands;

import formes.Forme;
import ui.NonExistentFormeException;

/**
 * Affiche les informations d'une forme ou d'un groupe.
 *
 * @author Mass'
 *
 */
public class Print implements Commande {

  private Forme forme;

  public Print(Forme forme) {
    this.forme = forme;
  }

  @Override
  public void execute() throws NonExistentFormeException {
    this.forme.print();

  }


}
