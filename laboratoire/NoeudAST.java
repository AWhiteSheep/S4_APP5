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
    return ((FeuilleAST)this.sommet).calculer(this.elementGauche.EvalAST(),this.elementDroite.EvalAST());
  }


  /** Lecture de noeud d'AST
   */
  public String PostFix( ) {
     return this.elementGauche.PostFix() + " " + this.elementDroite.PostFix() + " " + ((FeuilleAST)this.sommet).LectAST();
  }
  public String LectAST( ) {
    return "(" + this.elementGauche.LectAST() + this.sommet.LectAST() + this.elementDroite.LectAST() + ")";
  }
}


