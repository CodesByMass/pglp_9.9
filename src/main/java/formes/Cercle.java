package formes;

/**
 * Réprésentation de la forme cercle.
 *
 * @author Mass'
 *
 */
public class Cercle extends Forme {

  private int rayon;
  private Position2D position;

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
   * Déplacer l'objet selon les coordonnées mis en paramètres.
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
