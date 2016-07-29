package br.glcompiler.parser;

import java.util.List;

public interface Parser {	
	public abstract void parseProgram();
	public abstract List<String> getErrors();
}
