package br.glcompiler.message;

import java.util.Iterator;

import br.glcompiler.lex.Token;

public interface CompilerMessageLog {
	public abstract void addMessage(String message, MessageType type, Token token, Token.Kind expectedKind);
	public abstract Iterator<Message> getMessages();	
}
