package br.glcompiler.message;

import br.glcompiler.lex.Localization;

public interface CompilerMessage {
	
	public abstract String getErrorMessage(MessageType messageType, Localization localization, String ... parameters);	
	public abstract String getWarningMessage(MessageType messageType, Localization localization, String ... parameters);	
	public abstract String getInfoMessage(MessageType messageType, Localization localization, String ... parameters);
}
