/** @author Ahmed Khoumsi */

import java.lang.reflect.Type;

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
     switch (chaine) {
         case "+":
                typeTerminal = TypeTerminal.ADDITION;
             break;
         case "-":
                typeTerminal = TypeTerminal.SOUSTRACTION;
                break;
         case "*":
             typeTerminal = TypeTerminal.MULTIPLICATION;
             break;
         case "/":
             typeTerminal = TypeTerminal.DIVISION;
             break;
         case "(":
             typeTerminal = TypeTerminal.PARENTHÈSE_OUVERTE;
             break;
         case ")":
             typeTerminal = TypeTerminal.PARENTHÈSE_FERMEE;
             break;
         default:
             try {
                 Integer.parseInt(chaine);
                 typeTerminal = TypeTerminal.NOMBRE;
             } catch (Exception e) {
                typeTerminal = TypeTerminal.VARIABLE;
             }
             break;
     }
  }

}
