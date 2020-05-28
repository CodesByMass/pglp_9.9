package formes;

public class Triangle extends Forme {

  private Position2D position;
  int side1;
  int side2;
  int side3;

  public Triangle(String name, Position2D position, int side1, int side2, int side3) {
    super(name);
    this.position = position;
    this.side1 = side1;
    this.side2 = side2;
    this.side3 = side3;
  }


  public Position2D getPosition() {
    return position;
  }

  public int getSide1() {
    return side1;
  }



  public int getSide2() {
    return side2;
  }



  public int getSide3() {
    return side3;
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
    System.out.println("Triangle " + "(" + this.getName() + "=" + this.getPosition().toString()
        + ",coté 1 = " + this.getSide1() + ",coté 3 = " + this.getSide2() + ",coté 3 = "
        + this.getSide3() + ")");

  }

}
