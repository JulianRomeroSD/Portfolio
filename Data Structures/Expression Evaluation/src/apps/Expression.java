package apps;

import java.io.*;
import java.util.*;
import java.util.regex.*;

import structures.Stack;

public class Expression {

	/**
	 * Expression to be evaluated
	 */
	String expr;                
    
	/**
	 * Scalar symbols in the expression 
	 */
	ArrayList<ScalarSymbol> scalars;   
	
	/**
	 * Array symbols in the expression
	 */
	ArrayList<ArraySymbol> arrays;
    
    /**
     * String containing all delimiters (characters other than variables and constants), 
     * to be used with StringTokenizer
     */
    public static final String delims = " \t*+-/()[]";
    
    /**
     * Initializes this Expression object with an input expression. Sets all other
     * fields to null.
     * 
     * @param expr Expression
     */
    public Expression(String expr) {
        this.expr = expr;
    }

    /**
     * Populates the scalars and arrays lists with symbols for scalar and array
     * variables in the expression. For every variable, a SINGLE symbol is created and stored,
     * even if it appears more than once in the expression.
     * At this time, values for all variables are set to
     * zero - they will be loaded from a file in the loadSymbolValues method.
     */
    public void buildSymbols() {
    		/** COMPLETE THIS METHOD **/
    	scalars = new ArrayList<ScalarSymbol>();
    	arrays = new ArrayList<ArraySymbol>();
    	
    	StringTokenizer variableTokens = new StringTokenizer(this.expr,delims);
    	String token;
    	String expression = expr;
    	
    	//iterate through all tokens
    	while (variableTokens.hasMoreTokens()) {
    		
    		//Stores Token
    		token = variableTokens.nextToken();
    		
    		//checks if the first index is a character
    		if(Character.isLetter(token.charAt(0)) == true) {
    			
    			//gets index of token in expression and size of token
    			int index = expression.indexOf(token);
    			int size = token.length();
    			
    			//checks bracket containing expressions
    			if(expression.contains("[") == true) {
    				
    				//checks if token has bracket next to it
    				if(expression.charAt(index+size) == '[') {
    					
    					//makes sure to not over count same token
    					ArraySymbol tempArray = new ArraySymbol(token);
    					if (arrays.contains(tempArray)){
    						continue;
    					}

    					arrays.add(new ArraySymbol(token));
    				
    				}
    				//token has no bracket so scalar
    				else {
    					ScalarSymbol tempScalarBrackets = new ScalarSymbol(token);
    					if (scalars.contains(tempScalarBrackets)){
    						continue;
    					} 			
    					scalars.add(new ScalarSymbol(token));
    				}
    				
    			}
    			//checks non bracket expressions avoids null pointer exception 
    			else{
    				
    				ScalarSymbol tempScalarNoBrackets = new ScalarSymbol(token);
					if (scalars.contains(tempScalarNoBrackets)){
						continue;
					} 	
    				scalars.add(new ScalarSymbol(token));
    			}
    		
    		//shortens expression to not count an already read token
    		
    		expression = expression.substring(index+size);
    		}
    		
    	}
    	printScalars();
    	printArrays();
    		}
    
    
    /**
     * Loads values for symbols in the expression
     * 
     * @param sc Scanner for values input
     * @throws IOException If there is a problem with the input 
     */
    public void loadSymbolValues(Scanner sc) 
    throws IOException {
        while (sc.hasNextLine()) {
            StringTokenizer st = new StringTokenizer(sc.nextLine().trim());
            int numTokens = st.countTokens();
            String sym = st.nextToken();
            ScalarSymbol ssymbol = new ScalarSymbol(sym);
            ArraySymbol asymbol = new ArraySymbol(sym);
            int ssi = scalars.indexOf(ssymbol);
            int asi = arrays.indexOf(asymbol);
            if (ssi == -1 && asi == -1) {
            	continue;
            }
            int num = Integer.parseInt(st.nextToken());
            if (numTokens == 2) { // scalar symbol
                scalars.get(ssi).value = num;
            } else { // array symbol
            	asymbol = arrays.get(asi);
            	asymbol.values = new int[num];
                // following are (index,val) pairs
                while (st.hasMoreTokens()) {
                    String tok = st.nextToken();
                    StringTokenizer stt = new StringTokenizer(tok," (,)");
                    int index = Integer.parseInt(stt.nextToken());
                    int val = Integer.parseInt(stt.nextToken());
                    asymbol.values[index] = val;              
                }
            }
        }
    }
    
    
    /**
     * Evaluates the expression, using RECURSION to evaluate subexpressions and to evaluate array 
     * subscript expressions.
     * 
     * @return Result of evaluation
     */
    private String eval(String expr){
    	
    	if(expr.contains("(") == false) {
    		System.out.println("entered final: " + expr);
    		Expression x = new Expression(expr);
    		
    		expr = Float.toString(x.evaluate());
    		return expr;
    		
    	}
    	//has parenthesis
    	else {
    	String substring = "";
    	System.out.println("entered parenthesis " + expr);
    	
    	//has nested paranthesis
    	if(expr.charAt(expr.indexOf('(')+1) == '(' || expr.charAt(expr.lastIndexOf(")")-1) == ')') {
    		
    		substring = expr.substring(expr.indexOf("(") + 1, expr.lastIndexOf(")"));
    		System.out.println("nested substring:  " + substring );
    		
    		//paranthesis at end
    		if(expr.indexOf(")") + 1 == expr.length()) {
        		expr = expr.substring(0,expr.indexOf("("));
        		System.out.println("paranthesis at end left over expr: " + expr);
        		
        		if(substring.contains("(")==false) {
            		
            		Expression x = new Expression(substring);
            		substring = Float.toString(x.evaluate());
            		expr = expr + substring;
            	}
        		else{
        			
        			expr = expr + substring;
        			System.out.println("new expr contains  " + expr);
        		}
        		
        	}
    		//paranthesis not at end
        	else if(expr.indexOf(")") + 1 != expr.length()) {
        		expr = expr.substring(0, expr.indexOf("("));
        		System.out.println("paranthesis not at end: " + expr);
        		if(substring.contains("(")==false) {
            		
            		Expression x = new Expression(substring);
            		substring = Float.toString(x.evaluate());
            		expr = substring + expr;
            		System.out.println("expression after: "+expr);
            	}
        		else{
        			
        			expr = substring + expr;
        			System.out.println("new expr contains  " + expr);
        		}
        	}
    	}
    	//does not have nested paranthesis
    	else if(expr.charAt(expr.indexOf('(')+1) != '(') {
    		
    		substring = expr.substring(expr.indexOf("(") + 1, expr.indexOf(")"));
    		System.out.println("not nested substring: " + substring);
    		
        	if(expr.indexOf(")") + 1 == expr.length()) {
        		expr = expr.substring(0,expr.indexOf("("));
        		System.out.println("leftover1: " + expr);
        		
        		if(substring.contains("(")==false) {
            		
            		Expression x = new Expression(substring);
            		substring = Float.toString(x.evaluate());
            		expr = expr + substring;
            	}
        		else {
        			expr = substring + expr;
        		}
        		
        	}
        	else if(expr.indexOf(")") + 1 != expr.length()) {
        		expr = expr.substring(expr.indexOf(")")+1, expr.length());
        		System.out.println("leftover2: " + expr);
        		if(substring.contains("(")==false) {
            		
            		Expression x = new Expression(substring);
            		substring = Float.toString(x.evaluate());
            		expr = substring + expr;
            	}
        		else {
        		expr = expr + substring;
        		}
        	}
    	}
    	
    	}
    	return eval(expr);
    	}
    
    
private String evalArray(String expr){
		
		System.out.println(expr);
    	
    	if(expr.contains("[") == false) {
    		System.out.println("entered final: " + expr);
    		Expression x = new Expression(expr);
    		
    		expr = Float.toString(x.evaluate());
    		System.out.println("entered final: " + expr);
    		return expr;
    		
    	}
    	//has parenthesis
    	else {
    	String substring = "";
    	System.out.println("entered parenthesis " + expr);
    	
    	//has nested paranthesis
    	if(expr.charAt(expr.indexOf('[')+1) == '[' || expr.charAt(expr.lastIndexOf("]")-1) == ']') {
    		
    		substring = expr.substring(expr.indexOf("[") + 1, expr.lastIndexOf("]"));
    		System.out.println("nested substring:  " + substring );
    		
    		//paranthesis at end
    		if(expr.indexOf("]") + 1 == expr.length()) {
        		expr = expr.substring(0,expr.indexOf("["));
        		System.out.println("paranthesis at end left over expr: " + expr);
        		
        		if(substring.contains("[")==false) {
            		
            		Expression x = new Expression(substring);
            		substring = Float.toString(x.evaluate());
            		expr = expr + substring;
            	}
        		else{
        			
        			expr = expr + substring;
        			System.out.println("new expr contains  " + expr);
        		}
        		
        	}
    		//paranthesis not at end
        	else if(expr.indexOf("]") + 1 != expr.length()) {
        		expr = expr.substring(0, expr.indexOf("["));
        		System.out.println("paranthesis not at end: " + expr);
        		if(substring.contains("(")==false) {
            		
            		Expression x = new Expression(substring);
            		substring = Float.toString(x.evaluate());
            		expr = substring + expr;
            		System.out.println("expression after: "+expr);
            	}
        		else{
        			
        			expr = substring + expr;
        			System.out.println("new expr contains  " + expr);
        		}
        	}
    	}
    	//does not have nested paranthesis
    	else if(expr.charAt(expr.indexOf('[')+1) != '[') {
    		
    		substring = expr.substring(expr.indexOf("[") + 1, expr.indexOf("]"));
    		System.out.println("not nested substring: " + substring);
    		
        	if(expr.indexOf("]") + 1 == expr.length()) {
        		expr = expr.substring(0,expr.indexOf("["));
        		System.out.println("leftover1: " + expr);
        		
        		if(substring.contains("[")==false) {
            		
            		Expression x = new Expression(substring);
            		substring = Float.toString(x.evaluate());
            		expr = expr + substring;
            	}
        		else {
        			expr = substring + expr;
        		}
        		
        	}
        	else if(expr.indexOf("]") + 1 != expr.length()) {
        		expr = expr.substring(expr.indexOf("]")+1, expr.length());
        		System.out.println("leftover2: " + expr);
        		if(substring.contains("[")==false) {
            		
            		Expression x = new Expression(substring);
            		substring = Float.toString(x.evaluate());
            		expr = substring + expr;
            	}
        		else {
        		expr = expr + substring;
        		}
        	}
    	}
    	
    	}
    	return evalArray(expr);
    	}
    	
    
 
    
  
 
    public float evaluate() {
    		/** COMPLETE THIS METHOD **/
    		// following line just a placeholder for compilation
    	expr = expr.replaceAll("\\s","");
    	String delimsArray = "+-/*";
    	StringTokenizer allTokens = new StringTokenizer(expr,delims,true);
    	
    	Stack<String> operators = new Stack<String>();
    	Stack<String> temporaryOperators = new Stack<String>();
    	Stack<Float> temporaryNumbers = new Stack<Float>();
    	Stack<Float> numbers = new Stack<Float>();
    	String token; 	
    	StringTokenizer x = new StringTokenizer(expr,delimsArray);
		String arrayToken;
		int arrayIndex = 0;
    	//populates numbers and operators stacks
    	
    		if(expr.contains("(") == true) {
    			Float answer = Float.valueOf(eval(expr));
    			System.out.println("number "+answer);
    			
    			return answer;
    		}
    		
    		
    		
		
    	while (allTokens.hasMoreTokens()) {
    		token = allTokens.nextToken();
    		System.out.println("token:" +token);
    		//character is a number
    		if(Character.isDigit(token.charAt(0)) == true){
    			temporaryNumbers.push(Float.valueOf(token));
    		}
    		//character is an operator
    		else if(Character.isDigit(token.charAt(0)) == false && Character.isLetter(token.charAt(0)) == false) {
    			temporaryOperators.push(token);
    		}
    		//Character is a scalar and populates scalars
    		else if(Character.isLetter(token.charAt(0)) == true && !scalars.isEmpty() && arrays.isEmpty()) {
    			for(int j = 0; j < scalars.size();j++) {
    		  	if(token.contains(scalars.get(j).name)) {
    			  temporaryNumbers.push(Float.valueOf(scalars.get(j).value));
    		  }
    			}	
    		}
    		
    		else if(Character.isLetter(token.charAt(0)) == true && scalars.isEmpty() && !arrays.isEmpty()) {
    			while(x.hasMoreTokens()) {
    				arrayToken = x.nextToken();
    				System.out.println(arrayToken);
    				arrayToken = arrayToken.substring(arrayToken.indexOf("["), arrayToken.length());
    				float p = Float.parseFloat(evalArray(arrayToken));
    				
    				arrayIndex = Math.round(p);
    			
    				
    			
    				for(int j = 0; j < arrays.size();j++) {
    					if(token.contains(arrays.get(j).name)) {
    						float a = arrays.get(j).values[arrayIndex]; 
    						System.out.println("temp Stackl " + a);
    						temporaryNumbers.push(a);
        		  		
        		  }        		
    			
        		}
    				
    				
    		}for(int d = 0;d<expr.length();d++) {
    					if((expr.charAt(d) == '+' && expr.charAt(d-1) == ']')||(expr.charAt(d) == '-' && expr.charAt(d-1) == ']')||(expr.charAt(d) == '/' && expr.charAt(d-1) == ']')||(expr.charAt(d) == '*' && expr.charAt(d-1) == ']')) {
    						temporaryOperators.push(expr.substring(d, d+1));
    					}
    				}
    	break;		
    	}	
    }
    	System.out.println("tempnum1"+temporaryNumbers.peek());
    	System.out.println("tempnum2"+temporaryNumbers.pop());
       
    	//keeps operations going left to right
    	while(!temporaryOperators.isEmpty()) {
    		operators.push(temporaryOperators.pop());
    	}
    	while(!temporaryNumbers.isEmpty()) {
    		numbers.push(temporaryNumbers.pop());
    	}
    	
    	//puts operators and numbers into a temporary stack
    	while(!operators.isEmpty()) {
    		//evaluates multiply and puts result back in stack
    		if(operators.peek().equals("*")) {   		
    			operators.pop();
    				numbers.push(numbers.pop() * numbers.pop());
    			}
    		//evaluates divide and puts result back in stack
    		else if(operators.peek().equals("/")) {
    			operators.pop();
    			numbers.push(numbers.pop() / numbers.pop());
    		}
    		//stores add & subtract in temp stack
    		else if(operators.peek().equals("+")|| (operators.peek().equals("-")))  {
    			temporaryOperators.push(operators.pop());
    			temporaryNumbers.push(numbers.pop());
    		}
    	}
    	
    	//handles remainder number in numbers stack
    	if(numbers.size() ==1 && !temporaryNumbers.isEmpty()) {
    		temporaryNumbers.push(numbers.pop());
    	}
    	//keeps operations going left to right
    	while(!temporaryOperators.isEmpty()) {
    		operators.push(temporaryOperators.pop());
    	}
    	while(!temporaryNumbers.isEmpty()) {
    		numbers.push(temporaryNumbers.pop());
    	}
    	float answer = 0;
    	//handles single value
    	if(numbers.size() ==1 && temporaryNumbers.size()== 0) {
    		answer = numbers.pop();
    		return answer;
    	}
   //evaluates add and subtract stack
    	while(!operators.isEmpty()) {
    		if(operators.peek().equals("+")) {
    			operators.pop();
    			numbers.push(numbers.pop() + numbers.pop());   	
    		}
    		else if(operators.peek().equals("-")){
    			operators.pop();
    			numbers.push(numbers.pop() - numbers.pop());    		
    		}
    	}
    	//if last element means answer so return
    	if((numbers.size()== 1)) {
    		answer = numbers.pop();
    		return answer;
    	}
    	return answer;
    		
    }
    
//    private float evaluate(String expr,int start,int end) {
//    		if(expr.contains("(")== false) {
//    			return Float.parseFloat(expr);
//    		}
//    		else {
//    			return evaluate(expr,expr.indexOf("("),expr.indexOf(")"));
//    		}
//    		
//    	}
    
    	

    /**
     * Utility method, prints the symbols in the scalars list
     */
    public void printScalars() {
        for (ScalarSymbol ss: scalars) {
            System.out.println(ss);
        }
    }
    
    /**
     * Utility method, prints the symbols in the arrays list
     */
    public void printArrays() {
    		for (ArraySymbol as: arrays) {
    			System.out.println(as);
    		}
    }

}
