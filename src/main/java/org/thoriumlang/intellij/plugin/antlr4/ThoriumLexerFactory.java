package org.thoriumlang.intellij.plugin.antlr4;

import org.thoriumlang.compiler.antlr.ThoriumLexer;

public class ThoriumLexerFactory {
    public static ThoriumLexer newInstance() {
        return new ThoriumLexer(null, new IntellijLexerConfiguration());
    }
}
