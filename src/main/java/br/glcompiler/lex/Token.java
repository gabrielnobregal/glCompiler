package br.glcompiler.lex;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

public class Token {
	
	private Kind kind;
	private String lexeme;	
	private Localization localization;		
	
	private static enum LexGroup {
		BASIC, KEYWORD, OPERATOR
	}
	
	public static enum Kind {
		// basic
        UNKNOWN("na", LexGroup.BASIC), EOF("\u001a", LexGroup.BASIC), IDENTIFIER("identifier", LexGroup.BASIC), NUMBER("literal_number", LexGroup.BASIC), 
        CHARACTER("literal_character", LexGroup.BASIC),STRING("literal_string", LexGroup.BASIC),
        
        // keywords
        CLASS("classe", LexGroup.KEYWORD), ELSE("senao", LexGroup.KEYWORD), IF("se", LexGroup.KEYWORD), INSTANTIATE("instanciar", LexGroup.KEYWORD), 
        RETURN("retornar", LexGroup.KEYWORD), WHILE("enquanto", LexGroup.KEYWORD), IMPORT("importar", LexGroup.KEYWORD),
        INTEGER("inteiro", LexGroup.KEYWORD), TEXT("texto", LexGroup.KEYWORD), DOUBLE("real", LexGroup.KEYWORD),
        METHOD("metodo", LexGroup.KEYWORD), FUNCTION("funcao", LexGroup.KEYWORD),
        
        // operators
        PLUS("+", LexGroup.OPERATOR), MINUS("-", LexGroup.OPERATOR), TIMES("*", LexGroup.OPERATOR), SLASH("/", LexGroup.OPERATOR), 
        REMAINDER("\\", LexGroup.OPERATOR), EQUAL("==", LexGroup.OPERATOR), NOT_EQUAL("!=", LexGroup.OPERATOR), 
        LESS("<", LexGroup.OPERATOR), LESS_EQUAL("<=", LexGroup.OPERATOR), GREATER(">", LexGroup.OPERATOR), 
        GREATER_EQUAL(">=", LexGroup.OPERATOR), ASSIGN("=", LexGroup.OPERATOR), SEMICOLON(";", LexGroup.OPERATOR), COMMA(",", LexGroup.OPERATOR),
        PERIOD(".", LexGroup.OPERATOR), LEFT_PARENTHESIS("(", LexGroup.OPERATOR), RIGHT_PARENTHESIS(")", LexGroup.OPERATOR), 
        LEFT_BRACKET("[", LexGroup.OPERATOR), RIGHT_BRACKET("]", LexGroup.OPERATOR), LEFT_BRACE("{", LexGroup.OPERATOR), 
        RIGHT_BRACE("}", LexGroup.OPERATOR), DOUBLE_QUOTES("\"", LexGroup.OPERATOR), COLON(":", LexGroup.OPERATOR);
		
		private String value;
		private LexGroup group;
		
		private Kind(String value, LexGroup group) {
			this.value = value;
			this.group = group;
		}
		
		public String getValue() {
			return this.value;
		}
		
		public LexGroup getGroup() {
			return group;
		}
		
		public boolean isPrimitiveType() {			
			return this == Kind.INTEGER || this == Kind.TEXT || this == Kind.DOUBLE;		  
		}
		
		public boolean isExecutionUnit() {			
			return this == Kind.METHOD || this == Kind.FUNCTION;	  
		}
		
    }
	
	private final static Map<String, Kind> basicMap = new HashMap<>();
	private final static Map<String, Kind> keywordMap = new HashMap<>();
	private final static Map<String, Kind> operatorMap = new HashMap<>();
		
	static {
		constructMap(basicMap, LexGroup.BASIC);
		constructMap(keywordMap, LexGroup.KEYWORD);
		constructMap(operatorMap, LexGroup.OPERATOR);	
	}	
	
	public Token() {
		
	}		

	public Token(Kind kind, String lexeme, Localization localization) {
		super();
		this.kind = kind;
		this.lexeme = lexeme;
		this.localization = localization;
	}	

	public Token(Kind kind, Localization localization) {
		super();
		this.kind = kind;
		this.localization = localization;
	}

	public Token(Localization localization) {
		this.localization = localization;
	}	

	public String getLexeme() {
		return lexeme;
	}

	public void setLexeme(String lexeme) {
		this.lexeme = lexeme;
	}

	public Kind getKind() {
		return kind;
	}

	public void setKind(Kind kind) {
		this.kind = kind;
	}	
	
	public Localization getLocalization() {
		return localization;
	}

	public void setLocalization(Localization localization) {
		this.localization = localization;
	}

	public static boolean isPrimitiveType(Token token) {
		Kind tKind = token.getKind();
		return tKind == Kind.INTEGER || tKind == Kind.TEXT || tKind == Kind.DOUBLE;		  
	}
	
	public static Kind getKeywordKind(String lexeme) {
		return keywordMap.containsKey(lexeme) ? keywordMap.get(lexeme) : Kind.IDENTIFIER; 
	}
	
	public static Kind getOperatorKind(String lexeme) {
		Kind k = operatorMap.get(lexeme);
		return  k == null ? Kind.UNKNOWN : k;
	}
	
	public static Kind getBasicKind(String lexeme) {
		Kind k = basicMap.get(lexeme);
		return  k == null ? Kind.UNKNOWN : k; 
	}
	
	public static Kind getKind(String lexeme) {
		return Stream.of(keywordMap.get(lexeme), operatorMap.get(lexeme), basicMap.get(lexeme)).
			   filter(Objects::nonNull).findFirst().orElse(Kind.UNKNOWN);
	}	
	
	public static boolean hasDoubleCharOperatorPrefix(char c) {
		return c == '=' || c == '<' || c == '>' || c == '!';
	}
	
	private static void constructMap(Map<String, Kind> map, LexGroup group) {		
		for(Kind k : Kind.values()) {
			if(k.getGroup() == group) {
				map.put(k.getValue(), k);
			}
		}
	}
	
	@Override
	public String toString() {
		return "{" +this.kind.name() + ","  + this.kind.value + "," + this.lexeme + "}";		
	}
	
}
