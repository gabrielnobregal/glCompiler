package br.glcompiler.semantic;


public class Obj {	
	
	private ObjKind kind;    // VARIABLE, TYPE, METHOD
	
	private String name; // object name
    
	private ObjStruct struct; // object type
    
	private int val;     // CONSTANT: value
    
	private int address;     // ???VARIABLE, Math: Elementos locais void t(inteiro a, inteiro b)==> adr(a) = 1 e adr(b) = 2 -> pos das vars locais
	private int level;   // Todo escopo aberto increment - declaration level
    
	private int nParams; // METHOD: number of parameters
	private Obj locals;  // METHOD/Class: parameters and local objects
   
    public Obj(String name, ObjKind kind, ObjStruct struct) {
    	this.name = name;
    	this.kind = kind;
    	this.struct = struct;    	
    }  
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public ObjStruct getStruct() {
		return struct;
	}
	
	public void setType(ObjStruct struct) {
		this.struct = struct;
	}
	
	public int getVal() {
		return val;
	}
	
	public void setVal(int val) {
		this.val = val;
	}		
	
	public int getAddress() {
		return address;
	}

	public void setAddress(int address) {
		this.address = address;
	}

	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getnParams() {
		return nParams;
	}
	
	public void setnParams(int nParams) {
		this.nParams = nParams;
	}
	
	public Obj getLocals() {
		return locals;
	}	
	  
	@Override
	public boolean equals(Object obj) {
	   
		if(name == null || obj == null) {
		   return name == obj;
		}
	   
		if(obj instanceof Obj) {
		   return name.equals(((Obj)obj).name);   
		}
	   
		return false;	       	
	}

}
