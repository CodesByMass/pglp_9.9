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

  public Carre(String name, Position2D position, int side) {
    super(name, position);
    this.side = side;
  }



  public int getSide() {
    return side;
  }



  @Override
  public void print() {
    System.out.println("Carré " + "(" + this.getName() + "=" + this.getPosition().toString()
        + ",coté = " + this.getSide() + ")");

  }

}
