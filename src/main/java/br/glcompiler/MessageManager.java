package br.glcompiler;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import br.glcompiler.lex.Localization;

public class MessageManager {
	private ResourceBundle messages;
	
	public MessageManager(String language, String country) {
		messages = ResourceBundle.getBundle("messages", new Locale(language, country));
	}
	
	public String getMessage(MessageType messageType, String ... parameters) {
		return MessageFormat.format(messages.getString(messageType.name()), parameters);
	}
	
	public String getErrorMessage(MessageType messageType, Localization localization, String ... parameters) {
		String errorPrefix = MessageFormat.format(messages.getString(MessageType.ERROR_PREFIX.name()), localization.getLine(), localization.getColumn());		
		return errorPrefix + getMessage(messageType, parameters);			
	}
	
}
