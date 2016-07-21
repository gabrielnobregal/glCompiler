package br.glcompiler.lex;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.Reader;

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
		// Includes all type of tabulation: see: http://unicode-table.com/
		while (lac <= ' ') {
			nextChar();
		}
	}

	@Override
	public Token nextToken() {

		skipBlankChars();

		Token token = new Token(localization);

		if (lac == EOF_CHAR) {
			token.setKind(Token.Kind.EOF);
		} 
		
		/*
		else 
		if (Character.isDigit(lac)) {
			readNumber(token);
		} else 
		if (Character.isLetter(lac)) {
			readName(token);
		} else 
		if (lac == '\'') {
			readCharacter(token);
		} else 
		if (lac == '/') { // This test is not include in readOperator
			// because of recursion (return next())
			nextChar();
			if (lac == '/') {
				// skip commentary
				do {
					nextChar();
				} while (c != EOL && c != EOF_CHAR);
				nextChar();
				return next(); // return next token (commentary is not a token)
			} else {
				token.kind = Token.Kind.SLASH;
				token.string = "/";
			}
		} else if (!readOperator(token)) {
			// An invalid operator that begins with ! but differ
			// from != could been read in readOperator.
			// If so, token is already built, so token.string is NOT empty.
			if (token.string.isEmpty()) {
				token.kind = Token.Kind.UNKNOWN;
				token.errorMessage = ERROR_INVALID_SYMBOL;
				nextChar();
			}
		}
		*/
		return token;
	}

}
