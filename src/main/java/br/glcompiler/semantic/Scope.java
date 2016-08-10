package br.glcompiler.semantic;

import java.util.LinkedList;
import java.util.List;

public class Scope {
	
	private Scope outer;
	private List<Obj> objects;
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
	
	public void insertObj(Obj.Kind kind, String name, ObjStruct objStruct) {
		 Obj object = new Obj(kind, name, objStruct);
	        if (kind == Obj.VARIABLE) {
	            object.adr = scope.nVars;
	            scope.nVars++;
	            object.level = scope.level;
	        }
		
	}
	
	
	
}
