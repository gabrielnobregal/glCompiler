package br.glcompiler.lex;

public class Localization {
	
	private int position;
	private int line;
	private int column;
		
	public int getPosition() {
		return position;
	}

	public int getLine() {
		return line;
	}

	public int getColumn() {
		return column;
	}

	public void incPosition() {
		this.position++;
	}
	
	public void incLine( ){
		this.line++;
	}
	
	public void incColumn() {
		this.column++;
	}
	
	public void resetColumn() {
		this.column = 0;
	}
}
