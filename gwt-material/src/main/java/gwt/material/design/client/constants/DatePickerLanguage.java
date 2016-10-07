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
package gwt.material.design.client.constants;

import com.google.gwt.resources.client.TextResource;
import gwt.material.design.client.resources.MaterialDatePickerClientBundle;

/**
 * Language List of DatePicker - contains 43 supported languages
 *
 * @author kevzlou7979
 */
public enum DatePickerLanguage {

    AR("ar", "Arabic", MaterialDatePickerClientBundle.INSTANCE.ar()),
    BG("bg_BG", "Bulgarian", MaterialDatePickerClientBundle.INSTANCE.bg()),
    BS("bs_BA", "Bosnian", MaterialDatePickerClientBundle.INSTANCE.bs()),
    CA("ca_ES", "Catalan", MaterialDatePickerClientBundle.INSTANCE.ca()),
    CS("cs_CZ", "Czech", MaterialDatePickerClientBundle.INSTANCE.cs()),
    DA("da_DK", "Danish", MaterialDatePickerClientBundle.INSTANCE.da()),
    DE("de_DE", "German", MaterialDatePickerClientBundle.INSTANCE.de()),
    EL("el_GR", "Greek", MaterialDatePickerClientBundle.INSTANCE.el()),
    EN("en", "English", MaterialDatePickerClientBundle.INSTANCE.en()),
    ES("es_ES", "Spanish", MaterialDatePickerClientBundle.INSTANCE.es()),
    ET("et_EE", "Estonian", MaterialDatePickerClientBundle.INSTANCE.et()),
    EU("eu_ES", "Basque", MaterialDatePickerClientBundle.INSTANCE.eu()),
    FA("fa_ir", "Farsi", MaterialDatePickerClientBundle.INSTANCE.fa()),
    FI("fi_FI", "Finnish", MaterialDatePickerClientBundle.INSTANCE.fi()),
    FR("fr_FR", "French", MaterialDatePickerClientBundle.INSTANCE.fr()),
    GL("gl_ES", "Galician", MaterialDatePickerClientBundle.INSTANCE.gl()),
    HE("he_IL", "Hebrew", MaterialDatePickerClientBundle.INSTANCE.he()),
    HI("hi_IN", "Hindi", MaterialDatePickerClientBundle.INSTANCE.hi()),
    HR("hr_HR", "Croatian", MaterialDatePickerClientBundle.INSTANCE.hr()),
    HU("hu_HU", "Hungarian", MaterialDatePickerClientBundle.INSTANCE.hu()),
    ID("id_ID", "Indonesian", MaterialDatePickerClientBundle.INSTANCE.id()),
    IS("is_IS", "Icelandic", MaterialDatePickerClientBundle.INSTANCE.is()),
    IT("it_IT", "Italian", MaterialDatePickerClientBundle.INSTANCE.it()),
    JA("ja_JP", "Japanese", MaterialDatePickerClientBundle.INSTANCE.ja()),
    KO("ko_KR", "Korean", MaterialDatePickerClientBundle.INSTANCE.ko()),
    LT("lt_LT", "Lietuviškai", MaterialDatePickerClientBundle.INSTANCE.lt()),
    LV("lv_LV", "Latvian", MaterialDatePickerClientBundle.INSTANCE.lv()),
    NB("nb_NO", "Norwegian", MaterialDatePickerClientBundle.INSTANCE.nb()),
    NE("ne_NP", "Nepali", MaterialDatePickerClientBundle.INSTANCE.ne()),
    NL("nl_NL", "Dutch", MaterialDatePickerClientBundle.INSTANCE.nl()),
    PL("pl_PL", "Polish", MaterialDatePickerClientBundle.INSTANCE.pl()),
    PT_br("pt_BR", "Brazilian Portuguese", MaterialDatePickerClientBundle.INSTANCE.pt_BR()),
    PT_PT("pt_PT", "Portuguese", MaterialDatePickerClientBundle.INSTANCE.pt_PT()),
    RO("ro_RO", "Romanian", MaterialDatePickerClientBundle.INSTANCE.ro()),
    RU("ru_RU", "Russian", MaterialDatePickerClientBundle.INSTANCE.ru()),
    SK("sk_SK", "Slovak", MaterialDatePickerClientBundle.INSTANCE.sk()),
    SL("sl_SI", "Slovenian", MaterialDatePickerClientBundle.INSTANCE.sl()),
    SV("sv_SE", "Swedish", MaterialDatePickerClientBundle.INSTANCE.sv()),
    TH("th_TH", "Thai", MaterialDatePickerClientBundle.INSTANCE.th()),
    TR("tr_TR", "Turkish", MaterialDatePickerClientBundle.INSTANCE.tr()),
    UK("uk_UA", "Ukrainian", MaterialDatePickerClientBundle.INSTANCE.uk()),
    VI("vi_VN", "Vietnamese", MaterialDatePickerClientBundle.INSTANCE.vi()),
    ZH_CN("zh_CN", "Simplified Chinese", MaterialDatePickerClientBundle.INSTANCE.zh_CN()),
    ZH_TW("zh_TW", "Traditional Chinese", MaterialDatePickerClientBundle.INSTANCE.zh_TW());

    private final String code;
    private final String name;
    private final TextResource js;

    DatePickerLanguage(final String code, final String name, final TextResource js) {
        this.code = code;
        this.name = name;
        this.js = js;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public TextResource getJs() {
        return js;
    }
}
