package br.glcompiler.lex;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import org.apache.log4j.Logger;
import br.glcompiler.lex.Token.Kind;
import static br.glcompiler.lex.Token.*;

public class GLScanner implements Scanner {
	
	private Logger logger = Logger.getLogger(GLScanner.class);	

	private char lac; // lookahead character
	private Reader reader;
	private Localization localization;
 
	private static final char EOL = '\n';
	private static final char EOF_CHAR = '\u0080';

	public GLScanner(File srcFile) {
	}

	public GLScanner(Reader reader) {
		this.reader = reader;
		localization = new Localization();
		nextChar();		
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
        
        return new Token(Token.getKeywordKind(lexeme.toString()), lexeme.toString(), localization); 
    }
	
	private Token readNumber() {
		
        StringBuilder lexeme = new StringBuilder();
        
        while (Character.isDigit(lac)) {
            lexeme.append(lac);
            nextChar();
        }   
        
        return new Token(Kind.NUMBER, lexeme.toString(), localization);
	}
	

	private Token readOperator() {		
        StringBuilder lexeme = new StringBuilder(2);
        String prefix = "" + lac;
        Kind lexKind =  Token.getOperatorKind("" + prefix);
        
        if(Token.hasDoubleCharOperatorPrefix(lac)) {
        	
        	lexeme.append(lac);
        	nextChar();
        	lexeme.append(lac);
        	    
        	lexKind = Token.getOperatorKind(lexeme.toString());
        	
        	if(lexKind != Kind.UNKNOWN) {
        		nextChar();
        	}
        } else {
        	nextChar();
        }       
        
        return new Token(lexKind, lexeme.toString(), localization);
	}
	
	
	private Token readCharacter() {
		return null;
	}
	
	@Override
	public Token nextToken() {

		skipBlankChars();

		Token token = null;
		
		if (lac == EOF_CHAR) {
			token = new Token(Token.Kind.EOF, localization); 
		} else
		if (Character.isDigit(lac)) {
			token = readNumber(); 
		} else 
		if (Character.isLetter(lac)) {
			token = readName();
		} else			
		if (lac == '\"') {
			token = readCharacter();
		} else {
			token = readOperator();
		}
			
		
		return token;
	}

}
