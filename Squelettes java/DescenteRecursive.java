/** @author Ahmed Khoumsi */

import java.util.ArrayList;

/** Cette classe effectue l'analyse syntaxique
 */
public class DescenteRecursive {

  // Attributs
  int arrayptr;
  ArrayList<Terminal> ULsynth;

  Terminal currentTerminal;

  String chiffre= "0123456789";
  String variable= "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz_";
  String operateur= "*/+-()";

/** Constructeur de DescenteRecursive :
      - recoit en argument le nom du fichier contenant l'expression a analyser
      - pour l'initalisation d'attribut(s)
 */
public DescenteRecursive(String in) {
    //
  arrayptr = 0;
  Reader r = new Reader(in);
  AnalLex lexical = new AnalLex(r.toString()); // Creation de l'analyseur lexical

  // Execution de l'analyseur lexical
  Terminal t = null;
  this.ULsynth = new ArrayList<>();
  while(lexical.resteTerminal()){
    t = lexical.prochainTerminal();
    ULsynth.add(t);  // contient la liste des ULs
  }
}

private boolean isNotDone() {
  return arrayptr < ULsynth.size();
}

private Terminal nextTerminal() {
  this.currentTerminal = ULsynth.get(this.arrayptr);
  this.arrayptr++;
  return currentTerminal;
}

/** AnalSynt() effectue l'analyse syntaxique et construit l'AST.
 *    Elle retourne une reference sur la racine de l'AST construit
 */
public ElemAST AnalSynt( ) {
  arrayptr = 0;
  nextTerminal();
  return E();
}


// Methode pour chaque symbole non-terminal de la grammaire retenue
// ...
  public FeuilleAST T() {
    if(chiffre.contains(currentTerminal.chaine) || variable.contains(currentTerminal.chaine)) {
      FeuilleAST feuille = new FeuilleAST(currentTerminal);
      if(isNotDone())
        nextTerminal();
      else
        currentTerminal = null;
      return feuille;
    } else {
      ErreurSynt("Erreur ElemAST: n'est pas un UL accepte.");
    }
    return null;
  }
  public ElemAST E() {
    NoeudAST noeud = null;
    FeuilleAST n1 = (FeuilleAST) T();
    if(currentTerminal == null)
      return n1;
    if(operateur.contains(currentTerminal.chaine))
    {
      Terminal operator = currentTerminal;
      nextTerminal();
      ElemAST n2 = E();
      noeud = new NoeudAST(operator, n1, n2);
    }
    return noeud;
  }


/** ErreurSynt() envoie un message d'erreur syntaxique
 */
public void ErreurSynt(String s)
{
  System.out.println(s);
  System.exit(0);
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

