package st.redline.smalltalk.parser;

import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

/**
 * Created with IntelliJ IDEA.
 * User: maya
 * Date: 6/6/12
 * Time: 9:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class RedlineParserDefinition implements ParserDefinition {
    @NotNull
    @Override
    public Lexer createLexer(Project project) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public PsiParser createParser(Project project) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public IFileElementType getFileNodeType() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @NotNull
    @Override
    public TokenSet getWhitespaceTokens() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @NotNull
    @Override
    public TokenSet getCommentTokens() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @NotNull
    @Override
    public TokenSet getStringLiteralElements() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @NotNull
    @Override
    public PsiElement createElement(ASTNode astNode) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public PsiFile createFile(FileViewProvider fileViewProvider) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode astNode, ASTNode astNode1) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
