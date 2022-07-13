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
  this.currentTerminal = UL.get(this.PtrLec);
  this.PtrLec++;
  return currentTerminal;
}

public boolean HasMore() {
  return this.PtrLec < UL.size();
}

/** AnalSynt() effectue l'analyse syntaxique et construit l'AST.
 *    Elle retourne une reference sur la racine de l'AST construit
 */
public ElemAST AnalSynt( ) {
  this.PtrLec = 0;
  getNextTerminal();
  return E();
}


// Methode pour chaque symbole non-terminal de la grammaire retenue
// ... 
// ...
public ElemAST E() {
  NoeudAST noeud = null;
  FeuilleAST n1 = (FeuilleAST) L();
  if(currentTerminal == null)
    return n1;
  if(currentTerminal.typeTerminal == TypeTerminal.OPERATEUR)
  {
    Terminal operator = currentTerminal;
    getNextTerminal();
    ElemAST n2 = E();
    noeud = new NoeudAST(n1, n2, new FeuilleAST(operator));
  }
  return noeud;
}
public ElemAST L() {
  if(currentTerminal.typeTerminal == TypeTerminal.NOMBRE) {
    FeuilleAST feuille = new FeuilleAST(currentTerminal);
    if(HasMore())
      getNextTerminal();
    else
      currentTerminal = null;
    return feuille;
  } else {
    ErreurSynt("Erreur ElemAST: n'est pas un UL accepté.");
  }
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

