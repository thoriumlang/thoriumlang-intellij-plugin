package org.thoriumlang.intellij.plugin.antlr4;

import org.thoriumlang.compiler.antlr4.LexerConfiguration;

public class IntellijLexerConfiguration implements LexerConfiguration {
    @Override
    public boolean keepAllTokens() {
        return true; //
    }
}
