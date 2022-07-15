/** @author Ahmed Khoumsi */

/** Classe representant une feuille d'AST
 */
public class FeuilleAST extends ElemAST {

  // Attribut(s)
    Terminal terminal;

/**Constructeur pour l'initialisation d'attribut(s)
 */
  public FeuilleAST(Terminal terminal) {  // avec arguments
    this.terminal = terminal;
  }


  /** Evaluation de feuille d'AST
   */
  public int EvalAST() throws Exception {
      int evaluation = 0;
      try {
        evaluation= Integer.parseInt(this.terminal.c);
        }
      catch (Exception e) {
          throw new Exception("Ne peux pas parser la variable en integer pour l'evaluation: " + this.terminal.c);
      }
      return evaluation;
  }


 /** Lecture de chaine de caracteres correspondant a la feuille d'AST
  */
  public String LectAST( ) {
    return this.terminal.c;
  }
    public String PostFix( ) {
        return this.terminal.c;
    }


    public int calculer(int operandGauche, int operandDroite) {
        switch (this.terminal.typeTerminal) {
            case ADDITION:
                return operandGauche + operandDroite;
            case SOUSTRACTION:
                return operandGauche - operandDroite;
            case MULTIPLICATION:
                return operandGauche * operandDroite;
            case DIVISION:
                return operandGauche / operandDroite;
            default:
                // WTF
                return 0;
        }
    }
}
