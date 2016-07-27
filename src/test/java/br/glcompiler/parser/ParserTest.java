package br.glcompiler.parser;

import org.apache.log4j.Logger;
import org.junit.Test;

import br.glcompiler.lex.GLScannerTest;
import br.glcompiler.lex.GLScannerWrapper;
import br.glcompiler.lex.Scanner;
import junit.framework.TestCase;

public class ParserTest	extends TestCase {	
		
		private Logger logger = Logger.getLogger(GLScannerTest.class);	
		
		@Test
		public void testBasicParser() {			
			
			Scanner scanner = new GLScannerWrapper("importar \"teste.gl\" "
												  + "classe Nova { }");
			Parser glParser = new GLParser(scanner);
			glParser.parseProgram();
			
		}
}
