package commands;

import formes.Forme;
import ui.NonExistentFormeException;

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
