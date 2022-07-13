/** @author Ahmed Khoumsi */

/** Classe representant une feuille d'AST
 */
public class FeuilleAST extends ElemAST {

  // Attribut(s)
    Terminal SymboleFeuille;

/**Constructeur pour l'initialisation d'attribut(s)
 */
  public FeuilleAST(Terminal symbole) {  // avec arguments
    //
      this.SymboleFeuille = symbole;
  }


  /** Evaluation de feuille d'AST
   */
  public int EvalAST( ) {
    //
      return 0;
  }


 /** Lecture de chaine de caracteres correspondant a la feuille d'AST
  */
  public String LectAST( ) {
      return this.SymboleFeuille.chaine;
  }

}
