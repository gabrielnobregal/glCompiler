package br.glcompiler.semantic;

import br.glcompiler.lex.Token;

public enum StructKind {
	NONE,
	
	INTEGER,
	TEXT,
	REAL,
	CLASS,
	
	ARRAY;
	
	public static StructKind get(Token.Kind kind) {
		
		switch (kind) {
		case INTEGER:
			return StructKind.INTEGER;
		case TEXT:
			return StructKind.TEXT;		
		case DOUBLE:
			return StructKind.REAL;		
		}		
		
		return NONE;
	}
	
}