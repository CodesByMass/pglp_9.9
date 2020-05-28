package formes;

/**
 * Superclasse des formes géométriques.
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
   * Affiche les coordonnées de la forme.
   */
  @Override
  public abstract void print();

  public String getName() {
    return name;
  }

}
