package formes;

/**
 * R�pr�sentation de la forme cercle.
 *
 * @author Mass'
 *
 */
public class Cercle extends Forme {

  private int rayon;
  private Position2D position;

  /**
   * Constructeur de Cercle.
   *
   * @param name du cercle
   * @param position 2D
   * @param rayon longueur.
   */
  public Cercle(String name, Position2D position, int rayon) {
    super(name);
    this.position = position;
    this.rayon = rayon;
  }


  public Position2D getPosition() {
    return position;
  }

  public int getRayon() {
    return rayon;
  }


  /**
   * D�placer l'objet selon les coordonn�es mis en param�tres.
   */
  @Override
  public void move(int x, int y) {
    this.position.setX(this.position.getX() + x);
    this.position.setY(this.position.getY() + y);
  }

  @Override
  public void print() {

    System.out.println("Cercle " + "(" + this.getName() + "=" + this.getPosition().toString()
        + ",rayon = " + this.getRayon() + ")");
  }

}
