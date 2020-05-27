package persistance;

public abstract class Dao<T> {

  /**
   * Ajoute une forme à la base de données.
   *
   * @param t l'objet à ajouter
   * @throws Exception si une erreur SQL se produit.
   */
  abstract void create(T t) throws Exception;

  /**
   * Modifie une forme dans la base de données.
   * 
   * @param t la forme à modifier
   * @throws Exception si une erreur SQL se produit.
   */
  abstract void update(T t) throws Exception;

  /**
   * Supprime une forme dans la base de données.
   * 
   * @param t la forme à supprimer.
   * @throws Exception si une erreur SQL se produit.
   */
  abstract void delete(T t) throws Exception;

  /**
   * Récupère une forme de la base données.
   * 
   * @param nom de la forme à récupérer.
   * @return la forme demandée
   * @throws Exception si la forme n'existe pas ou n'est pas dans le groupe.
   */
  abstract T read(String nom) throws Exception;
}
