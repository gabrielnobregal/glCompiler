package br.glcompiler.parser;

import java.util.HashSet;
import java.util.Set;
import static br.glcompiler.lex.Token.*;

@SuppressWarnings(value = { "serial" })
public class FirstSet {
	
	public static final Set<Kind> mulop, actPars, addOp, expr, type,
    formPars, varDecl, methodDecl, constDecl,
    classDecl, block, designator, statement,
    factor, term;
	
	
	 static {        
		 	
		 	mulop = new HashSet<Kind>() {{
	        	add(Kind.TIMES);
	        	add(Kind.SLASH);
	        	add(Kind.REMAINDER);
	        }};
	        
	        actPars =  new HashSet<Kind>() {{
	        	add(Kind.LEFT_PARENTHESIS);		        	
	        }};
	        
	        addOp =  new HashSet<Kind>() {{
	        	add(Kind.PLUS);
	        	add(Kind.MINUS);	        	
	        }};
	        
	        designator =  new HashSet<Kind>() {{
	        	add(Kind.IDENTIFIER);	        	        	
	        }};
	        
	        factor =  new HashSet<Kind>() {{
	        	add(Kind.NUMBER);
	        	add(Kind.CHARACTER);
	        	add(Kind.INSTANTIATE);
	        	add(Kind.LEFT_PARENTHESIS);	        	
	        }};
	        	        
	        term = new HashSet<Kind>(factor);
	        
	        expr = new HashSet<Kind>(term) {{
	        	add(Kind.MINUS);                	
	        }};	        
	       
	        type = new HashSet<Kind>() {{
	        	add(Kind.IDENTIFIER);                	
	        }};
	       
	        formPars = type;
	        
	        varDecl = type;

	        methodDecl = new HashSet<Kind>() {{
	        	//add(Kind.VOID);                	
	        }};
	        
	
	        constDecl = new HashSet<Kind>() {{
	        	//add(Kind.FINAL);                	
	        }};

	        
	        classDecl = new HashSet<Kind>() {{
	        	add(Kind.CLASS);                	
	        }};
	     
	        block = new HashSet<Kind>() {{
	        	add(Kind.LEFT_BRACE);                	
	        }};

	        statement = new HashSet<Kind>(block) {{
	        	addAll(designator);
	        	add(Kind.IF);
	        	add(Kind.WHILE); 
	        	add(Kind.RETURN); 
	        	//add(Kind.READ); 
	        	//add(Kind.PRINT); 
	        	add(Kind.SEMICOLON);	        	
	        }};
	        
	 }
	

}
