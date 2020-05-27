package persistance;

public abstract class Dao<T> {

  /**
   * Ajoute une forme � la base de donn�es.
   *
   * @param t l'objet � ajouter
   * @throws Exception si une erreur SQL se produit.
   */
  abstract void create(T t) throws Exception;

  /**
   * Modifie une forme dans la base de donn�es.
   * 
   * @param t la forme � modifier
   * @throws Exception si une erreur SQL se produit.
   */
  abstract void update(T t) throws Exception;

  /**
   * Supprime une forme dans la base de donn�es.
   * 
   * @param t la forme � supprimer.
   * @throws Exception si une erreur SQL se produit.
   */
  abstract void delete(T t) throws Exception;

  /**
   * R�cup�re une forme de la base donn�es.
   * 
   * @param nom de la forme � r�cup�rer.
   * @return la forme demand�e
   * @throws Exception si la forme n'existe pas ou n'est pas dans le groupe.
   */
  abstract T read(String nom) throws Exception;
}
