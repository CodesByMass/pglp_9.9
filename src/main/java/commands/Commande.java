package commands;

import ui.NonExistentFormeException;

/**
 * L'interface commande avec la méthode execute.
 *
 * @author Mass'
 *
 */
public interface Commande {

  public void execute() throws NonExistentFormeException;
}
