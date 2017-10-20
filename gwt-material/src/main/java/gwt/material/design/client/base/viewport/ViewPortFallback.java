/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2017 GwtMaterialDesign
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
package gwt.material.design.client.base.viewport;

/**
 * View Port Fallback.
 * <br><br>
 * Return true to continue propagating view port detection, false to break.
 * <br><br>
 * Note that if {@link ViewPortHandler#propagateFallback(boolean)} is not true,
 * this will only be called if no {@link Resolution} matches are made.
 * Otherwise the fallback is called upon every failed {@link Resolution} match
 * and can be propagated (or not propagated) with its call result.
 *
 * @author Ben
 */
public interface ViewPortFallback {

    /**
     * Fallback call.
     *
     * @param viewPort the view port that wasn't detected.
     * @return true to continue propagating view port detection, false to break.
     */
    boolean call(ViewPortRect viewPort);
}
