/** @author Ahmed Khoumsi */

import java.util.ArrayList;

/** Cette classe effectue l'analyse lexicale
 */
public class AnalLex {

// Attributs
  int Etat;
  int ptrLect;
  String text;
  Terminal terminal;
  //ArrayList<Terminal> UL;
  String chiffre= "0123456789";
  String MAJ= "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  String MIN= "abcdefghijklmnopqrstuvwxyz";
  String operateur= "*/+-()";
  String underscore= "_";


/** Constructeur pour l'initialisation d'attribut(s)
 */
  public AnalLex(String args) {  // arguments possibles
    //
    Etat = 0;
    ptrLect = 0;
    text = args;

  }


/** resteTerminal() retourne :
      false  si tous les terminaux de l'expression arithmetique ont ete retournes
      true s'il reste encore au moins un terminal qui n'a pas ete retourne 
 */
  public boolean resteTerminal( ) {
    return this.ptrLect != this.text.length();
  }
  
  
/** prochainTerminal() retourne le prochain terminal
      Cette methode est une implementation d'un AEF
 */  
  public Terminal prochainTerminal( ) {
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
          //UL.add(terminal);

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
          ErreurLex("Erreur Etat 0: commence avec autre chose que MAJ, chiffre ou operateur");
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
          //UL.add(terminal);
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
        //AUTRE
        else {
          ErreurLex("Erreur Etat 2: 2 underscores ou fini avec underscore");
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
          //UL.add(terminal);
          return terminal;
        }
      } else {
        ErreurLex("Autre etat");
      }
    }
  }

 
/** ErreurLex() envoie un message d'erreur lexicale
 */ 
  public void ErreurLex(String s) {	
     //
    System.out.println(s);
    System.exit(0);
  }

  /*public ArrayList<Terminal> GetUL(){
    return UL;
  }*/
  
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
      toWrite +=t.chaine + "\n" ;  // toWrite contient le resultat
    }				   //    d'analyse lexicale
    System.out.println(toWrite); 	// Ecriture de toWrite sur la console
    Writer w = new Writer(args[1],toWrite); // Ecriture de toWrite dans fichier args[1]
    System.out.println("Fin d'analyse lexicale");
  }
}
