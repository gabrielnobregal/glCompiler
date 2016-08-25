package br.glcompiler.codegen;

import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.ACC_SUPER;
import static org.objectweb.asm.Opcodes.V1_7;

import org.objectweb.asm.ClassWriter;

import br.glcompiler.semantic.Obj;

public class CodeGenerator {
	
	private ClassWriter cw;
	
	public void createClass(Obj object) {
		
		cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES); 		
		//Generics
		cw.visit(V1_7, ACC_PUBLIC+ACC_SUPER, "sample/HelloGen", null, "java/lang/Object", null);
		
	}
	
}
