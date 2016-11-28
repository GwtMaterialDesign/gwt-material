/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2016 GwtMaterialDesign
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
package gwt.material.design.client.ui;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import gwt.material.design.client.base.HasTitle;
import gwt.material.design.client.constants.HeadingSize;
import gwt.material.design.client.ui.html.Div;
import gwt.material.design.client.ui.html.Heading;
import gwt.material.design.client.ui.html.Paragraph;

//@formatter:off

/**
 * Title is a component that will easily add Title and description widgets
 * <p>
 * <p>
 * <h3>UiBinder Usage:</h3>
 * <pre>
 * {@code
 * <m:MaterialTitle title="I love Material Design" description="This is sample description" />
 * }
 * </pre>
 * </p>
 *
 * @author kevzlou7979
 * @author Ben Dol
 */
//@formatter:on
public class MaterialTitle extends Div implements HasTitle {

    private Heading header = new Heading(HeadingSize.H4);
    private Paragraph paragraph = new Paragraph();

    public MaterialTitle() {
        header.setFontWeight(300);
        header.getElement().getStyle().setMarginTop(60, Unit.PX);
        add(header);
        add(paragraph);
    }

    public MaterialTitle(String title, String description) {
        this();
        setTitle(title);
        setDescription(description);
    }

    public MaterialTitle(String title) {
        this();
        setTitle(title);
    }

    @Override
    public void setDescription(String description) {
        paragraph.setText(description);
    }

    @Override
    public void setTitle(String title) {
        header.getElement().setInnerSafeHtml(SafeHtmlUtils.fromString(title));
    }
}
