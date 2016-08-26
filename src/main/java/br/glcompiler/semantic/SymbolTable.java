package br.glcompiler.semantic;

import br.glcompiler.exceptions.SemanticException;
import br.glcompiler.lex.Token;

public class SymbolTable {

	private Scope currentScope;	
	
	public void openScope() {
		currentScope = new Scope(currentScope, currentScope.getLevel() + 1);
	}
	
	public void closeScope() {
		currentScope = currentScope.getOuter();
	}
	
	public Obj insertClass(String identifier) throws SemanticException {
        return currentScope.insertObject(currentScope, identifier, ObjKind.TYPE, StructKind.CLASS);
    } 
		
	public Obj insertVariable(String identifier, Token.Kind type, boolean isArray) throws SemanticException {
		return currentScope.insertObject(currentScope, identifier, ObjKind.VARIABLE, StructKind.CLASS);
    }
	
	private ObjStruct typeVar(String identifier, Token.Kind type, boolean isArray) {
		
				
		return null;
	}
	/*
	public Obj insertMethod(String identifier) throws SemanticException {
        return currentScope.insertObject(currentScope, identifier, ObjKind.TYPE, StructKind.CLASS);
    }
    */
}
