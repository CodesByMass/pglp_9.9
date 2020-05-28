package ui;

import java.util.ArrayList;
import java.util.Scanner;
import commands.AddForme;
import commands.Create;
import commands.CreateComposite;
import commands.Delete;
import commands.Move;
import commands.Print;
import formes.Forme;
import persistance.DaoFactory;

public class DrawingTUI {

  private Scanner sc;

  public DrawingTUI() {
    sc = new Scanner(System.in);
    System.out.println("Choisissez ce que vous voulez faire :");
    System.out.println("Pour ajouter une forme");
    System.out.println("-> name = typeForme((x, y), param1, param2...)");
    System.out.println("Pour créer un groupe de formes");
    System.out
        .println("On ne peut créer un groupe qu'avec des formes déjà existantes, ou sans formes");
    System.out.println("-> NomGroupe = NomForme1 + NomForme2  ");
    System.out.println("Pour ajouter un élément à un groupe ");
    System.out.println("-> Ajout NomGroupe + NomForme ");
    System.out.println("Pour déplacer une forme ou un groupe de formes");
    System.out.println("-> Move(Nom, (x, y))");
    System.out.println("Pour afficher des informations sur une forme ou un groupe");
    System.out.println("-> Print Nom");
    System.out.println("Pour Supprimer une forme ou un groupe");
    System.out.println("-> Delete Nom");
    System.out.println("Pour quitter l'application");
    System.out.println("-> quit");
  }


  public boolean nextCommand() throws NonExistentFormeException {
    String line = "";
    if (sc.hasNextLine()) {
      line = sc.nextLine();
      if (line.contains("quit")) {
        return false;
      }
    }

    if (line.contains("=")) {
      if (line.contains("(")) {
        create(line);
      } else {
        createComposite(line);
      }
    } else if (line.contains("Move")) {
      move(line);
    } else if (line.contains("Print")) {
      print(line);
    } else if (line.contains("Ajout")) {
      add(line);
    } else if (line.contains("Delete")) {
      delete(line);
    } else
      System.out.println("Commande inconnue, respectez la syntaxe");

    return true;

  }



  public void create(String line) {
    String[] lineContent = line.split("=");
    String name = lineContent[0].trim();

    String[] infos = lineContent[1].split("((");
    String forme = infos[0].trim();

    String[] tab = infos[1].split(",");

    int x = Integer.parseInt(tab[0].trim());
    int y = Integer.parseInt(tab[1].split(")")[0].trim());

    ArrayList<Integer> parameters = new ArrayList<Integer>();

    // Retirer la dernière parenthèse
    tab[tab.length - 1] = tab[tab.length - 1].split(")")[0].trim();

    for (int i = 2; i < tab.length; i++) {
      parameters.add(Integer.parseInt(tab[i].trim()));
    }


    Create create = new Create(forme.toLowerCase(), name, x, y, parameters);

    create.execute();


  }

  public void createComposite(String line) throws NonExistentFormeException {

    String[] lineContent = line.split("=");

    String name = lineContent[0].trim();
    String[] parameters = lineContent[1].split("+");

    ArrayList<String> liste = new ArrayList<>();
    for (int i = 0; i < parameters.length; i++) {
      liste.add(parameters[i].trim());
    }

    new CreateComposite(name, liste).execute();

  }

  private void add(String line) {
    String[] lineContent = line.split("Ajout");
    String nomGroupe = lineContent[1].split("+")[0].trim();
    String nomForme = lineContent[1].split("+")[1].trim();

    new AddForme(nomGroupe, nomForme);

  }

  public void move(String line) throws NonExistentFormeException {
    String[] lineContent = line.split("(");
    String name = lineContent[1].trim().split(",", 2)[0].trim();

    String position = lineContent[2].split(")")[0];

    int x = Integer.parseInt(position.split(",")[0].trim());
    int y = Integer.parseInt(position.split(",")[1].trim());

    // Récupérer le type de forme
    String typeForme = DaoFactory.getFormeDao().read(name).getClass().getSimpleName();

    new Move(name, typeForme, x, y).execute();

  }

  public void print(String line) throws NonExistentFormeException {

    String name = line.split("Print")[1].trim();

    Forme forme = DaoFactory.getFormeDao().read(name);

    new Print(forme);

  }

  private void delete(String line) throws NonExistentFormeException {
    String name = line.split("Delete")[1].trim();

    Forme forme = DaoFactory.getFormeDao().read(name);

    new Delete(forme);

  }

}


