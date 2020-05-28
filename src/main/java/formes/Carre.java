package formes;

/**
 * Classe représentant un carré.
 *
 * @author Mass'
 *
 */
public class Carre extends Forme {

  /**
   * Coté d'un carré.
   */
  private int side;
  private Position2D position;

  public Carre(String name, Position2D position, int side) {
    super(name);
    this.position = position;
    this.side = side;
  }



  public Position2D getPosition() {
    return position;
  }

  public int getSide() {
    return side;
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
    System.out.println("Carré " + "(" + this.getName() + "=" + this.getPosition().toString()
        + ",coté = " + this.getSide() + ")");

  }



}
