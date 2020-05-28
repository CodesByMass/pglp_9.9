package formes;

public class Rectangle extends Forme {

  private Position2D position;
  private int longueur;
  private int largeur;

  /**
   * Constructeur de Rectangle.
   *
   * @param name de la forme
   * @param position x et y.
   * @param longueur en pixels
   * @param largeur en pixels
   */
  public Rectangle(String name, Position2D position, int longueur, int largeur) {
    super(name);
    this.position = position;
    this.longueur = longueur;
    this.largeur = largeur;
  }


  public Position2D getPosition() {
    return position;
  }

  public int getLongueur() {
    return longueur;
  }

  public int getLargeur() {
    return largeur;
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
    System.out.println("Rectangle " + "(" + this.getName() + "=" + this.getPosition().toString()
        + ",longueur = " + this.getLongueur() + ", largeur = " + this.getLargeur() + ")");
  }

}
