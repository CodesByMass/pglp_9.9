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

  /**
   * Constructeur de la forme carré.
   *
   * @param name du carré
   * @param position 2D
   * @param side taille des cotés.
   */
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
    System.out.println("Carré " + "(" + this.getName() + "=" + this.getPosition().toString()
        + ",coté = " + this.getSide() + ")");

  }



}
