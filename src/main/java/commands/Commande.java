package commands;

import ui.NonExistentFormeException;

public interface Commande {

  public void execute() throws NonExistentFormeException;
}
