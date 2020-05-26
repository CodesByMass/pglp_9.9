package formes;

public class Rectangle extends Forme {

  private int longueur;
  private int largeur;

  public Rectangle(String name, Position2D position, int longueur, int largeur) {
    super(name, position);
    this.longueur = longueur;
    this.largeur = largeur;
  }

  public int getLongueur() {
    return longueur;
  }

  public int getLargeur() {
    return largeur;
  }


  @Override
  public void print() {
    System.out.println("Rectangle " + "(" + this.getName() + "=" + this.getPosition().toString()
        + ",longueur = " + this.getLongueur() + ", largeur = " + this.getLargeur() + ")");
  }

}
