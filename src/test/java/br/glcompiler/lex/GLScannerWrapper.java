package br.glcompiler.lex;

import java.io.StringReader;

public class GLScannerWrapper extends GLScanner {

	public GLScannerWrapper(String sourceCode) {
		super(new StringReader(sourceCode));	
	}
}
