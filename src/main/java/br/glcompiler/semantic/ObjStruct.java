package br.glcompiler.semantic;


public class ObjStruct {
	
	// Any object	
	private StructKind structKind; // ARRAY
	
	// For arrays
	private ObjStruct arrayType; //// Tipo do array...
	
	// For classes
	private int numFields;
	private Obj field;
	
	
	public ObjStruct(StructKind structKind) {
		super();
		this.structKind = structKind;
	}

	public StructKind getStructKind() {
		return structKind;
	}

	public void setStructKind(StructKind structKind) {
		this.structKind = structKind;
	}

	public ObjStruct getArrayType() {
		return arrayType;
	}

	public void setArrayType(ObjStruct arrayType) {
		this.arrayType = arrayType;
	}

	public int getNumFields() {
		return numFields;
	}

	public void setNumFields(int numFields) {
		this.numFields = numFields;
	}

	public Obj getField() {
		return field;
	}

	public void setField(Obj field) {
		this.field = field;
	}

}
