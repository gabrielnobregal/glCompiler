package br.glcompiler.lex;

import org.apache.log4j.Logger;
import org.junit.Test;
import br.glcompiler.lex.Token.Kind;
import junit.framework.TestCase;


public class GLScannerTest extends TestCase {	
	
	private Logger logger = Logger.getLogger(GLScannerTest.class);	
	
	@Test
	public void testBasicScanner() {
		
		// First set of test		
		logger.info("===Basic statement test");
		tokenSetAssert(new GLScannerWrapper("se(a   <=  5){}"), Kind.IF, Kind.LEFT_PARENTHESIS, Kind.IDENTIFIER ,Kind.LESS_EQUAL, 
															 	Kind.NUMBER, Kind.RIGHT_PARENTHESIS, 
															 	Kind.LEFT_BRACE, Kind.RIGHT_BRACE );
		
		// Full simple valid program	
		logger.info("===Full program test");
		tokenSetAssert(new GLScannerWrapper("importar \"teste.gl\" \n" +  
										    "classe MinhaClasse  { \n" +
										    "\t   inteiro valor; " +
										    "}" +
										    
										    "classe App \t { " +
										    
										    "\t metodo inicio() {" +
										    "inteiro a = 2 + 5;" +										    
											"}"+
											"}"),
											
											Kind.IMPORT, Kind.DOUBLE_QUOTES, Kind.STRING, Kind.DOUBLE_QUOTES,
											
											Kind.CLASS, Kind.IDENTIFIER, Kind.LEFT_BRACE,
											Kind.INTEGER, Kind.IDENTIFIER, Kind.SEMICOLON,	
											Kind.RIGHT_BRACE,
											
											Kind.CLASS, Kind.IDENTIFIER, Kind.LEFT_BRACE,											
											Kind.METHOD, Kind.IDENTIFIER, Kind.LEFT_PARENTHESIS, Kind.RIGHT_PARENTHESIS, Kind.LEFT_BRACE,
											Kind.INTEGER, Kind.IDENTIFIER, Kind.ASSIGN, Kind.NUMBER, Kind.PLUS, Kind.NUMBER, Kind.SEMICOLON,
											Kind.RIGHT_BRACE, Kind.RIGHT_BRACE
											);
		
	}
	
	
	private void tokenSetAssert(GLScanner scanner, Kind ... tokenKinds) {		
		for(Kind kind : tokenKinds) {
			Token t = scanner.nextToken();
			logger.info(kind + " = " + t);
			if(t.getKind() != kind) {				
				fail("Expected " + kind + " but returns " + t);
			}			
		}		
				
	}	
	
}
