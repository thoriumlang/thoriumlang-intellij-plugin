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

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import org.antlr.intellij.adaptor.SymtabUtils;
import org.antlr.intellij.adaptor.psi.ScopeNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.thoriumlang.intellij.plugin.ThoriumFileType;
import org.thoriumlang.intellij.plugin.ThoriumIcons;
import org.thoriumlang.intellij.plugin.ThoriumLanguage;

import javax.swing.*;

public class FileRoot extends PsiFileBase implements ScopeNode {
    public FileRoot(FileViewProvider viewProvider) {
        super(viewProvider, ThoriumLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return ThoriumFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "Thorium file";
    }

    @Nullable
    @Override
    public Icon getIcon(int flags) {
        return ThoriumIcons.FILE;
    }

    @Override
    public ScopeNode getContext() {
        return null;
    }

    @Nullable
    @Override
    public PsiElement resolve(PsiNamedElement element) {
        return SymtabUtils.resolve(
                this,
                ThoriumLanguage.INSTANCE,
                element,
                "/root/typeDef/IDENTIFIER"
        );
    }
}
