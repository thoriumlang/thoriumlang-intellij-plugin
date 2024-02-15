package org.thoriumlang.intellij.plugin;

import com.intellij.lang.CodeDocumentationAwareCommenter;
import com.intellij.psi.PsiComment;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.Nullable;

public class ThoriumCommenter implements CodeDocumentationAwareCommenter {
    @Nullable
    @Override
    public String getLineCommentPrefix() {
        return "//";
    }

    @Nullable
    @Override
    public String getBlockCommentPrefix() {
        return "/*";
    }

    @Nullable
    @Override
    public String getBlockCommentSuffix() {
        return "*/";
    }

    @Nullable
    @Override
    public String getCommentedBlockCommentPrefix() {
        return null;
    }

    @Nullable
    @Override
    public String getCommentedBlockCommentSuffix() {
        return null;
    }

    @Nullable
    @Override
    public IElementType getLineCommentTokenType() {
        return ThoriumParserDefinition.LINE_COMMENT;
    }

    @Nullable
    @Override
    public IElementType getBlockCommentTokenType() {
        return ThoriumParserDefinition.BLOCK_COMMENT;
    }

    @Nullable
    @Override
    public IElementType getDocumentationCommentTokenType() {
        return ThoriumParserDefinition.DOC_COMMENT;
    }

    @Nullable
    @Override
    public String getDocumentationCommentPrefix() {
        return "/**";
    }

    @Nullable
    @Override
    public String getDocumentationCommentLinePrefix() {
        return "*";
    }

    @Nullable
    @Override
    public String getDocumentationCommentSuffix() {
        return "*/";
    }

    @Override
    public boolean isDocumentationComment(PsiComment element) {
        return element.getTokenType() == ThoriumParserDefinition.DOC_COMMENT || element.getTokenType().equals(ThoriumParserDefinition.DOC_COMMENT);
    }
}
