package formes;

public class Cercle extends Forme {

  private int rayon;

  public Cercle(String name, Position2D position, int rayon) {
    super(name, position);
    this.rayon = rayon;
  }



  public int getRayon() {
    return rayon;
  }



  @Override
  public void print() {

    System.out.println("Cercle " + "(" + this.getName() + "=" + this.getPosition().toString()
        + ",rayon = " + this.getRayon() + ")");
  }

}
