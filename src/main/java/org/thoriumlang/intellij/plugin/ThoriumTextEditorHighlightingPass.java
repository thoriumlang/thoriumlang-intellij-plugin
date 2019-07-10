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

import com.intellij.codeHighlighting.TextEditorHighlightingPass;
import com.intellij.codeInsight.daemon.impl.HighlightInfo;
import com.intellij.codeInsight.daemon.impl.HighlightInfoType;
import com.intellij.codeInsight.daemon.impl.UpdateHighlightersUtil;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiRecursiveElementWalkingVisitor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.thoriumlang.intellij.plugin.psi.MethodSignature;

import java.util.ArrayList;
import java.util.List;

public class ThoriumTextEditorHighlightingPass extends TextEditorHighlightingPass {
    private final PsiFile myFile;
    private final List<HighlightInfo> myInfos = new ArrayList<>();

    ThoriumTextEditorHighlightingPass(@NotNull PsiFile file, @Nullable Document document) {
        super(file.getProject(), document);
        this.myFile = file;
    }

    @Override
    public void doCollectInformation(@NotNull ProgressIndicator progress) {
        myFile.accept(new PsiRecursiveElementWalkingVisitor() {
            @Override
            public void visitElement(PsiElement element) {
                if (element instanceof MethodSignature) {
                    myInfos.add(
                            HighlightInfo.newHighlightInfo(HighlightInfoType.INFORMATION)
                                    .range(((MethodSignature) element).getNameIdentifierThorium())
                                    .needsUpdateOnTyping(false)
                                    .textAttributes(ThoriumSyntaxHighlighter.METHOD_DECLARATION)
                                    .create()
                    );
                }
                super.visitElement(element);
            }
        });
    }

    @Override
    public void doApplyInformationToEditor() {
        if (myDocument == null || myInfos.isEmpty()) {
            return;
        }
        UpdateHighlightersUtil.setHighlightersToEditor(
                myProject, myDocument, 0, myFile.getTextLength(), myInfos, getColorsScheme(), getId()
        );
    }
}