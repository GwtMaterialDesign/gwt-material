package gwt.material.design.client.base.helper;

/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 GwtMaterialDesign
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


import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;

public final class CodeHelper {
    public static SafeHtml parseCode(String code) {
        SafeHtmlBuilder builder = new SafeHtmlBuilder();
        String[] splitted = code.replaceAll("\\\\s", " ").split("\\\\n\\s?");
        String[] arr$ = splitted;
        int len$ = splitted.length;

        for (int i$ = 0; i$ < len$; ++i$) {
            String s = arr$[i$];
            builder.append(SafeHtmlUtils.fromTrustedString(SafeHtmlUtils.htmlEscapeAllowEntities(s)));
            builder.appendHtmlConstant("<br>");
        }

        return builder.toSafeHtml();
    }

    private CodeHelper() {
    }
}
