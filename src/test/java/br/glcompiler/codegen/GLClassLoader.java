package br.glcompiler.codegen;


public class GLClassLoader extends ClassLoader {
		
	public Class load(String className) throws ClassNotFoundException {			
		ClassLoader classLoader = this.getClass().getClassLoader();
		return  classLoader.loadClass(className);
	}

	public void invokeClassMethod(Class clazz, String methodName, Object ... args) throws Exception {				      
        Object obj = clazz.getConstructor().newInstance();      
        clazz.getMethod(methodName).invoke(obj);		
	}
	
	
}
