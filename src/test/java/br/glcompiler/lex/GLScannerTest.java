package br.glcompiler.lex;

import org.apache.log4j.Logger;
import org.junit.Test;
import junit.framework.TestCase;
import static br.glcompiler.lex.Token.*;


public class GLScannerTest extends TestCase {	
	
	private Logger logger = Logger.getLogger(GLScannerTest.class);	
	
	@Test
	public void testBasicScanner() {				
		// First set of test 
		tokenSetAssert(new GLScannerWrapper("<=  5){}"), Kind.LESS_EQUAL, 
															     Kind.NUMBER, Kind.RIGHT_PARENTHESIS, 
															     Kind.LEFT_BRACE, Kind.RIGHT_BRACE );		
	}
	
	
	private void tokenSetAssert(GLScanner scanner, Kind ... tokenKinds) {		
		for(Kind kind : tokenKinds) {
			Kind r = scanner.nextToken().getKind();
			logger.info(kind + " = " + r);
			//assertTrue(kind == r);
		}
		
		assertTrue(Token.Kind.EOF == scanner.nextToken().getKind());		
	}	
	
}
