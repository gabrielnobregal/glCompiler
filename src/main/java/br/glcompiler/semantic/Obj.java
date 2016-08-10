package br.glcompiler.semantic;


public class Obj {	
	
	public Kind kind;    // CONSTANT, VARIABLE, TYPE, METHOD, PROGRAM
    public String name; // object name
    
    public ObjStruct type; // object type
    public int val;     // CONSTANT: value
    
    public int adr;     // VARIABLE, Math: address
    public int level;   // VARIABLE: declaration level
    
    public int nParams; // METHOD: number of parameters
    public Obj locals;  // METHOD: parameters and local objects
   
    
   
    public enum Kind {
    	VARIABLE,
    	TYPE,
    	METHOD
    }	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public ObjType getType() {
		return type;
	}
	
	public void setType(ObjType type) {
		this.type = type;
	}
	
	public int getVal() {
		return val;
	}
	
	public void setVal(int val) {
		this.val = val;
	}
	
	public int getAdr() {
		return adr;
	}
	
	public void setAdr(int adr) {
		this.adr = adr;
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
