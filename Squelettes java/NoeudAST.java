/** @author Ahmed Khoumsi */

/** Classe representant une feuille d'AST
 */
public class NoeudAST extends ElemAST {

  // Attributs
  Terminal operateurNoeud;
  ElemAST n1, n2;

  /** Constructeur pour l'initialisation d'attributs
   */
  public NoeudAST(Terminal operateur, ElemAST n1, ElemAST n2) { // avec arguments
    //
    this.operateurNoeud = operateur;
    this.n1 = n1;
    this.n2 = n2;
  }

 
  /** Evaluation de noeud d'AST
   */
  public int EvalAST( ) {
     //
    return 0;
  }


  /** Lecture de noeud d'AST
   */
  public String LectAST( ) {
    n1.LectAST();
    n2.LectAST();
    return this.operateurNoeud.chaine;
  }

}


