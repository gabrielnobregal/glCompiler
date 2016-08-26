package br.glcompiler.semantic;


public class ObjStruct {
	
	// Any object	
	private StructKind structKind;
	
	// For arrays
	private ObjStruct arrayType; ////????
	
	// For classes
	private int numFields;
	private Obj field;
	
	
	public ObjStruct(StructKind structKind) {
		super();
		this.structKind = structKind;
	}
	

}
