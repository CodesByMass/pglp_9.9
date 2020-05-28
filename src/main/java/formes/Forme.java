package formes;

/**
 * Superclasse des formes g�ographiques.
 *
 * @author Mass'
 *
 */
public abstract class Forme implements Mover, Printer {
  protected String name;


  public Forme(String name) {
    this.name = name;
  }


  @Override
  public abstract void move(int x, int y);

  /**
   * Affiche les coordonn�es de la forme.
   */
  @Override
  public abstract void print();

  public String getName() {
    return name;
  }

}
