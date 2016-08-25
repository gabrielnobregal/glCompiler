package br.glcompiler.semantic;

import br.glcompiler.exceptions.SemanticException;

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
	/*
	public Obj insertVar(String identifier, boolean isArray) throws SemanticException {
        return currentScope.insertObject(currentScope, identifier, ObjKind.TYPE, StructKind.CLASS);
    }

	public Obj insertMethod(String identifier) throws SemanticException {
        return currentScope.insertObject(currentScope, identifier, ObjKind.TYPE, StructKind.CLASS);
    }
    */
}
