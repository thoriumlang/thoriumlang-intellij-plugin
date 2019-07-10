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
package org.thoriumlang.intellij.plugin.psi;

import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.IncorrectOperationException;
import org.antlr.intellij.adaptor.psi.ANTLRPsiLeafNode;
import org.antlr.intellij.adaptor.psi.Trees;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.thoriumlang.intellij.plugin.ThoriumLanguage;
import org.thoriumlang.intellij.plugin.ThoriumParserDefinition;

public class IdentifierPSINode extends ANTLRPsiLeafNode implements PsiNamedElement {
    public IdentifierPSINode(IElementType type, CharSequence text) {
        super(type, text);
    }

    @Override
    public String getName() {
        return getText();
    }

    @Override
    public PsiElement setName(@NonNls @NotNull String name) throws IncorrectOperationException {
        if (getParent() == null) {
            return this;
        }

        PsiElement newID = Trees.createLeafFromText(
                getProject(),
                ThoriumLanguage.INSTANCE,
                getContext(),
                name,
                ThoriumParserDefinition.IDENTIFIER
        );

        if (newID != null) {
            return this.replace(newID);
        }

        return this;
    }

    @Override
    public PsiReference getReference() {
        return null;
    }
}
