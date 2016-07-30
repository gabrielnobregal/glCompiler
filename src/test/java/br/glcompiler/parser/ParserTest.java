package br.glcompiler.parser;

import java.util.Iterator;
import org.apache.log4j.Logger;
import org.junit.Test;
import br.glcompiler.lex.GLScannerTest;
import br.glcompiler.lex.GLScannerWrapper;
import br.glcompiler.lex.Scanner;
import br.glcompiler.message.CompilerMessageLog;
import br.glcompiler.message.Message;
import br.glcompiler.message.MessageType;
import junit.framework.TestCase;

public class ParserTest	extends TestCase {	
		
		private Logger logger = Logger.getLogger(GLScannerTest.class);	
		
		
		private boolean hasParseError(CompilerMessageLog messageLog, MessageType messageType) {
			Iterator<Message> it = messageLog.getMessages();
			
			while(it.hasNext()) {
				Message m = it.next();
				logger.info(m.getType().name() + "==" + messageType.name());
				if(m.getType() == messageType) {
					return true;
				}				
			}
			
			return false;
		}
		
		private boolean validateParserErrors(CompilerMessageLog messageLog, MessageType ... messageTypes) {			
			for(MessageType t : messageTypes) {				
				assertEquals(true, hasParseError(messageLog, t));
			}			
			
			return true;
		}	
		
		private CompilerMessageLog getParserMessages(String sourceCode ) {
			Scanner scanner = new GLScannerWrapper(sourceCode);
			Parser glParser = new GLParser(scanner);
			glParser.parseProgram();
			return glParser.getMessageLog();
		}
		
		
		@Test
		public void testBasicParser() {			
			
			validateParserErrors(getParserMessages("importar \"teste.gl\" " +
					  						       " Nova { }"),
					  					           MessageType.NO_CLASS_DECLARED);			
			
		}
}
