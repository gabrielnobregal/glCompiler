package br.glcompiler.lex;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import br.glcompiler.lex.Token.Kind;
import static br.glcompiler.lex.Token.*;

public class GLScanner implements Scanner {
	
	//private Logger logger = Logger.getLogger(GLScanner.class);	

	private char lac; // lookahead character
	
	private Reader reader;
	private Localization localization;
	
	private int doubleQuotesNumber;
	private Token lastToken;
	
	private static final char EOL = '\n';
	private static final char EOF_CHAR = '\u0080';

	public GLScanner(File srcFile) {
	}

	public GLScanner(Reader reader) {
		this.reader = reader;
		localization = new Localization();
		nextChar();	
		lastToken = new Token(Kind.UNKNOWN, localization);
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
	

	private Token readString() {
		StringBuilder lexeme = new StringBuilder();		
		
		do {
			lexeme.append(lac);
			nextChar();
		} while(lac != '\"' && lac != EOL );
		
		return new Token(Kind.STRING, lexeme.toString(), localization);
	}
	
	private boolean hasDoubleQuotesOpened() {
		return (doubleQuotesNumber % 2 == 1 && lastToken.getKind() == Kind.DOUBLE_QUOTES);
	}
	
	private Token readOperator() {		
        StringBuilder lexeme = new StringBuilder(2);
        String prefix = "" + lac;
        Kind lexKind =  Token.getOperatorKind("" + prefix);
        Kind lookAheadKind;
        
        if(Token.hasDoubleCharOperatorPrefix(lac)) {
        	
        	lexeme.append(lac);
        	nextChar();
        	lexeme.append(lac);
        	    
        	lookAheadKind = Token.getOperatorKind(lexeme.toString());
        	
        	if(lookAheadKind != Kind.UNKNOWN) {
        		lexKind = lookAheadKind;
        		nextChar();
        	} 
        	
        } else {
        	nextChar();
        }       
        
       
        if(lexKind == Kind.DOUBLE_QUOTES) {
        	doubleQuotesNumber++;
        }
        
        
        return new Token(lexKind, lexeme.toString(), localization);
	}
	
	
	@Override
	public Token nextToken() {
		
		// Spaces are skipped only outside of double quotes
		if(!hasDoubleQuotesOpened()) {
			skipBlankChars(); 
		}
		
		Token token = null;
		
		if (lac == EOF_CHAR) {
			token = new Token(Token.Kind.EOF, localization); 
		} else
		if(hasDoubleQuotesOpened()) { 
			token = readString();
		} else
		if (Character.isDigit(lac)) {
			token = readNumber(); 
		} else 
		if (Character.isLetter(lac)) {
			token = readName();
		} else {		
			token = readOperator();
		}
			
		
		return (lastToken = token);
	}

}
