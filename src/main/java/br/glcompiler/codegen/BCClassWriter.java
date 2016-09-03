package br.glcompiler.codegen;

import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.ACC_SUPER;
import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.GETSTATIC;
import static org.objectweb.asm.Opcodes.INVOKESPECIAL;
import static org.objectweb.asm.Opcodes.INVOKEVIRTUAL;
import static org.objectweb.asm.Opcodes.RETURN;
import static org.objectweb.asm.Opcodes.V1_7;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import br.glcompiler.semantic.Obj;

public class BCClassWriter {
	
	private ClassWriter cw;
	private String className;
	private String packageName;
	
	private BCClassWriter() {		
	}
	
	public static BCClassWriter createClass(String packageName, String className) {
		 BCClassWriter bClassWriter = new BCClassWriter();		 
		 bClassWriter.setupSimpleClass(packageName, className);
		return bClassWriter;
	}
	
	private void setupSimpleClass(String packageName, String className) {
		
		setPackageName(packageName);
		setClassName(className);		
		
		// Create class
		cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);		
		cw.visit(V1_7, ACC_PUBLIC+ACC_SUPER, packageName + "/" + className, null, "java/lang/Object", null);
		
		// Create default constructor
		MethodVisitor mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
		mv.visitCode();
		mv.visitVarInsn(ALOAD, 0); //load the first local variable: this
		mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
		mv.visitInsn(RETURN);
		mv.visitMaxs(1,1); // Slots na stack e nro de argumentos
		mv.visitEnd();		
	}
	
	public BCFunctionWriter createFunction(String name, BCType returnType, BCType ... argType) {
		return new BCFunctionWriter(this, name, returnType, argType).create();
	}
	
	public BCMethodWriter createMethod(String name, BCType ... argType) {		
		return new BCMethodWriter(this, name, argType).create();
		
		/*
		//"([Ljava/lang/String;)V"
		cw.visitMethod(ACC_PUBLIC, "metodo", "()V", null, null);
		
		
		
		
		

		cw.visitCode();
		
		cw.visitFieldInsn(GETSTATIC,"java/lang/System", "out", "Ljava/io/PrintStream;"); //put System.out to operand stack
		cw.visitLdcInsn("Hello"); //load const "Hello" from const_pool, and put onto the operand stack		
		
		cw.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
		cw.visitInsn(RETURN);
		cw.visitMaxs(2,1);
		
		
		methodWriter.visitEnd();
		*/
		
	}
	
	
	public String getPackageName() {
		return packageName;
	}
	
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getClassName() {
		return className;
	}

	private void setClassName(String className) {
		this.className = className;
	}

	public BCClassWriter addAttribute(String identifier, String type) {
		return this;
	}	
	
	public byte[] buildClassToByteArray() {
		cw.visitEnd();		
		return cw.toByteArray();		
	}
	
	public void buildClassToFile(String path) throws IOException {		
		FileOutputStream out = new FileOutputStream(path + "/" + this.getClassName() + ".class");
		out.write(buildClassToByteArray());
		out.close();			
	}
	
	
	
	

	protected ClassWriter getCw() {
		return cw;
	}
	
	
}
