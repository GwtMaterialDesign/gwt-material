/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2019 GwtMaterialDesign
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
package gwt.material.design.client.base;

/**
 * The zoom property in CSS allows you to scale your content. It is non-standard, and was originally implemented only in
 * Internet Explorer. Although several other browsers now support zoom, it isn't recommended for production sites.
 *
 * @see <a href="https://developer.mozilla.org/en-US/docs/Web/CSS/zoom">Official Documentation</a>
 * @author kevzlou7979@gmail.com
 */
public interface HasZoom {

    void setZoom(Double level);
}
