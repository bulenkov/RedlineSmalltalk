// This is a generated file. Not intended for manual editing.
package st.redline.smalltalk.parser;

import org.jetbrains.annotations.*;
import com.intellij.lang.LighterASTNode;
import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import com.intellij.openapi.diagnostic.Logger;
import static st.redline.smalltalk.psi.SmalltalkTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class SmalltalkParser implements PsiParser {

  public static Logger LOG_ = Logger.getInstance("st.redline.stride.parser.SmalltalkParser");

  @NotNull
  public ASTNode parse(IElementType root_, PsiBuilder builder_) {
    int level_ = 0;
    boolean result_;
    builder_ = adapt_builder_(root_, builder_, this);
    if (root_ == ST_ADD_EXPRESSION) {
      result_ = add_expression(builder_, level_ + 1);
    }
    else if (root_ == ST_EXPRESSION) {
      result_ = expression(builder_, level_ + 1);
    }
    else if (root_ == ST_IDENTIFIER) {
      result_ = identifier(builder_, level_ + 1);
    }
    else if (root_ == ST_LITERAL_EXPRESSION) {
      result_ = literal_expression(builder_, level_ + 1);
    }
    else if (root_ == ST_MUL_EXPRESSION) {
      result_ = mul_expression(builder_, level_ + 1);
    }
    else if (root_ == ST_REFERENCE_EXPRESSION) {
      result_ = reference_expression(builder_, level_ + 1);
    }
    else {
      Marker marker_ = builder_.mark();
      enterErrorRecordingSection(builder_, level_, _SECTION_RECOVER_, null);
      result_ = parse_root_(root_, builder_, level_);
      exitErrorRecordingSection(builder_, level_, result_, true, _SECTION_RECOVER_, TOKEN_ADVANCER);
      marker_.done(root_);
    }
    return builder_.getTreeBuilt();
  }

  protected boolean parse_root_(final IElementType root_, final PsiBuilder builder_, final int level_) {
    return root(builder_, level_ + 1);
  }

  private static final TokenSet[] EXTENDS_SETS_ = new TokenSet[] {
    TokenSet.create(ST_ADD_EXPRESSION, ST_EXPRESSION, ST_LITERAL_EXPRESSION, ST_MUL_EXPRESSION,
      ST_REFERENCE_EXPRESSION),
  };

  public static boolean type_extends_(IElementType child_, IElementType parent_) {
    for (TokenSet set : EXTENDS_SETS_) {
      if (set.contains(child_) && set.contains(parent_)) return true;
    }
    return false;
  }

  /* ********************************************************** */
  // '+' factor
  public static boolean add_expression(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "add_expression")) return false;
    if (!nextTokenIs(builder_, ST_ADD)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker left_marker_ = (Marker)builder_.getLatestDoneMarker();
    if (!invalid_left_marker_guard_(builder_, left_marker_, "add_expression")) return false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = consumeToken(builder_, ST_ADD);
    pinned_ = result_; // pin = 1
    result_ = result_ && factor(builder_, level_ + 1);
    if (result_ || pinned_) {
      marker_.drop();
      left_marker_.precede().done(ST_ADD_EXPRESSION);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // factor add_expression *
  public static boolean expression(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "expression")) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    int start_ = builder_.getCurrentOffset();
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_RECOVER_, "<expression>");
    result_ = factor(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    result_ = result_ && expression_1(builder_, level_ + 1);
    LighterASTNode last_ = result_? builder_.getLatestDoneMarker() : null;
    if (last_ != null && last_.getStartOffset() == start_ && type_extends_(last_.getTokenType(), ST_EXPRESSION)) {
      marker_.drop();
    }
    else if (result_ || pinned_) {
      marker_.done(ST_EXPRESSION);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_RECOVER_, recover_parser_);
    return result_ || pinned_;
  }

  // add_expression *
  private static boolean expression_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "expression_1")) return false;
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!add_expression(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "expression_1");
        break;
      }
      offset_ = next_offset_;
    }
    return true;
  }

  /* ********************************************************** */
  // primary mul_expression *
  static boolean factor(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "factor")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = primary(builder_, level_ + 1);
    result_ = result_ && factor_1(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // mul_expression *
  private static boolean factor_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "factor_1")) return false;
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!mul_expression(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "factor_1");
        break;
      }
      offset_ = next_offset_;
    }
    return true;
  }

  /* ********************************************************** */
  // id
  public static boolean identifier(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "identifier")) return false;
    if (!nextTokenIs(builder_, ST_ID)) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, ST_ID);
    if (result_) {
      marker_.done(ST_IDENTIFIER);
    }
    else {
      marker_.rollbackTo();
    }
    return result_;
  }

  /* ********************************************************** */
  // number | string
  public static boolean literal_expression(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "literal_expression")) return false;
    if (!nextTokenIs(builder_, ST_NUMBER) && !nextTokenIs(builder_, ST_STRING)
        && replaceVariants(builder_, 2, "<literal expression>")) return false;
    boolean result_ = false;
    int start_ = builder_.getCurrentOffset();
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, "<literal expression>");
    result_ = consumeToken(builder_, ST_NUMBER);
    if (!result_) result_ = consumeToken(builder_, ST_STRING);
    LighterASTNode last_ = result_? builder_.getLatestDoneMarker() : null;
    if (last_ != null && last_.getStartOffset() == start_ && type_extends_(last_.getTokenType(), ST_LITERAL_EXPRESSION)) {
      marker_.drop();
    }
    else if (result_) {
      marker_.done(ST_LITERAL_EXPRESSION);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_GENERAL_, null);
    return result_;
  }

  /* ********************************************************** */
  // '*' primary
  public static boolean mul_expression(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "mul_expression")) return false;
    if (!nextTokenIs(builder_, ST_MUL)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker left_marker_ = (Marker)builder_.getLatestDoneMarker();
    if (!invalid_left_marker_guard_(builder_, left_marker_, "mul_expression")) return false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = consumeToken(builder_, ST_MUL);
    pinned_ = result_; // pin = 1
    result_ = result_ && primary(builder_, level_ + 1);
    if (result_ || pinned_) {
      marker_.drop();
      left_marker_.precede().done(ST_MUL_EXPRESSION);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // literal_expression | reference_expression qreference_expression *
  static boolean primary(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "primary")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = literal_expression(builder_, level_ + 1);
    if (!result_) result_ = primary_1(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // reference_expression qreference_expression *
  private static boolean primary_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "primary_1")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = reference_expression(builder_, level_ + 1);
    result_ = result_ && primary_1_1(builder_, level_ + 1);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  // qreference_expression *
  private static boolean primary_1_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "primary_1_1")) return false;
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!qreference_expression(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "primary_1_1");
        break;
      }
      offset_ = next_offset_;
    }
    return true;
  }

  /* ********************************************************** */
  // '.' identifier
  public static boolean qreference_expression(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "qreference_expression")) return false;
    if (!nextTokenIs(builder_, ST_DOT)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker left_marker_ = (Marker)builder_.getLatestDoneMarker();
    if (!invalid_left_marker_guard_(builder_, left_marker_, "qreference_expression")) return false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = consumeToken(builder_, ST_DOT);
    pinned_ = result_; // pin = 1
    result_ = result_ && identifier(builder_, level_ + 1);
    if (result_ || pinned_) {
      marker_.drop();
      left_marker_.precede().done(ST_REFERENCE_EXPRESSION);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // !(number | string | id)
  static boolean recover(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "recover")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_NOT_, null);
    result_ = !recover_0(builder_, level_ + 1);
    marker_.rollbackTo();
    result_ = exitErrorRecordingSection(builder_, level_, result_, false, _SECTION_NOT_, null);
    return result_;
  }

  // number | string | id
  private static boolean recover_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "recover_0")) return false;
    boolean result_ = false;
    Marker marker_ = builder_.mark();
    result_ = consumeToken(builder_, ST_NUMBER);
    if (!result_) result_ = consumeToken(builder_, ST_STRING);
    if (!result_) result_ = consumeToken(builder_, ST_ID);
    if (!result_) {
      marker_.rollbackTo();
    }
    else {
      marker_.drop();
    }
    return result_;
  }

  /* ********************************************************** */
  // identifier
  public static boolean reference_expression(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "reference_expression")) return false;
    if (!nextTokenIs(builder_, ST_ID)) return false;
    boolean result_ = false;
    boolean pinned_ = false;
    Marker marker_ = builder_.mark();
    enterErrorRecordingSection(builder_, level_, _SECTION_GENERAL_, null);
    result_ = identifier(builder_, level_ + 1);
    pinned_ = result_; // pin = 1
    if (result_ || pinned_) {
      marker_.done(ST_REFERENCE_EXPRESSION);
    }
    else {
      marker_.rollbackTo();
    }
    result_ = exitErrorRecordingSection(builder_, level_, result_, pinned_, _SECTION_GENERAL_, null);
    return result_ || pinned_;
  }

  /* ********************************************************** */
  // expression *
  static boolean root(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "root")) return false;
    int offset_ = builder_.getCurrentOffset();
    while (true) {
      if (!expression(builder_, level_ + 1)) break;
      int next_offset_ = builder_.getCurrentOffset();
      if (offset_ == next_offset_) {
        empty_element_parsed_guard_(builder_, offset_, "root");
        break;
      }
      offset_ = next_offset_;
    }
    return true;
  }

  final static Parser recover_parser_ = new Parser() {
    public boolean parse(PsiBuilder builder_, int level_) {
      return recover(builder_, level_ + 1);
    }
  };
}
