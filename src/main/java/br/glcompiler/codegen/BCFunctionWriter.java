package br.glcompiler.codegen;

import static org.objectweb.asm.Opcodes.ACC_PUBLIC;

import java.util.List;

import org.objectweb.asm.MethodVisitor;

public class BCFunctionWriter extends BCMethodWriter {
	
	private BCType returnType;
	
	protected BCFunctionWriter(BCClassWriter classWriter, String name, BCType returnType, BCType ... args) {
		super(classWriter, name, args);
		this.returnType = returnType;
	}	

	@Override
	protected BCFunctionWriter create() {
		MethodVisitor mw = getMw();		
		super.setMw(getClassWriter().getCw().visitMethod(ACC_PUBLIC, super.getName(), functionSignature(getName(),returnType, getArgsList()), null, null));		
		mw.visitCode();
		return this;
	}
	
	private String functionSignature(String name, BCType returnType, List<BCType> args) {
		return "()V";
	}	
	

}
