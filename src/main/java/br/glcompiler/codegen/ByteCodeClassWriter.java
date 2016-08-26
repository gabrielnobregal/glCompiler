package br.glcompiler.codegen;

import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.ACC_SUPER;
import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.INVOKESPECIAL;
import static org.objectweb.asm.Opcodes.RETURN;
import static org.objectweb.asm.Opcodes.V1_7;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import br.glcompiler.semantic.Obj;

public class ByteCodeClassWriter {
	
	private ClassWriter cw;
	
	private ByteCodeClassWriter(String className) {
		
	}
	
	private ByteCodeClassWriter setupSimpleClass(String className) {
		// Create class
		cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);		
		cw.visit(V1_7, ACC_PUBLIC+ACC_SUPER, "sample/" + className, null, "java/lang/Object", null);
		
		// Create default constructor
		MethodVisitor mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
		mv.visitCode();
		mv.visitVarInsn(ALOAD, 0); //load the first local variable: this
		mv.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V");
		mv.visitInsn(RETURN);
		mv.visitMaxs(1,1); // Slots na stack e nro de argumentos
		mv.visitEnd();
		
		return this;
	}
	
	
	public static ByteCodeClassWriter createClass(String className) {		
		ByteCodeClassWriter bClassWriter = new ByteCodeClassWriter(className);		
		return bClassWriter.setupSimpleClass(className);
	}
	
	
	
}
