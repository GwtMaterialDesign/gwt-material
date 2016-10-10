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
package gwt.material.design.client.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

/**
 * Client bundle for date picker - contains 43 supported languages
 *
 * @author kevzlou7979
 */
public interface MaterialDatePickerClientBundle extends ClientBundle {
    MaterialDatePickerClientBundle INSTANCE = GWT.create(MaterialDatePickerClientBundle.class);

    @Source("js/datepicker/ar.js")
    TextResource ar();

    @Source("js/datepicker/bg_BG.js")
    TextResource bg();

    @Source("js/datepicker/bs_BA.js")
    TextResource bs();

    @Source("js/datepicker/ca_ES.js")
    TextResource ca();

    @Source("js/datepicker/cs_CZ.js")
    TextResource cs();

    @Source("js/datepicker/da_DK.js")
    TextResource da();

    @Source("js/datepicker/de_DE.js")
    TextResource de();

    @Source("js/datepicker/el_GR.js")
    TextResource el();

    @Source("js/datepicker/en.js")
    TextResource en();

    @Source("js/datepicker/es_ES.js")
    TextResource es();

    @Source("js/datepicker/et_EE.js")
    TextResource et();

    @Source("js/datepicker/eu_ES.js")
    TextResource eu();

    @Source("js/datepicker/fa_ir.js")
    TextResource fa();

    @Source("js/datepicker/fi_FI.js")
    TextResource fi();

    @Source("js/datepicker/fr_FR.js")
    TextResource fr();

    @Source("js/datepicker/gl_ES.js")
    TextResource gl();

    @Source("js/datepicker/he_IL.js")
    TextResource he();

    @Source("js/datepicker/hi_IN.js")
    TextResource hi();

    @Source("js/datepicker/hr_HR.js")
    TextResource hr();

    @Source("js/datepicker/hu_HU.js")
    TextResource hu();

    @Source("js/datepicker/id_ID.js")
    TextResource id();

    @Source("js/datepicker/is_IS.js")
    TextResource is();

    @Source("js/datepicker/it_IT.js")
    TextResource it();

    @Source("js/datepicker/ja_JP.js")
    TextResource ja();

    @Source("js/datepicker/ko_KR.js")
    TextResource ko();

    @Source("js/datepicker/lt_LT.js")
    TextResource lt();

    @Source("js/datepicker/lv_LV.js")
    TextResource lv();

    @Source("js/datepicker/nb_NO.js")
    TextResource nb();

    @Source("js/datepicker/ne_NP.js")
    TextResource ne();

    @Source("js/datepicker/nl_NL.js")
    TextResource nl();

    @Source("js/datepicker/pl_PL.js")
    TextResource pl();

    @Source("js/datepicker/pt_BR.js")
    TextResource pt_BR();

    @Source("js/datepicker/pt_PT.js")
    TextResource pt_PT();

    @Source("js/datepicker/ro_RO.js")
    TextResource ro();

    @Source("js/datepicker/ru_RU.js")
    TextResource ru();

    @Source("js/datepicker/sk_SK.js")
    TextResource sk();

    @Source("js/datepicker/sl_SI.js")
    TextResource sl();

    @Source("js/datepicker/sv_SE.js")
    TextResource sv();

    @Source("js/datepicker/th_TH.js")
    TextResource th();

    @Source("js/datepicker/tr_TR.js")
    TextResource tr();

    @Source("js/datepicker/uk_UA.js")
    TextResource uk();

    @Source("js/datepicker/vi_VN.js")
    TextResource vi();

    @Source("js/datepicker/zh_CN.js")
    TextResource zh_CN();

    @Source("js/datepicker/zh_TW.js")
    TextResource zh_TW();


}