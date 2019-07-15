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

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;

public class ThoriumColorSettingsPage implements ColorSettingsPage {
    private static final AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[]{
            new AttributesDescriptor("Identifier", ThoriumSyntaxHighlighter.IDENTIFIER),
            new AttributesDescriptor("Keyword", ThoriumSyntaxHighlighter.KEYWORD),
            new AttributesDescriptor("String", ThoriumSyntaxHighlighter.STRING),
            new AttributesDescriptor("Number", ThoriumSyntaxHighlighter.NUMBER),
            new AttributesDescriptor("Line comment", ThoriumSyntaxHighlighter.LINE_COMMENT),
            new AttributesDescriptor("Block comment", ThoriumSyntaxHighlighter.BLOCK_COMMENT),
            new AttributesDescriptor("Punctuation", ThoriumSyntaxHighlighter.PUNCTUATION),
    };

    @Nullable
    @Override
    public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        return null;
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return ThoriumIcons.FILE;
    }

    @NotNull
    @Override
    public SyntaxHighlighter getHighlighter() {
        return new ThoriumSyntaxHighlighter();
    }

    @NotNull
    @Override
    public String getDemoText() {
        return
                "use a.b.{\n" +
                        "    c.D : DD,\n" +
                        "    c.E : EE\n" +
                        "};\n" +
                        "\n" +
                        "// this is a comment\n" +
                        "type MyType {\n" +
                        "    /**\n" +
                        "     * returns bla bla bla\n" +
                        "     */\n" +
                        "    public a(): ((A?&B)|C)?|(D);\n" +
                        "    namespace b(): A&B;\n" +
                        "    c(): A?&C;\n" +
                        "}";
    }

    @NotNull
    @Override
    public AttributesDescriptor[] getAttributeDescriptors() {
        return DESCRIPTORS;
    }

    @NotNull
    @Override
    public ColorDescriptor[] getColorDescriptors() {
        return ColorDescriptor.EMPTY_ARRAY;
    }

    @NotNull
    @Override
    public String getDisplayName() {
        return "Thorium";
    }
}
