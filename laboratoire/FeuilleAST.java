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
  public int EvalAST() {
      return Integer.parseInt(this.terminal.c);
  }


 /** Lecture de chaine de caracteres correspondant a la feuille d'AST
  */
  public String LectAST( ) {
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
