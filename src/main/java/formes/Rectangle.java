package formes;

public class Rectangle extends Forme {

  private Position2D position;
  private int longueur;
  private int largeur;

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
   * Déplacer l'objet selon les coordonnées mis en paramètres.
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
