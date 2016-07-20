package br.glcompiler.lex;

public class Token {
	private String lexeme;
	private Kind category;
	
	
	public static enum Kind {
		
        UNKNOWN, EOF, IDENTIFIER, NUMBER, CHARACTER,
        
        // keywords
        CLASS, ELSE, IF, NEW, PRINT, PROGRAM, READ,
        RETURN, VOID, WHILE,
        
        // operators
        PLUS, MINUS, TIMES, SLASH, REMAINDER, EQUAL, NOT_EQUAL, LESS,
        LESS_EQUAL, GREATER, GREATER_EQUAL, ASSIGN, SEMICOLON, COMMA,
        PERIOD, LEFT_PARENTHESIS, RIGHT_PARENTHESIS, LEFT_BRACKET,
        RIGHT_BRACKET, LEFT_BRACE, RIGHT_BRACE
    }
	
	
	
}
