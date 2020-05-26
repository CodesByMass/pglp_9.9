package formes;

/**
 * Superclasse des formes géographiques.
 *
 * @author Mass'
 *
 */
public abstract class Forme {
  protected String name;
  protected Position2D position;

  public Forme(String name, Position2D position) {
    this.name = name;
    this.position = position;
  }

  public void move(int x, int y) {
    this.position.setX(this.position.getX() + x);
    this.position.setY(this.position.getY() + y);
  }

  public abstract void print();

  public String getName() {
    return name;
  }

  public Position2D getPosition() {
    return position;
  }
}
