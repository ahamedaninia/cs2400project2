//CALCULATOR

public class Calculator {
    
    
    /** 
     * @param infix  the infix expression being transformed into postfix
     * @return String  the postfix expression from the infix
     */
    //Converts an infix expression to an equivalent postfix expression
    public static String convertToPostFix(String infix) {
        LinkedStack<Character> operatorStack = new LinkedStack<Character>();
        String postfix = "";
        char topOperator;

        while(infix.length() > 0) {
            //get the first character in the expression
            char nextCharacter = infix.charAt(0);

            switch(nextCharacter) {
                case 'a' : case 'b' : case 'c' : //nextCharacter is a variable
                case 'd' : case'e' :  case 'f' : 
                case 'g' : case 'h' : case 'i' : 
                case 'j' : case 'k' : case 'l' : 
                case 'm' : case 'n' : case 'o' : 
                case 'p' : case 'q' : case 'r' : 
                case 's' : case 't' : case 'u' : 
                case 'v' : case 'w' : case 'x' : 
                case 'y' : case 'z' :
                    postfix += nextCharacter;
                    break;
                case '^' : //nextCharacter is an exponent (highest precedence)
                    operatorStack.push(nextCharacter);
                    break;
                case '+' : case '-' : case '*' : case '/' : //nextCharacter is an operator
                    while(!operatorStack.isEmpty() && (checkPrecedence(nextCharacter) <=
                    checkPrecedence(operatorStack.peek()))) {
                        postfix += operatorStack.peek();
                        operatorStack.pop();
                    }
                    operatorStack.push(nextCharacter);
                    break;
                case '(' : //nextChar is a parantheses (opening)
                    operatorStack.push(nextCharacter);
                    break;
                case ')' : //stack is not empty if infix exp is valid
                    topOperator = operatorStack.pop();
                    while (topOperator != '(') {
                        postfix += topOperator;
                        topOperator = operatorStack.pop();
                    }
                    break;
                default: break; //ignore unexpected characters
                
            }
            infix = infix.substring(1);
        }

        while (!operatorStack.isEmpty()) {
            topOperator = operatorStack.pop();
            postfix += topOperator;
        }
        return postfix;
    }

    
    /** 
     * @param operator  the operator from a given expression
     * @return int  the precedence of the operator
     */
    //a method that checks the precedence of an operator
    // +, - has a precedence of 1
    // *, / has a precedence of 2
    public static int checkPrecedence(char operator) {
        int precedence = 0;
        if (operator == '+' || operator == '-') {
            precedence = 1;
        } else if (operator == '*' || operator == '/') {
            precedence = 2;
        } 
        return precedence;
    }




    
    /** 
     * @param postfix  the postfix expression (from above)
     * @return int  the result of the postfix and manually assigned values for the variables
     */
    public static int evaluatePostFix(String postfix) {
        //evaluates a postfix expression
        StackInterface<Integer> valueStack = new ResizableArrayStack<Integer>();

        while(postfix.length() > 0) {
            char nextCharacter = postfix.trim().charAt(0);
            switch (nextCharacter) {
                case 'a' :                      //nextCharacter is a variable
                    valueStack.push(2);  //manual input of variable values
                    break; 
                case 'b' :
                    valueStack.push(3); 
                    break;  
                case 'c' :
                    valueStack.push(4);
                    break;   
                case 'd' : 
                    valueStack.push(5); 
                    break; 
                case 'e' : 
                    valueStack.push(6);
                    break;
                case '+' : case '-' : case '*' : case '/' : case '^' :
                    int operandTwo = valueStack.pop();
                    int operandOne = valueStack.pop();
                    int result = simpleEval(operandOne, operandTwo, nextCharacter);
                    valueStack.push(result);
                    break;
                default: break; //ignore unexpected characters
            }
            postfix = postfix.substring(1);
        }
        return valueStack.peek();

    }

    
    /** 
     * @param opOne  the first operand
     * @param opTwo  the second operand
     * @param operator  the operator for opOne and OpTwo
     * @return int  the result of the operation
     */
    //evalute a simple expression
    public static int simpleEval(int opOne, int opTwo, char operator) {
        int result = 0;

        switch(operator) {
            case '+' :
                result = opTwo + opOne;
                break;
            case '-' :
                result = opOne - opTwo;
                break;
            case '*' :
                result = opTwo * opOne;
                break;
            case '/' :
                result = opOne / opTwo;
                break;
            case '^' :
                result = (int) Math.pow(opTwo, opOne);
        }

        return result;
    }
}
