//CALCULATOR TEST

public class CalculatorTest extends Calculator {
    
    /** 
     * @param args
     */
    public static void main(String[] args) {

        //testing the convertopostfix method
        String infix = "a*b/(c-a)+d*e";
        System.out.println("The infix expression is \n" + infix);
        String postfix = convertToPostFix(infix);
        System.out.println("The postfix expression is \n" + postfix);

        //testing evaluate postfix method
        System.out.println("Let a = 2, b = 3, c = 4, d = 5, e = 6");
        System.out.println("Then " + infix + " = " + evaluatePostFix(postfix));
    }
}
