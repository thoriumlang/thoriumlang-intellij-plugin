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

import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IElementType;
import org.antlr.intellij.adaptor.lexer.PSIElementTypeFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.thoriumlang.intellij.plugin.antlr4.Tokens;

public class ThoriumPairedBraceMatcher implements PairedBraceMatcher {
    private static final Tokens tokens = Tokens.INSTANCE;

    @NotNull
    @Override
    public BracePair[] getPairs() {
        return new BracePair[]{
                new BracePair(
                        PSIElementTypeFactory
                                .getTokenIElementTypes(ThoriumLanguage.INSTANCE)
                                .get(tokens.tokenId("(")),
                        PSIElementTypeFactory
                                .getTokenIElementTypes(ThoriumLanguage.INSTANCE)
                                .get(tokens.tokenId(")")),
                        false
                ),
                new BracePair(
                        PSIElementTypeFactory
                                .getTokenIElementTypes(ThoriumLanguage.INSTANCE)
                                .get(tokens.tokenId("{")),
                        PSIElementTypeFactory
                                .getTokenIElementTypes(ThoriumLanguage.INSTANCE)
                                .get(tokens.tokenId("}")),
                        true
                )
        };
    }

    @Override
    public boolean isPairedBracesAllowedBeforeType(@NotNull IElementType lbraceType,
            @Nullable IElementType contextType) {
        return true;
    }

    @Override
    public int getCodeConstructStart(PsiFile file, int openingBraceOffset) {
        return openingBraceOffset;
    }
}
