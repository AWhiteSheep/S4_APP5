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
    return ((FeuilleAST)this.sommet).calculer(this.elementDroite.EvalAST(), this.elementGauche.EvalAST());
  }


  /** Lecture de noeud d'AST
   */
  public String LectAST( ) {
     return this.elementGauche.LectAST() + this.sommet.LectAST() + this.elementDroite.LectAST();
  }

}


