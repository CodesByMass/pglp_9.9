package formes;

import java.util.ArrayList;

/**
 * La classe composite des formes.
 *
 * @author Mass'
 *
 */
public class CompositeFormes extends Forme implements Mover, Printer {
  /**
   * Nom du groupe.
   */
  private String name;
  /**
   * Liste d'enfants du composite.
   */
  private ArrayList<Forme> childsFormes = new ArrayList<Forme>();

  /**
   * Constructeur avec uniquement le nom. Peut �tre utilis� si il n y a pas encore de liste de
   * formes.
   *
   * @param name du groupe.
   */
  public CompositeFormes(String name) {
    super(name);
    this.childsFormes.clear();
  }

  public CompositeFormes(String name, ArrayList<Forme> childsFormes) {
    super(name);
    this.childsFormes = childsFormes;
  }



  public ArrayList<Forme> getChildsFormes() {
    return childsFormes;
  }



  /**
   * Ajoute une forme � la liste d'enfants.
   *
   * @param f la forme � ajouter.
   */
  public void add(Forme f) {
    if (f == null || childsFormes.contains(f)) {
      throw new IllegalArgumentException("La forme n'a pas pu �tre ajout�e");
    } else {
      this.childsFormes.add(f);
    }
  }

  /**
   * Supprime une forme de la liste.
   *
   * @param f la forme � supprimer.
   */
  public void delete(Forme f) {
    if (!childsFormes.contains(f)) {
      throw new IllegalArgumentException("La forme ne fait pas partie de cette liste");
    } else {
      this.childsFormes.remove(f);
    }
  }

  /**
   * D�placer plusieurs formes � la fois.
   *
   * @see Mover#move(int, int)
   */
  @Override
  public void move(int x, int y) {
    for (Forme f : childsFormes) {
      f.move(x, y);
    }
  }

  /**
   * Afficher toute la liste du groupe.
   *
   * @see Printer#print()
   */
  @Override
  public void print() {
    System.out
        .println("Le nom du groupe est : " + this.getName() + System.getProperty("line.separator"));
    for (Forme f : childsFormes) {
      f.print();
    }
  }
}
