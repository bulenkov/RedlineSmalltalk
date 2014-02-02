package st.redline.smalltalk.highlighter;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.editor.SyntaxHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;
import st.redline.smalltalk.lexer.RedlineFlexLexer;
import st.redline.smalltalk.parser.RedlineElementTypes;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: maya
 * Date: 6/3/12
 * Time: 10:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class SmalltalkSyntaxHighlighter extends SyntaxHighlighterBase {
    private static final Map<IElementType, TextAttributesKey> ATTRIBUTES = new HashMap<IElementType, TextAttributesKey>();

    private Logger logger = Logger.getInstance(SmalltalkSyntaxHighlighter.class);

    static final TokenSet sCOMMENT = TokenSet.create(
            RedlineElementTypes.COMMENT
    );

    static final TokenSet sSTRING = TokenSet.create(
            RedlineElementTypes.STRING
    );

    static final TokenSet sNUMBERS = TokenSet.create(
            RedlineElementTypes.DIGITS
    );

    static final TokenSet sIDENTIFIER = TokenSet.create(
            RedlineElementTypes.IDENTIFIER
    );

    static final TokenSet sKEYWORD = TokenSet.create(
            RedlineElementTypes.KEYWORD
    );

    // Is this necessary?
    static final TokenSet sBAD_CHARACTERS = TokenSet.create(
            TokenType.BAD_CHARACTER
    );

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new RedlineFlexLexer();
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType iElementType) {
        logger.info("getTokenHighlights");
        return pack(ATTRIBUTES.get(iElementType));
    }

    // Registering TextAttributes
    static final String STRING_ID = "String";
    static final String COMMENT_ID = "Comment";
    static final String NUMBER_ID = "Numbers";
    static final String IDENTIFIER_ID = "Identifiers";
    static final String KEYWORD_ID = "Keyword";

    // Is this necessary?
    static final String BAD_CHARACTER_ID = "Bad character";

    static {
        TextAttributesKey.createTextAttributesKey(COMMENT_ID, SyntaxHighlighterColors.DOC_COMMENT.getDefaultAttributes());

        TextAttributesKey.createTextAttributesKey(STRING_ID, SyntaxHighlighterColors.STRING.getDefaultAttributes());

        TextAttributesKey.createTextAttributesKey(NUMBER_ID, SyntaxHighlighterColors.NUMBER.getDefaultAttributes());

        // DOC_COMMENT_TAG until we get a better default
        TextAttributesKey.createTextAttributesKey(IDENTIFIER_ID, SyntaxHighlighterColors.DOC_COMMENT_TAG.getDefaultAttributes());

        TextAttributesKey.createTextAttributesKey(KEYWORD_ID, SyntaxHighlighterColors.KEYWORD.getDefaultAttributes());
    }

    public static TextAttributesKey COMMENT = TextAttributesKey.createTextAttributesKey(COMMENT_ID);
    public static TextAttributesKey STRING = TextAttributesKey.createTextAttributesKey(STRING_ID);
    public static TextAttributesKey NUMBER = TextAttributesKey.createTextAttributesKey(NUMBER_ID);
    public static TextAttributesKey IDENTIFIER = TextAttributesKey.createTextAttributesKey(IDENTIFIER_ID);
    public static TextAttributesKey KEYWORD = TextAttributesKey.createTextAttributesKey(KEYWORD_ID);

    static {
        fillMap(ATTRIBUTES, sCOMMENT, COMMENT);
        fillMap(ATTRIBUTES, sSTRING, STRING);
        fillMap(ATTRIBUTES, sNUMBERS, NUMBER);
        fillMap(ATTRIBUTES, sIDENTIFIER, IDENTIFIER);
        fillMap(ATTRIBUTES, sKEYWORD, KEYWORD);
    }
}
