/** @author Ahmed Khoumsi */

/** Cette classe identifie les terminaux reconnus et retournes par
 *  l'analyseur lexical
 */
public class Terminal {


// Constantes et attributs
//  ....
    public String[] operators = {"+", "-", "*", "/"};
    public String[] operatorPriorite = {"(", ")"};
  public String c;
  public TypeTerminal typeTerminal;


/** Un ou deux constructeurs (ou plus, si vous voulez)
  *   pour l'initalisation d'attributs 
 */	
  public Terminal(String chaine) {   // arguments possibles
     this.c = chaine;
     if(this.c.length() == 1) {
         for (int i = 0; i < operators.length; i++) {
             if(chaine.equals(operators[i])) {
                 typeTerminal = TypeTerminal.OPERATEUR;
             }
         }
         if (typeTerminal == null) {
             for (int i = 0; i < operatorPriorite.length; i++) {
                 if (chaine.equals(operatorPriorite[i])) {
                     typeTerminal = TypeTerminal.PRIORITE;
                 }
             }
         }
     } else {
         typeTerminal = TypeTerminal.NOMBRE;
     }
  }

}
