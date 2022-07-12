/** @author Ahmed Khoumsi */

/** Classe representant une feuille d'AST
 */
public class NoeudAST extends ElemAST {

  // Attributs
  public ElemAST elementGauche;
  public ElemAST elementDroite;
  public ElemAST sommet;

  /** Constructeur pour l'initialisation d'attributs
   */
  public NoeudAST(ElemAST elementGauche, ElemAST elementDroite, ElemAST sommet) { // avec arguments
    this.elementGauche = elementGauche;
    this.elementDroite = elementDroite;
    this.sommet = sommet;
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


