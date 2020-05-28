package persistance;

import ui.NonExistentFormeException;

public abstract class Dao<T> {

  /**
   * Ajoute une forme à la base de données.
   *
   * @param t l'objet à ajouter
   * @throws NonExistentFormeException
   * @throws NonExistentFormeException si la forme n'existe pas ou n'est pas dans le groupe.
   */
  public abstract void create(T t) throws NonExistentFormeException;

  /**
   * Modifie une forme dans la base de données.
   *
   * @param t la forme à modifier
   * @throws NonExistentFormeException si la forme n'existe pas ou n'est pas dans le groupe.
   */
  public abstract void update(T t) throws NonExistentFormeException;

  /**
   * Supprime une forme dans la base de données.
   *
   * @param t la forme à supprimer.
   * @throws NonExistentFormeException si la forme n'existe pas ou n'est pas dans le groupe.
   */
  public abstract void delete(T t) throws NonExistentFormeException;

  /**
   * Récupère une forme de la base données.
   *
   * @param nom de la forme à récupérer.
   * @return la forme demandée
   * @throws NonExistentFormeException si la forme n'existe pas ou n'est pas dans le groupe.
   */
  public abstract T read(String nom) throws NonExistentFormeException;
}
