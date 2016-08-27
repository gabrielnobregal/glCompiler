package br.glcompiler.semantic;

import java.util.Optional;

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
		
	public Obj insertVariable(String identifier, Token.Kind tokenKind, boolean isArray) throws SemanticException {
		return currentScope.insertObject(currentScope, identifier, ObjKind.VARIABLE, StructKind.NONE);
    }
	
	public Optional<Obj> findObj(String identifier) {		
		Optional<Obj> object = Optional.empty();
		
		for(Scope s = currentScope; s != null && !object.isPresent(); s = s.getOuter()) {
			object = s.findObject(identifier);
		}
		
		return object;		
	}
	
	private void defineVar(ObjStruct objStruct, StructKind structKind, boolean isArray) {		
		if(isArray) {
			objStruct.setArrayType(new ObjStruct(structKind));
		} else {
			objStruct.setStructKind(structKind);
		}
	}
	
	private ObjStruct typeVar(String identifier, Token.Kind tokenKind, boolean isArray) {
		
		ObjStruct objStruct = new ObjStruct(StructKind.NONE);
		
		if(isArray) {
			objStruct.setStructKind(StructKind.ARRAY);
		}
		
		defineVar(objStruct, StructKind.get(tokenKind), isArray);		
		
		return objStruct;
	}
	
	
	/*
	public Obj insertMethod(String identifier) throws SemanticException {
        return currentScope.insertObject(currentScope, identifier, ObjKind.TYPE, StructKind.CLASS);
    }
    */
}
