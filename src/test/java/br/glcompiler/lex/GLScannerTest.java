package br.glcompiler.lex;

import java.io.StringReader;
import org.junit.Test;
import junit.framework.TestCase;


public class GLScannerTest extends TestCase {	
	
	static Token.Kind tk; // access shortcut
	
	private void tokenSetAssert(GLScanner scanner, Token.Kind ... tokenKinds) {		
		for(Token.Kind kind : tokenKinds) {
			assertTrue(kind == scanner.nextToken().getKind());
		}
		
		assertTrue(Token.Kind.EOF == scanner.nextToken().getKind());		
	}	
	
	
	@SuppressWarnings("static-access")
	@Test
	public void basicScannerTest() {		
		
		// First set of test 
		tokenSetAssert(new GLScannerWrapper("if(a<=  5){}"), tk.IF, tk.LEFT_PARENTHESIS, 
															 tk.IDENTIFIER, tk.LESS_EQUAL, 
															 tk.NUMBER, tk.LEFT_BRACE, 
															 tk.RIGHT_BRACE );	
		
		
	}
	
	
	
}
