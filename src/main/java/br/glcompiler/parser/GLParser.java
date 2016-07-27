package br.glcompiler.parser;

import br.glcompiler.lex.GLScannerWrapper;
import br.glcompiler.lex.Scanner;
import br.glcompiler.lex.Token;
import static br.glcompiler.lex.Token.*;

public class GLParser implements Parser {

	private Token currentToken;
	private Token.Kind currentKind;
	
	private Token laToken; //lookahead token
	private Scanner scanner;
	
	public GLParser(Scanner scanner) {
		this.scanner = scanner;
	}	
	
	private void nextToken() {
		currentToken = laToken;
		laToken = scanner.nextToken();
		currentKind = laToken.getKind();
	}
	
	private boolean ifKindNotMatch(Kind kind, String message) {
		if(currentKind != kind) {
			System.out.println("Error detected: expected '" + Kind.CLASS.getValue() + "' but found '" + currentKind.getValue() + "'" +  message);
			return true;
		}
		return false;
	}
	
	@Override
	public void parseProgram() {	
		program();
	}
	
	public void program() {
		
		while(currentKind == Kind.IMPORT) {
			importSource();
			nextToken();
		}		
		
		ifKindNotMatch(Kind.CLASS, "" );
		
		while(currentKind == Kind.CLASS) {
			classDecl();
			nextToken();
		}
	}
	
	public void importSource() {
		
	}
	
	public void classDecl() {
		
	}
	
	
	
	
	
		

}
