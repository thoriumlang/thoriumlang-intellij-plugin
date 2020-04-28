package org.thoriumlang.intellij.plugin.antlr4;

import org.thoriumlang.compiler.antlr.ThoriumParser;

public class ThoriumParserFactory {
    public static ThoriumParser newInstance () {
        return new ThoriumParser(null);

    }
}
