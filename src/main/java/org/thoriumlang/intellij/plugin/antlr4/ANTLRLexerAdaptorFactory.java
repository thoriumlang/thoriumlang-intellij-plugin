package org.thoriumlang.intellij.plugin.antlr4;

import org.antlr.intellij.adaptor.lexer.ANTLRLexerAdaptor;
import org.thoriumlang.intellij.plugin.ThoriumLanguage;

public class ANTLRLexerAdaptorFactory {
    public static ANTLRLexerAdaptor newInstance() {
        return new ANTLRLexerAdaptor(ThoriumLanguage.INSTANCE, ThoriumLexerFactory.newInstance());
    }
}
