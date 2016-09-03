package br.glcompiler.codegen;

import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.ACC_STATIC;
import static org.objectweb.asm.Opcodes.GETSTATIC;
import static org.objectweb.asm.Opcodes.INVOKEVIRTUAL;
import static org.objectweb.asm.Opcodes.RETURN;

import java.util.Arrays;
import java.util.List;

import org.objectweb.asm.MethodVisitor;

public class BCMethodWriter {
	private BCClassWriter classWriter;
	private MethodVisitor mw;
	
	private String name;
	private List<BCType> argsList;	
			
			
	protected BCMethodWriter(BCClassWriter classWriter, String name, BCType ... args) {
		this.classWriter = classWriter;			
		this.name = name;
		argsList = Arrays.asList(args);
	}
	
	protected BCMethodWriter create() {
		mw = classWriter.getCw().visitMethod(ACC_PUBLIC, name, methodSignature(argsList), null, null);
		mw.visitCode();
		return this;
	}
	
	public void build() {		
		mw.visitInsn(RETURN);
		mw.visitMaxs(2,1);
		mw.visitEnd();		
	}	
	
	public String getName() {
		return name;
	}	

	public List<BCType> getArgsList() {
		return argsList;
	}	
	
	protected MethodVisitor getMw() {
		return mw;
	}
	
	protected void setMw(MethodVisitor mw) {
		this.mw = mw;
	}
	
	protected BCClassWriter getClassWriter() {
		return classWriter;
	}	
	
	private String methodSignature(List<BCType> argsList) {
		return "()V"; //TODO: converts argument list into bytecode type
	}	
	
}
