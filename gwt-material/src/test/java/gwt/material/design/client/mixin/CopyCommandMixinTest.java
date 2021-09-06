/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2020 GwtMaterialDesign
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package gwt.material.design.client.mixin;

import com.google.gwt.user.client.ui.RootPanel;
import gwt.material.design.client.base.CopyCommand;
import gwt.material.design.client.base.mixin.CopyCommandMixin;
import gwt.material.design.client.ui.MaterialTextBox;

public class CopyCommandMixinTest extends BaseMixinTest<CopyCommandMixin<MaterialTextBox>> {

    @Override
    protected void runTest(CopyCommandMixin<MaterialTextBox> mixin) {
        // Attach Widget
        MaterialTextBox textBox = new MaterialTextBox();
        RootPanel.get().add(textBox);
        textBox.setCopyCommand(CopyCommand.ON_READONLY_HOVER);

        // Test CopyCommand Types
        checkCopyCommandType(textBox, CopyCommand.ON_ALWAYS);
        checkCopyCommandType(textBox, CopyCommand.ON_READONLY);
        checkCopyCommandType(textBox, CopyCommand.ON_READONLY_HOVER);
        checkCopyCommandType(textBox, CopyCommand.ON_ALWAYS_HOVER);
    }

    protected void checkCopyCommandType(MaterialTextBox textBox, CopyCommand copyCommand) {
        textBox.setCopyCommand(copyCommand);
        assertEquals(copyCommand, textBox.getCopyCommand());
    }

    @Override
    protected CopyCommandMixin<MaterialTextBox> setupMixin() {
        return new CopyCommandMixin<>(new MaterialTextBox());
    }
}
