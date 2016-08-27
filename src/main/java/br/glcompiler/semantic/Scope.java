package br.glcompiler.semantic;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import br.glcompiler.exceptions.SemanticException;

public class Scope {
	
	private Scope outer;
	private LinkedList<Obj> objects;
	private int numVars;
	private int level;
	
	public Scope(Scope outer, int level) {
		this.outer = outer;
		this.level = level;
		this.objects = new LinkedList<Obj>();		
	}

	public Scope getOuter() {
		return outer;
	}

	public void setOuter(Scope outer) {
		this.outer = outer;
	}

	public int getNumVars() {
		return numVars;
	}

	public void setNumVars(int numVars) {
		this.numVars = numVars;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	public boolean objectExists(String identifier) {		
		return objects.stream().anyMatch(o -> identifier.equals(o.getName()));
	}
	
	protected Optional<Obj> findObject(String identifier) {
		return objects.stream().filter(o -> identifier.equals(o.getName())).findFirst();
	}
		
	protected Obj insertObject(Scope scope, String identifier, ObjKind objKind, StructKind structKind) throws SemanticException {
		
		if(objectExists(identifier)) {
			throw new SemanticException("Object already exists in scope."); // TODO: Change return to Optional
		}
		
		Obj object = new Obj(identifier, objKind, new ObjStruct(structKind));
		
		if (objKind == ObjKind.VARIABLE) {
            int n = scope.getNumVars();
			object.setAddress(n);
            scope.setNumVars(++n);            
            object.setLevel(scope.getLevel()); 
        }				
		
		objects.add(object);
		
		return object;
	}
	
}
