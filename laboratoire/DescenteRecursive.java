/** @author Ahmed Khoumsi */

import java.util.ArrayList;

/** Cette classe effectue l'analyse syntaxique
 */
public class DescenteRecursive {

  // Attributs
  public ArrayList<Terminal> UL;
  public Terminal currentTerminal;
  public int PtrLec;

/** Constructeur de DescenteRecursive :
      - recoit en argument le nom du fichier contenant l'expression a analyser
      - pour l'initalisation d'attribut(s)
 */
public DescenteRecursive(String in) {
  Reader r = new Reader(in);
  AnalLex lexical = new AnalLex(r.toString()); // Creation de l'analyseur lexical
  UL = lexical.doAnalLex();
}

public Terminal getNextTerminal() {
  Terminal next = UL.get(this.PtrLec);
  this.PtrLec++;
  return next;
}

/** AnalSynt() effectue l'analyse syntaxique et construit l'AST.
 *    Elle retourne une reference sur la racine de l'AST construit
 */
public ElemAST AnalSynt( ) {
  this.PtrLec = 0;
  this.currentTerminal = getNextTerminal();
}


// Methode pour chaque symbole non-terminal de la grammaire retenue
// ... 
// ...
public ElemAST E() {
  NoeudAST n1 = T();
  return null;
}
public ElemAST T() {
  return null;
}


/** ErreurSynt() envoie un message d'erreur syntaxique
 */
public void ErreurSynt(String s)
{
    //
}



  //Methode principale a lancer pour tester l'analyseur syntaxique 
  public static void main(String[] args) {
    String toWriteLect = "";
    String toWriteEval = "";

    System.out.println("Debut d'analyse syntaxique");
    if (args.length == 0){
      args = new String [2];
      args[0] = "ExpArith.txt";
      args[1] = "ResultatSyntaxique.txt";
    }
    DescenteRecursive dr = new DescenteRecursive(args[0]);
    try {
      ElemAST RacineAST = dr.AnalSynt();
      toWriteLect += "Lecture de l'AST trouve : " + RacineAST.LectAST() + "\n";
      System.out.println(toWriteLect);
      toWriteEval += "Evaluation de l'AST trouve : " + RacineAST.EvalAST() + "\n";
      System.out.println(toWriteEval);
      Writer w = new Writer(args[1],toWriteLect+toWriteEval); // Ecriture de toWrite 
                                                              // dans fichier args[1]
    } catch (Exception e) {
      System.out.println(e);
      e.printStackTrace();
      System.exit(51);
    }
    System.out.println("Analyse syntaxique terminee");
  }

}

