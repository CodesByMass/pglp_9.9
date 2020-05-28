package ui;

/**
 * Déclenchée quand le type de la forme n'est pas reconnue par le programme. Par exemple quand
 * l'utilisateur crée une forme.
 *
 * @author Mass'
 *
 */
public class FalseTypeException extends Exception {


  private static final long serialVersionUID = 8570598671480876310L;

  public FalseTypeException() {
    System.out.println("Ce type de forme n'existe pas ");
  }

}
