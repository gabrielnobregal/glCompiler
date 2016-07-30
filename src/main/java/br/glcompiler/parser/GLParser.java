package br.glcompiler.parser;

import br.glcompiler.lex.Scanner;
import br.glcompiler.lex.Token;
import br.glcompiler.message.CompilerMessageI18N;
import br.glcompiler.message.CompilerMessageLog;
import br.glcompiler.message.CompilerMessageLogList;
import br.glcompiler.message.MessageType;

import static br.glcompiler.lex.Token.*;

import java.util.List;

public class GLParser implements Parser {
	
	private Token token; //currentToken		
	private Token lookaheadToken; //lookahead token
	private Scanner scanner;
	
	private CompilerMessageI18N externalMessage;
	private CompilerMessageLog messageLog;
	
	public GLParser(Scanner scanner) {
		externalMessage = new CompilerMessageI18N("en", "US"); // TODO: make this configurable
		messageLog = new CompilerMessageLogList(); // TODO: This is a compiler property. Inject same instance in all modules
		
		this.scanner = scanner;
		token = lookaheadToken = new Token();
		nextToken();		
	}	
	
	@Override
	public CompilerMessageLog getMessageLog() {		
		return messageLog;
	}
	
	private Token getToken() {
		return token;
	}
	
	private Token.Kind getKind()  {
		return token.getKind();
	}
	
	private Token getLookaheadToken() {
		return lookaheadToken;
	}
	
	private Token.Kind getLookaheadKind()  {
		return lookaheadToken.getKind();
	}
		
	private void nextToken() {
		token = lookaheadToken;
		lookaheadToken = scanner.nextToken();
	}	
	
	private MessageDefinition defineMessage(MessageType type, String ... parameters) {
		return new MessageDefinition(type, parameters);
	}
	
	class MessageDefinition {
		public MessageType type;
		public String parameters[];
		
		public MessageDefinition(MessageType type, String ... parameters) {
			this.type = type;
			this.parameters = parameters;
		}
	}
	
	
	private boolean tokenMatch(MessageDefinition messageDefinition, Token.Kind ... kind) {			
       boolean match = tokenMatch(kind) ;
       
       if(!match) {
    	   messageLog.addMessage(externalMessage.getErrorMessage(messageDefinition.type, token.getLocalization(), messageDefinition.parameters), 
    			   				 messageDefinition.type, token);       
       }
       
       return match;       
    }
	
	private boolean tokenMatch(Token.Kind... kind) {
		for (Token.Kind k : kind) {
            if (getToken().getKind() == k) {
            	nextToken();
                return true;
            }
         }
		
		return false;		
	}
	
	@Override
	public void parseProgram() {
		nextToken();
		program();
	}
	
	public void program() {
		
		while(tokenMatch(Kind.IMPORT)) {
			importSource();			
		}
		
		boolean classExists = false;
		
		while(getKind() == Kind.CLASS) {
			classDecl();		
			classExists = true;
		}
		
	}
	
	public void importSource() {
		
		//nextToken();
		tokenMatch(defineMessage(MessageType.NO_CLASS_DECLARED), Kind.DOUBLE_QUOTES); 
		
		nextToken();
		//tokenMatch("Expected local filename to defining source name.", Kind.TEXT);
		
		nextToken();
		//tokenMatch("Expected \"(double quotes) to enclose a source name.", Kind.DOUBLE_QUOTES);	
		
		nextToken();
	}
	
	public void classDecl() {
		
	}

	
	
	
	
	
	
		

}
