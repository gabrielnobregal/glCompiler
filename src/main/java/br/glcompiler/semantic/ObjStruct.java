package br.glcompiler.semantic;


public class ObjStruct {
	
	// Any object	
	private Kind structKind;
	
	// For arrays
	private ObjStruct arrayType;
	
	// For classes
	private int numFields;
	private Obj field; // acessed field class
	
	enum Kind {
		NONE,
		INTEGER,
		TEXT,
		REAL,
		CLASS
	}

}
