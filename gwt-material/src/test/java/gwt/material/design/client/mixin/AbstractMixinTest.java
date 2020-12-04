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

import gwt.material.design.client.base.mixin.AbstractMixin;
import gwt.material.design.client.ui.base.BaseTestCase;

public abstract class AbstractMixinTest<M extends AbstractMixin> extends BaseTestCase {

    protected M mixin;

    public void testMixin() {
        mixin = setupMixin();
        runTest(mixin);
    }

    protected abstract void runTest(M mixin);

    protected abstract M setupMixin();

    public M getMixin() {
        return mixin;
    }
}
