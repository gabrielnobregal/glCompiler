package br.glcompiler.message;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import br.glcompiler.lex.Localization;

public class CompilerMessageI18N implements CompilerMessage {
	private ResourceBundle messages;
	
	public CompilerMessageI18N(String language, String country) {
		messages = ResourceBundle.getBundle("messages", new Locale(language, country));
	}
	
	private String getMessage(MessageType messageType, String ... parameters) {
		return MessageFormat.format(messages.getString(messageType.name()), parameters);
	}
	
	public String getErrorMessage(MessageType messageType, Localization localization, String ... parameters) {
		String errorPrefix = MessageFormat.format(messages.getString(MessageType.ERROR_PREFIX.name()), localization.getLine(), localization.getColumn());		
		return errorPrefix + getMessage(messageType, parameters);			
	}
	
	public String getWarningMessage(MessageType messageType, Localization localization, String ... parameters) {
		String warnPrefix = MessageFormat.format(messages.getString(MessageType.WARNING_PREFIX.name()), localization.getLine(), localization.getColumn());		
		return warnPrefix + getMessage(messageType, parameters);			
	}	
	
	public String getInfoMessage(MessageType messageType, Localization localization, String ... parameters) {
		String infoPrefix = MessageFormat.format(messages.getString(MessageType.INFO_PREFIX.name()), localization.getLine(), localization.getColumn());		
		return infoPrefix + getMessage(messageType, parameters);			
	}
}
