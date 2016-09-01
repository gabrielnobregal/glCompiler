package br.glcompiler.codegen;

public class BCFactory {
	
	private BCFactory() {		
	}
	
	public static BCClassWriter createClass(String className) {		
		return new BCClassWriter(className);
	}
	
	public static BCMethodWriter createMethod(BCClassWriter classWriter) {		
		return new BCMethodWriter(classWriter);
	}

}
