package br.glcompiler.message;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import br.glcompiler.lex.Token;

public class CompilerMessageLogList implements CompilerMessageLog {

	private List<Message> messageList;
	
	public CompilerMessageLogList() {
		messageList = new ArrayList<>();
	}	
	
	@Override
	public void addMessage(String message, MessageType type, Token token,  Token.Kind expectedKind) {
		messageList.add(new Message(message, type, token, expectedKind));		
	}

	@Override
	public Iterator<Message> getMessages() {		
		return messageList.iterator();
	}
	

}
