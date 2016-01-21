package gwt.material.design.client.ui;

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

import com.google.gwt.user.client.ui.TextBox;
import gwt.material.design.client.constants.IconType;
import gwt.material.design.client.constants.InputType;
import gwt.material.design.client.ui.html.Label;

//@formatter:off

/**
 * Material Search is a value box component that returs a result based on your search
 *
 * <p>
 * <h3>UiBinder Usage:</h3>
 * <pre>
 * {@code
 * <m:MaterialSearch placeholder="Sample"/>
 * }
 * </pre>
 * </p>
 *
 * @author kevzlou7979
 * @author Ben Dol
 * @see <a href="http://gwt-material-demo.herokuapp.com/#navigations">Material Search</a>
 */
//@formatter:on
public class MaterialSearch extends MaterialValueBox<String>{

    private Label label = new Label();
    private MaterialIcon iconSearch = new MaterialIcon(IconType.SEARCH);
    private MaterialIcon iconClose = new MaterialIcon(IconType.CLOSE);


    public MaterialSearch() {
        super(new TextBox());
        setType(InputType.SEARCH);
        label.add(iconSearch);
        label.getElement().setAttribute("for", "search");
        add(label);
        add(iconClose);
    }
}


