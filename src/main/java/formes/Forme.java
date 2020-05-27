package formes;

/**
 * Superclasse des formes g�ographiques.
 *
 * @author Mass'
 *
 */
public abstract class Forme implements Mover, Printer {
  protected String name;
  protected Position2D position;

  public Forme(String name, Position2D position) {
    this.name = name;
    this.position = position;
  }

  /**
   * D�placer l'objet selon les coordonn�es mis en param�tres.
   */
  @Override
  public void move(int x, int y) {
    this.position.setX(this.position.getX() + x);
    this.position.setY(this.position.getY() + y);
  }

  /**
   * Affiche les coordonn�es de la forme.
   */
  public abstract void print();

  public String getName() {
    return name;
  }

  public Position2D getPosition() {
    return position;
  }
}
