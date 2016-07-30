package br.glcompiler.message;

import br.glcompiler.lex.Token;

public class Message {
	
	private Token token;
	private String text;
	private MessageType type;
	
	
	public Message(String text, MessageType type, Token token) {
		super();
		this.token = token;
		this.text = text;
		this.type = type;
	}

	public Token getToken() {
		return token;
	}

	protected void setToken(Token token) {
		this.token = token;
	}

	public String getText() {
		return text;
	}

	protected void setText(String text) {
		this.text = text;
	}
	
	public MessageType getType() {
		return type;
	}

	protected void setType(MessageType type) {
		this.type = type;
	}


}
