package br.glcompiler.semantic;

public class SymbolTable {

	private Scope currentScope;
		
	void openScope() {
		currentScope = new Scope(currentScope, currentScope.getLevel() + 1);
	}
	
	void closeScope() {
		currentScope = currentScope.getOuter();
	}
	
}
