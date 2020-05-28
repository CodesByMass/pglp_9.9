package ui;

import java.sql.SQLException;
import persistance.DbConn;

/**
 * Classe principale qui contient la méthode run qui lance l'application.
 *
 * @see DrawingTui#DrawingTui()
 *
 * @author Mass'
 *
 */
public enum DrawingApp {
  APP;



  public void run() throws NonExistentFormeException, SQLException {
    /**
     * Lance la base de données
     */
    new DbConn().createTables();
    System.out.println("Bienvenue sur le logiciel de dessin :) ");
    DrawingTui userInterface = new DrawingTui();
    while (userInterface.nextCommand()) {

    }
    System.out.println("Au revoir, à la prochaine ;) ");

  }

  public static void main(String[] args) throws Exception {
    APP.run();

  }
}
