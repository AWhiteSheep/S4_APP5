/** @author Ahmed Khoumsi */

/** Classe representant une feuille d'AST
 */
public class NoeudAST extends ElemAST {

  // Attributs
  public Terminal terminal;

  /** Constructeur pour l'initialisation d'attributs
   */
  public NoeudAST(Terminal terminal) { // avec arguments
    //
    this.terminal = terminal;
  }

 
  /** Evaluation de noeud d'AST
   */
  public int EvalAST( ) {
     return 0;
  }


  /** Lecture de noeud d'AST
   */
  public String LectAST( ) {
     return null;
  }

}


