package br.glcompiler.message;

public enum MessageType {
	
	// Message prefixes
	ERROR_PREFIX,
	WARNING_PREFIX,
	INFO_PREFIX,

	
	// Generic messages
	EXPECTED_SYMBOL,
	EXPECTED_BEGIN_SYMBOL,
	EXPECTED_END_SYMBOL,
	EXPECTED_IDENTIFIER,
	
	// Specific messages
	NO_CLASS_DECLARED,
	UNEXPECTED_END_OF_FILE
	
	
}
