package br.glcompiler.codegen;

import java.io.File;

import org.apache.log4j.Logger;
import org.junit.Test;

public class GLCodeGenTest {
	
	private Logger logger = Logger.getLogger(GLCodeGenTest.class);	
	private GLClassLoader classLoader;
	
	private String testClassPath = "/home/napalm/workspace/glCompiler/target/test-classes";
	private String testPackage = "testgen";
	
	public GLCodeGenTest() {
		classLoader = new GLClassLoader();
		new File(getPackagePath()).mkdirs();		
	}
			
	@Test
	public void invokeSimpleMethod() throws Exception {
		String className = "teste";
		
		BCClassWriter cw = BCClassWriter.createClass(testPackage, className);
		cw.createMethod("calcula").build();
		cw.buildClassToFile(getPackagePath());
		
		Class clazz = classLoader.load("testgen.teste"); 
		classLoader.invokeClassMethod(clazz, "calcula");
	}	
	
	@Test
	public void basicMethodMath() throws Exception {		
		
	}
	
	private String getClassName(String name) {
		return testPackage + "." + name;
	}
	
	private String getClassPath() {
		return testClassPath;
	}
	
	private String getPackagePath() {
		return getClassPath() + "/" + testPackage;
	}
	
	private String getClassFilePah(String name) {
		return testClassPath + "/" + testPackage + "/" + name + ".class";
	}
	
	private String getClass(String name) {
		return testPackage + "." + name;
	}

	
}
