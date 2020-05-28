package persistance;

import ui.NonExistentFormeException;

public abstract class Dao<T> {

  /**
   * Ajoute une forme � la base de donn�es.
   *
   * @param t l'objet � ajouter
   * @throws NonExistentFormeException si la forme n'existe pas ou n'est pas dans le groupe.
   */
  public abstract void create(T t) throws NonExistentFormeException;

  /**
   * Modifie une forme dans la base de donn�es.
   *
   * @param t la forme � modifier
   * @throws NonExistentFormeException si la forme n'existe pas ou n'est pas dans le groupe.
   */
  public abstract void update(T t) throws NonExistentFormeException;

  /**
   * Supprime une forme dans la base de donn�es.
   *
   * @param t la forme � supprimer.
   * @throws NonExistentFormeException si la forme n'existe pas ou n'est pas dans le groupe.
   */
  public abstract void delete(T t) throws NonExistentFormeException;

  /**
   * R�cup�re une forme de la base donn�es.
   *
   * @param nom de la forme � r�cup�rer.
   * @return la forme demand�e
   * @throws NonExistentFormeException si la forme n'existe pas ou n'est pas dans le groupe.
   */
  public abstract T read(String nom) throws NonExistentFormeException;
}
