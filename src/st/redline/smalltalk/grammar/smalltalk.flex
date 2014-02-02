package st.redline.stride.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;
import st.redline.stride.parser.RedlineElementTypes;

%%

%public
%class RedlineLexer
%implements RedlineElementTypes, FlexLexer
%unicode

/* Special code for integration with IntelliJ lexer */
%function advance
%type IElementType

%eof{ return;
%eof}

// Lifted off clojure.flex
%{
  /*
  public final int getTokenStart(){
    return zzStartRead;
  }

  public final int getTokenEnd(){
    return getTokenStart() + yylength();
  }

  public void reset(CharSequence buffer, int start, int end,int initialState) {
    char [] buf = buffer.toString().substring(start,end).toCharArray();
    yyreset( new CharArrayReader( buf ) );
    yybegin(initialState);
  }

  public void reset(CharSequence buffer, int initialState){
    reset(buffer, 0, buffer.length(), initialState);
  }
  */
%}

WHITESPACE = ([ ] | \r | \n | \r\n | \t | \f)+

LETTER = [A-Za-z]

DIGIT = [0-9]

IDENTIFIER = {LETTER} ({LETTER} | {DIGIT})*

COMMENT = \" .* \"

STRING = ' ([^'] | '')* '

%%

<YYINITIAL>{
  {COMMENT}                              {  return COMMENT; }
  {STRING}                               {  return STRING; }
  {WHITESPACE}                           {  return WHITE_SPACE; }
  {DIGIT}+                               {  return DIGITS; }
  {IDENTIFIER}:                          {  return KEYWORD; }
  {IDENTIFIER}                           {  return IDENTIFIER; }
}

.                                        { return TokenType.BAD_CHARACTER; }