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

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class ThoriumFileType extends LanguageFileType {
    public static final ThoriumFileType INSTANCE = new ThoriumFileType();

    public ThoriumFileType() {
        super(ThoriumLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName() {
        return "Thorium file";
    }

    @NotNull
    @Override
    public String getDescription() {
        return "Thorium language file";
    }

    @NotNull
    @Override
    public String getDefaultExtension() {
        return "th";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return ThoriumIcons.FILE;
    }
}
