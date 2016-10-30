/*
 * #%L
 * GwtBootstrap3
 * %%
 * Copyright (C) 2015 GwtBootstrap3
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
package gwt.material.design.client.base.validator;

import com.google.gwt.i18n.client.ConstantsWithLookup;
import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;

/**
 * Validation messages.
 * <p>
 * Message functions should be the key with "_" replacing any periods. This allows the
 * ValidationMessageResolver to find the message by key.
 *
 * @author Steven Jardine
 */
@DefaultLocale("en")
public interface ValidationMessages extends ConstantsWithLookup {

    class Keys {

        public static final String BLANK = "gwt.material.design.validation.Blank.message";

        public static final String DECIMAL_MAX = "gwt.material.design.validation.DecimalMax.message";

        public static final String DECIMAL_MIN = "gwt.material.design.validation.DecimalMin.message";

        public static final String FIELD_MATCH = "gwt.material.design.validation.FieldMatch.message";

        public static final String FUTURE = "gwt.material.design.validation.Future.message";

        public static final String PAST = "gwt.material.design.validation.Past.message";

        public static final String REGEX = "gwt.material.design.validation.RegEx.message";

        public static final String SIZE = "gwt.material.design.validation.Size.message";
    }

    /**
     * @return the blank validation message.
     */
    @Key(Keys.BLANK)
    @DefaultStringValue("Field cannot be blank")
    String gwt_material_design_validation_Blank_message();

    /**
     * @return the decimal max validation message.
     */
    @Key(Keys.DECIMAL_MAX)
    @DefaultStringValue("Value must be less than or equal to {1}")
    String gwt_material_design_validation_DecimalMax_message();

    /**
     * @return the decimal min validation message.
     */
    @Key(Keys.DECIMAL_MIN)
    @DefaultStringValue("Value must be greater than or equal to {1}")
    String gwt_material_design_validation_DecimalMin_message();

    /**
     * @return the field match validation message.
     */
    @Key(Keys.FIELD_MATCH)
    @DefaultStringValue("{1} do not match")
    String gwt_material_design_validation_FieldMatch_message();

    /**
     * @return the future validation message.
     */
    @Key(Keys.FUTURE)
    @DefaultStringValue("Value must be in the future")
    String gwt_material_design_validation_Future_message();

    /**
     * @return the past validation message.
     */
    @Key(Keys.PAST)
    @DefaultStringValue("Value must be in the past")
    String gwt_material_design_validation_Past_message();

    /**
     * @return the regular expression validation message.
     */
    @Key(Keys.REGEX)
    @DefaultStringValue("Must match regex")
    String gwt_material_design_validation_RegEx_message();

    /**
     * @return the size validation message.
     */
    @Key(Keys.SIZE)
    @DefaultStringValue("Size must be between {1} and {2}")
    String gwt_material_design_validation_Size_message();

}
