package formes;

/**
 * Classe repr�sentant un carr�.
 *
 * @author Mass'
 *
 */
public class Carre extends Forme {

  /**
   * Cot� d'un carr�.
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
   * D�placer l'objet selon les coordonn�es mis en param�tres.
   */
  @Override
  public void move(int x, int y) {
    this.position.setX(this.position.getX() + x);
    this.position.setY(this.position.getY() + y);
  }

  @Override
  public void print() {
    System.out.println("Carr� " + "(" + this.getName() + "=" + this.getPosition().toString()
        + ",cot� = " + this.getSide() + ")");

  }



}
