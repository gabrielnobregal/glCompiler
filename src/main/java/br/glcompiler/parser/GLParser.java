package br.glcompiler.parser;

import br.glcompiler.MessageManager;
import br.glcompiler.MessageType;
import br.glcompiler.lex.Scanner;
import br.glcompiler.lex.Token;
import static br.glcompiler.lex.Token.*;

import java.util.List;

public class GLParser implements Parser {
	
	private Token token; //currentToken		
	private Token lookaheadToken; //lookahead token
	private Scanner scanner;
	
	private MessageManager messageManager;
	
	public GLParser(Scanner scanner) {
		messageManager = new MessageManager("en", "US"); // TODO: make this configurable
		
		this.scanner = scanner;
		token = lookaheadToken = new Token();
		nextToken();		
	}	
	
	@Override
	public List<String> getErrors() {
		
		return null;
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
	
	private void error(MessageHelper message) {
		System.err.println(messageManager.getErrorMessage(message.type, getToken().getLocalization(), message.parameters));
	}
	
	class MessageHelper {
		public MessageType type;
		public String parameters[];
		
		public MessageHelper(MessageType type, String ... parameters) {
			this.type = type;
			this.parameters = parameters;
		}
	}
	
	
	private boolean tokenMatch(MessageHelper message, Token.Kind... kind) {
		error(message);
       boolean match = tokenMatch(kind);
       if(!match) error(message);
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
		tokenMatch(new MessageHelper(MessageType.NO_CLASS_DECLARED), Kind.DOUBLE_QUOTES); 
		
		nextToken();
		//tokenMatch("Expected local filename to defining source name.", Kind.TEXT);
		
		nextToken();
		//tokenMatch("Expected \"(double quotes) to enclose a source name.", Kind.DOUBLE_QUOTES);	
		
		nextToken();
	}
	
	public void classDecl() {
		
	}

	
	
	
	
	
	
		

}
