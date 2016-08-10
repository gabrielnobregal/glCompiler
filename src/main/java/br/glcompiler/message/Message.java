package br.glcompiler.message;

import br.glcompiler.lex.Token;

public class Message {
	
	private Token token;
	private String text;
	private MessageType type;
	private Token.Kind expectedTokenKind;
	
	
	public Message(String text, MessageType type, Token token, Token.Kind expectedTokenKind) {
		super();
		this.token = token;
		this.text = text;
		this.type = type;
		this.expectedTokenKind = expectedTokenKind;
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

	public Token.Kind getExpectedTokenKind() {
		return expectedTokenKind;
	}

	protected void setExpectedTokenKind(Token.Kind expectedTokenKind) {
		this.expectedTokenKind = expectedTokenKind;
	}

	
}
