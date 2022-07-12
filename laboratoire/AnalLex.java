/** @author Ahmed Khoumsi */

import java.util.ArrayList;
import java.util.List;

/** Cette classe effectue l'analyse lexicale
 */
public class AnalLex {

// Attributs
//  ...
  public int Etat;
  public int PtrLect;
  public String text;
  public ArrayList<Terminal> UL;


/** Constructeur pour l'initialisation d'attribut(s)
 */
  public AnalLex(String arg) {  // arguments possibles
    //
    this.text = arg;
  }


/** resteTerminal() retourne :
      false  si tous les terminaux de l'expression arithmetique ont ete retournes
      true s'il reste encore au moins un terminal qui n'a pas ete retourne 
 */
  public boolean resteTerminal() {
      return this.PtrLect != this.text.length();
  }
  
  
/** prochainTerminal() retourne le prochain terminal
      Cette methode est une implementation d'un AEF
 */  
  public Terminal prochainTerminal() {
    String terminalString = "";
    this.Etat = 0;
    while(true) {
      if(this.PtrLect >= this.text.length())
        return new Terminal(terminalString);
      char currentChar = this.text.charAt(this.PtrLect);
      switch (this.Etat) {
        case 0:
          if(currentChar == '+') {
            this.PtrLect++;
            terminalString += currentChar;
            return new Terminal(terminalString);
          } else if(currentChar == 'a' || currentChar == '1' || currentChar == '0') {
            terminalString += currentChar;
            this.Etat = 1;
            this.PtrLect++;
          } else {
            ErreurLex("n'est pas dans les char accepté");
            return null;
          }
          break;
        case 1:
          if(currentChar == 'a' || currentChar == '1' || currentChar == '0') {
            terminalString += currentChar;
            this.PtrLect++;
          } else {
            return new Terminal(terminalString);
          }
          break;
        default:
          ErreurLex("wtf");
          return null;
      }
    }
  }

 
/** ErreurLex() envoie un message d'erreur lexicale
 */ 
  public void ErreurLex(String s) {
    System.out.println(s);
  }

  public ArrayList<Terminal> doAnalLex() {
    this.PtrLect = 0;
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
      toWrite +=t.c + "\n" ;  // toWrite contient le resultat
    }				   //    d'analyse lexicale
    System.out.println(toWrite); 	// Ecriture de toWrite sur la console
    Writer w = new Writer(args[1],toWrite); // Ecriture de toWrite dans fichier args[1]
    System.out.println("Fin d'analyse lexicale");
  }
}
