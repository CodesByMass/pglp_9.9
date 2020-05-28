package ui;

/**
 * Déclenchée quand la forme demandée n'existe pas dans la base de données.
 *
 * @author Mass'
 *
 */
public class NonExistentFormeException extends Exception {


  private static final long serialVersionUID = 1565056526343376210L;

  public NonExistentFormeException() {
    System.out.println("Cette forme n'existe pas");
  }

}
