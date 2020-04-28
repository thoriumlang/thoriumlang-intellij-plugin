/*
 * Copyright 2019 Christophe Pollet
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.thoriumlang.intellij.plugin;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.psi.tree.IElementType;
import org.antlr.intellij.adaptor.lexer.TokenIElementType;
import org.jetbrains.annotations.NotNull;
import org.thoriumlang.compiler.antlr.ThoriumLexer;
import org.thoriumlang.intellij.plugin.antlr4.ANTLRLexerAdaptorFactory;
import org.thoriumlang.intellij.plugin.antlr4.Tokens;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class ThoriumSyntaxHighlighter implements SyntaxHighlighter {
    static final TextAttributesKey IDENTIFIER = createTextAttributesKey(
            "THORIUM_IDENTIFIER",
            DefaultLanguageHighlighterColors.IDENTIFIER
    );

    static final TextAttributesKey METHOD_DECLARATION = createTextAttributesKey(
            "THORIUM_METHOD_DECLARATION",
            DefaultLanguageHighlighterColors.FUNCTION_DECLARATION
    );

    static final TextAttributesKey KEYWORD = createTextAttributesKey(
            "THORIUM_KEYWORD",
            DefaultLanguageHighlighterColors.KEYWORD
    );

    static final TextAttributesKey STRING = createTextAttributesKey(
            "THORIUM_STRING",
            DefaultLanguageHighlighterColors.STRING
    );

    static final TextAttributesKey LINE_COMMENT = createTextAttributesKey(
            "THORIUM_LINE_COMMENT",
            DefaultLanguageHighlighterColors.LINE_COMMENT
    );
    static final TextAttributesKey BLOCK_COMMENT = createTextAttributesKey(
            "THORIUM_BLOCK_COMMENT",
            DefaultLanguageHighlighterColors.BLOCK_COMMENT
    );

    static final TextAttributesKey NUMBER = createTextAttributesKey(
            "THORIUM_NUMBER",
            DefaultLanguageHighlighterColors.NUMBER
    );

    static final TextAttributesKey PUNCTUATION = createTextAttributesKey(
            "THORIUM_PUNCTUATION",
            DefaultLanguageHighlighterColors.KEYWORD
    );

    private static final TextAttributesKey[] EMPTY_KEYS =
            new TextAttributesKey[0];

    private static final int SEMICOLON = Tokens.INSTANCE.tokenId(";");
    private static final int COLON = Tokens.INSTANCE.tokenId(":");
    private static final int QUESTION_MARK = Tokens.INSTANCE.tokenId("?");

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return ANTLRLexerAdaptorFactory.newInstance();
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        if (!(tokenType instanceof TokenIElementType)) {
            return EMPTY_KEYS;
        }

        TextAttributesKey attrKey;
        int antlrTokenType = ((TokenIElementType) tokenType).getANTLRTokenType();
        switch (antlrTokenType) {
            case ThoriumLexer.CLASS:
            case ThoriumLexer.TYPE:
            case ThoriumLexer.USE:
            case ThoriumLexer.PRIVATE:
            case ThoriumLexer.PUBLIC:
            case ThoriumLexer.NAMESPACE:
            case ThoriumLexer.VAL:
            case ThoriumLexer.VAR:
            case ThoriumLexer.TRUE:
            case ThoriumLexer.FALSE:
            case ThoriumLexer.NONE:
            case ThoriumLexer.THIS:
            case ThoriumLexer.RETURN:
                attrKey = KEYWORD;
                break;
            case ThoriumLexer.IDENTIFIER:
                attrKey = IDENTIFIER;
                break;
            case ThoriumLexer.STRING:
                attrKey = STRING;
                break;
            case ThoriumLexer.NUMBER:
                attrKey = NUMBER;
                break;
            case ThoriumLexer.LINE_COMMENT:
                attrKey = LINE_COMMENT;
                break;
            case ThoriumLexer.BLOCK_COMMENT:
                attrKey = BLOCK_COMMENT;
                break;
            default:
                if (antlrTokenType == SEMICOLON || antlrTokenType == COLON || antlrTokenType == QUESTION_MARK) {
                    attrKey = PUNCTUATION;
                } else {
                    return EMPTY_KEYS;
                }
        }

        return new TextAttributesKey[]{attrKey};
    }
}
