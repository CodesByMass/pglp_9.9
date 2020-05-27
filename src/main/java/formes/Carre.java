package formes;

public class Carre extends Forme {

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
    System.out.println("Carr� " + "(" + this.getName() + "=" + this.getPosition().toString()
        + ",cot� = " + this.getSide() + ")");

  }

}
