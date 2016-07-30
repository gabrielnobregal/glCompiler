package br.glcompiler.parser;

import br.glcompiler.message.CompilerMessageLog;

public interface Parser {	
	public abstract void parseProgram();
	public CompilerMessageLog getMessageLog();
}
