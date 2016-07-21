package br.glcompiler.lex;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.Reader;

import br.glcompiler.lex.Token.Kind;

public class GLScanner implements Scanner {

	private char lac; // lookahead character
	private Reader reader;
	private Localization localization;
 
	private static final char EOL = '\n';
	private static final char EOF_CHAR = '\u0080';

	public GLScanner(File srcFile) {
	}

	public GLScanner(Reader reader) {
		reader = new BufferedReader(reader);
		localization = new Localization();
	}

	private void nextChar() {

		try {
			lac = (char) reader.read();
		} catch (IOException e) {
			lac = EOF_CHAR;
			return;
		}

		localization.incColumn();
		localization.incPosition();

		if (lac == EOL) {
			localization.incLine();
			localization.resetColumn();
		}
	}

	private void skipBlankChars() {
		// Includes all types of tabulation see: http://unicode-table.com/
		while (lac <= ' ') {
			nextChar();
		}
	}

	private Token readName() {
    
		StringBuilder lexeme = new StringBuilder();
		
        while (Character.isLetterOrDigit(lac)) {
            lexeme.append(lac);
            nextChar();
        }
        
        return new Token(Kind.NUMBER, lexeme.toString(), localization);
    }
	
	private Token readNumber() {
		
        StringBuilder lexeme = new StringBuilder();
        
        while (Character.isDigit(lac)) {
            lexeme.append(lac);
            nextChar();
        }   
        
        return new Token(Kind.NUMBER, lexeme.toString(), localization);
	}
	
	private Token readCharacter() {
		return null;
	}
	
	@Override
	public Token nextToken() {

		skipBlankChars();

		Token token = null;
		 
		if (Character.isDigit(lac)) {
			token = readNumber(); 
		} else 
		if (Character.isLetter(lac)) {
			token = readName();
		} else 
		if (lac == '\"') {
			token = readCharacter();
		} else 
		if (lac == EOF_CHAR) {
			token = new Token(Token.Kind.EOF, localization); 
		}
		return token;
	}

}
