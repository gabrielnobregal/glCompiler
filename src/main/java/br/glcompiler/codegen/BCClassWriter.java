package br.glcompiler.codegen;

import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.ACC_SUPER;
import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.INVOKESPECIAL;
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
	
	protected BCClassWriter(String className) {
		setClassName(className);
		setupSimpleClass(className);
	}
	
	private void setupSimpleClass(String className) {
		// Create class
		cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);		
		cw.visit(V1_7, ACC_PUBLIC+ACC_SUPER, "default/" + className, null, "java/lang/Object", null);
		
		// Create default constructor
		MethodVisitor mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
		mv.visitCode();
		mv.visitVarInsn(ALOAD, 0); //load the first local variable: this
		mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
		mv.visitInsn(RETURN);
		mv.visitMaxs(1,1); // Slots na stack e nro de argumentos
		mv.visitEnd();		
	}
	
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
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

	protected void setCw(ClassWriter cw) {
		this.cw = cw;
	}
	
}
