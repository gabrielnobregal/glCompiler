package br.glcompiler.parser;

import br.glcompiler.codegen.BCClassWriter;
import br.glcompiler.lex.Scanner;
import br.glcompiler.lex.Token;
import br.glcompiler.message.CompilerMessageI18N;
import br.glcompiler.message.CompilerMessageLog;
import br.glcompiler.message.CompilerMessageLogList;
import br.glcompiler.message.MessageType;
import br.glcompiler.semantic.Obj;
import br.glcompiler.semantic.SymbolTable;

import static br.glcompiler.lex.Token.*;

import java.util.List;

public class GLParser implements Parser {
	
	private Token token; //currentToken		
	private Token lookaheadToken; //lookahead token
	private Scanner scanner;
	
	private SymbolTable symbolTable;
	private BCClassWriter classWriter; 
	
	
	private CompilerMessageI18N externalMessage;
	private CompilerMessageLog messageLog;
	
	public GLParser(Scanner scanner) {
		externalMessage = new CompilerMessageI18N("en", "US"); // TODO: make this configurable
		messageLog = new CompilerMessageLogList(); // TODO: This is a compiler property. Inject same instance in all modules
		symbolTable = new SymbolTable();
		
		// Init scannet and lookahead
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
	
	private void addMessage(Kind kind, MessageType errorMessageType) {
		messageLog.addMessage(externalMessage.getErrorMessage(errorMessageType, token.getLocalization(), kind.getValue(), token.getKind().getValue()), 
  				 errorMessageType, token, kind);  
	}
		
	private boolean tokenMatch(Kind kind, MessageType errorMessageType) {		
       
       if(getToken().getKind() != kind) {
    	   addMessage(kind, errorMessageType);
       }   
   
       return true;       
    }
	
	private boolean tokenMatch(Token.Kind kind) {
		 
		if (getToken().getKind() == kind) {
			 return true;
		 }
		 
		return false;		
	}
	
	private boolean tokenMatchMoveNext(Kind kind, MessageType errorMessageType) {		
		if(tokenMatch(kind, errorMessageType)) {
			nextToken();
			return true;		
		}
		return false;
	}
	
	private boolean tokenMatchMoveNext(Token.Kind kind) {
		 
		if(tokenMatch(kind)) {
			nextToken();
			return true;
		}
		 
		return false;		
	}
	
	
	@Override
	public void parseProgram() {
		nextToken();
		program();
	}
	
	private void program() {
		
		while(tokenMatch(Kind.IMPORT)) {
			importSource();			
		}
		
		boolean classExists = false;
		
		while(getKind() == Kind.CLASS) {
			classDecl();		
			classExists = true;
		}
		
		if(!classExists) {
			addMessage(Kind.CLASS, MessageType.NO_CLASS_DECLARED);
		}		
	}
	
	private void importSource() {		
		nextToken();	
		
		tokenMatchMoveNext(Kind.DOUBLE_QUOTES, MessageType.EXPECTED_BEGIN_SYMBOL);		
		tokenMatchMoveNext(Kind.STRING, MessageType.EXPECTED_SYMBOL);		
		tokenMatchMoveNext(Kind.DOUBLE_QUOTES, MessageType.EXPECTED_END_SYMBOL);		
	}
	
	private void classDecl() {
		nextToken();
		
		Obj classObject = null;
		
		if(tokenMatchMoveNext(Kind.IDENTIFIER, MessageType.EXPECTED_IDENTIFIER)) {		
			symbolTable.openScope();			
			classObject = symbolTable.insertClass(token.getLexeme());
			classWriter = BCClassWriter.createClass(token.getLexeme());
		} else {
			return;
		}
		
		tokenMatchMoveNext(Kind.LEFT_BRACE, MessageType.EXPECTED_BEGIN_SYMBOL);		
		
		while(!tokenMatchMoveNext(Kind.RIGHT_BRACE, MessageType.EXPECTED_END_SYMBOL)) {
			
			Kind k = getToken().getKind();
			
			if(k.isPrimitiveType() || k == Kind.IDENTIFIER) {
				varDecl(classObject);
			} else 
			if(k.isExecutionUnit()) {
				executionUnit(classObject);
			} else
			if(!tokenMatchMoveNext(Kind.EOF, MessageType.UNEXPECTED_END_OF_FILE)) {
				break;
			}
			
		} 
		
		symbolTable.closeScope();		
	}
	/*
	 * VarDecl = Type identifier ";" // Nao se pode inicializar a linguagem fora do método
	 * Type = (inteiro | real | texto) ["[""]"]
	 */
	private void varDecl(Obj obj) {		
		
		Kind varType = getToken().getKind();
		
		if(varType.isPrimitiveType()) {			
			
			boolean isArray = false;			
			
			if(tokenMatchMoveNext(Kind.LEFT_BRACKET, MessageType.EXPECTED_BEGIN_SYMBOL)) {
				tokenMatchMoveNext(Kind.RIGHT_BRACKET, MessageType.EXPECTED_BEGIN_SYMBOL);
				isArray = true;
			}
			
			if(tokenMatchMoveNext(Kind.IDENTIFIER, MessageType.EXPECTED_BEGIN_SYMBOL)) {
				//symbolTable.insertVariable(token.getLexeme(), varType, isArray)
			}			
			
			tokenMatchMoveNext(Kind.SEMICOLON, MessageType.EXPECTED_END_SYMBOL);
			
		} else {
			
			
			
			
		}
		
		
		
		
	}
	
	private void executionUnit(Obj obj) {
		
	}
}
