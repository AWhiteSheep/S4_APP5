/** @author Ahmed Khoumsi */

import java.util.ArrayList;
import java.util.List;

/** Cette classe effectue l'analyse lexicale
 */
public class AnalLex {

// Attributs
//  ...
int Etat;
  int ptrLect;
  String text;
  Terminal terminal;
  String chiffre= "0123456789";
  String MAJ= "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  String MIN= "abcdefghijklmnopqrstuvwxyz";
  String operateur= "*/+-()";
  String underscore= "_";
  public ArrayList<Terminal> UL;
  private ArrayList<Terminal> ULbeforeError;


/** Constructeur pour l'initialisation d'attribut(s)
 */
  public AnalLex(String arg) {  // arguments possibles
    //
    Etat = 0;
    ptrLect = 0;
    this.text = arg.replace(" ", "");
    this.ULbeforeError = new ArrayList<>();
  }

/** resteTerminal() retourne :
      false  si tous les terminaux de l'expression arithmetique ont ete retournes
      true s'il reste encore au moins un terminal qui n'a pas ete retourne 
 */
  public boolean resteTerminal() {
      return this.ptrLect != this.text.length();
  }


  public void printULbeforeError() {

    for (int i = 0; i < ULbeforeError.size(); i++) {
      Terminal terminal1 = ULbeforeError.get(i);
      System.out.println(terminal1.typeTerminal + " " + terminal1.c);
    }
  }
  
/** prochainTerminal() retourne le prochain terminal
      Cette methode est une implementation d'un AEF
 */  
  public Terminal prochainTerminal() {
    String chaineLocale= "";


    while(true) {
      if (ptrLect>= this.text.length()){
        terminal = new Terminal(chaineLocale);


        return terminal;
      }

      //ETAT 0
      if (Etat == 0) {
        //OPERATEURS
        if (operateur.indexOf(text.charAt(ptrLect)) != -1 ) {
          terminal = new Terminal(Character.toString(text.charAt(ptrLect)));
          ptrLect++;
          ULbeforeError.add(terminal);

          return terminal;
        }

        //CHIFFRES
        else if (chiffre.indexOf(text.charAt(ptrLect)) != -1 ) {
          Etat = 3;
          chaineLocale += text.charAt(ptrLect);
          ptrLect++;
        }

        //MAJUSCULES
        else if (MAJ.indexOf(text.charAt(ptrLect)) != -1 ) {
          Etat = 1;
          chaineLocale += text.charAt(ptrLect);
          ptrLect++;
        }

        //AUTRES
        else {
          terminal = new Terminal(chaineLocale + text.charAt(ptrLect));
          ULbeforeError.add(terminal);
          ErreurLex("Lieu:" + ptrLect + "\nCause: '" + text.charAt(ptrLect) + "' nest pas permis\n" +
                  "Doit commencer par MAJ, chiffre ou operateur\n");

          //return terminal;
        }
      }

      //ETAT 1
      else if (Etat == 1) {
        //MIN/MAJ
        if (MAJ.indexOf(text.charAt(ptrLect)) != -1 || MIN.indexOf(text.charAt(ptrLect)) != -1){
          Etat = 1;
          chaineLocale += text.charAt(ptrLect);
          ptrLect++;
        }
        //UNDERSCORE
        else if (underscore.indexOf(text.charAt(ptrLect)) != -1){
          Etat = 2;
          chaineLocale += text.charAt(ptrLect);
          ptrLect++;
        }
        //AUTRE
        else {
          Etat = 0;
          terminal = new Terminal(chaineLocale);
          ULbeforeError.add(terminal);

          return terminal;
        }
      }

      //ETAT 2
      else if (Etat == 2) {
        //MIN/MAJ
        if (MAJ.indexOf(text.charAt(ptrLect)) != -1 || MIN.indexOf(text.charAt(ptrLect)) != -1){
          Etat = 1;
          chaineLocale += text.charAt(ptrLect);
          ptrLect++;
        }
        //UNDERSCORE
        else if (underscore.indexOf(text.charAt(ptrLect)) != -1 ){
          terminal = new Terminal(chaineLocale + text.charAt(ptrLect));

          ULbeforeError.add(terminal);
          ErreurLex("Lieu:" + ptrLect + "\nCause: '" + text.charAt(ptrLect) + "' nest pas permis\n" +
                  "Ne peut avoir 2 underscores de suite.\n");
        }
        //AUTRE
        else {
          ptrLect--;
          terminal = new Terminal(chaineLocale );

          ULbeforeError.add(terminal);
          ErreurLex("Lieu:" + ptrLect + "\nCause: '" + text.charAt(ptrLect) + "' nest pas permis\n" +
                  "Ne peut finir avec un underscore.\n");
        }
      }

      //ETAT 3
      else if (Etat == 3) {

        //CHIFFRE
        if (chiffre.indexOf(text.charAt(ptrLect)) != -1) {
          Etat = 3;
          chaineLocale += text.charAt(ptrLect);
          ptrLect++;
        }

        //AUTRE
        else {
          //ptrLect--;
          Etat = 0;
          terminal = new Terminal(chaineLocale);
          ULbeforeError.add(terminal);
          return terminal;
        }
      } else {
        terminal = new Terminal(chaineLocale + text.charAt(ptrLect));
        ULbeforeError.add(terminal);
        ErreurLex("Lieu:" + ptrLect + "\nCause: '" + text.charAt(ptrLect) + "' nest pas permis\n" + "Autre etat \n");

      }


    }

  }

 
/** ErreurLex() envoie un message d'erreur lexicale
 */ 
  public void ErreurLex(String s) {
    printULbeforeError();
    System.out.println(s);
    System.exit(0);
  }

  public ArrayList<Terminal> doAnalLex() {
    this.ptrLect = 0;
    this.UL = new ArrayList<>();
    Terminal t = null;
    while(this.resteTerminal()){
      t = this.prochainTerminal();
      UL.add(t);
    }
    return UL;
  }

  
  //Methode principale a lancer pour tester l'analyseur lexical
  public static void main(String[] args) {
    String toWrite = "";
    System.out.println("Debut d'analyse lexicale");
    if (args.length == 0){
    args = new String [2];
            args[0] = "ExpArith.txt";
            args[1] = "ResultatLexical.txt";
    }
    Reader r = new Reader(args[0]);

    AnalLex lexical = new AnalLex(r.toString()); // Creation de l'analyseur lexical

    // Execution de l'analyseur lexical
    Terminal t = null;
    while(lexical.resteTerminal()){
      t = lexical.prochainTerminal();
      toWrite +=t.typeTerminal.toString() + " " + t.c + "\n" ;  // toWrite contient le resultat
    }				   //    d'analyse lexicale
    System.out.println(toWrite); 	// Ecriture de toWrite sur la console
    Writer w = new Writer(args[1],toWrite); // Ecriture de toWrite dans fichier args[1]
    System.out.println("Fin d'analyse lexicale");
  }
}
