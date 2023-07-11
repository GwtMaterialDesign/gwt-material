package gwt.material.design.client.ui;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.LinkElement;
import gwt.material.design.client.base.HasSymbols;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.base.mixin.CssNameMixin;
import gwt.material.design.client.constants.SymbolType;
import gwt.material.design.client.ui.html.Span;

public class MaterialSymbol extends MaterialWidget implements HasSymbols {

    static {
        LinkElement linkElement = Document.get().createLinkElement();
        linkElement.setRel("stylesheet");
        linkElement.setHref("https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200");
        body().append(linkElement);
    }

    protected Span span = new Span();
    protected CssNameMixin<Span, SymbolType> symbolTypeMixin;
    protected boolean filled;
    protected int weight = 400;
    protected int grade = 0;
    protected int opticalSize = 48;

    public MaterialSymbol() {
        super(Document.get().createElement("div"));
        setType(SymbolType.OUTLINED);
    }

    @Override
    protected void onLoad() {
        super.onLoad();

        load();
        add(span);
    }

    @Override
    public void setSymbol(String symbol) {
        span.getElement().setInnerText(symbol);
    }

    @Override
    public void setType(SymbolType type) {
        getSymbolTypeMixin().setCssName(type);
    }

    @Override
    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    @Override
    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public void setOpticalSize(int size) {
        this.opticalSize = size;
    }

    protected void load() {
        getElement().setAttribute("style", "font-variation-settings: 'FILL' 1, 'wght' 700, 'GRAD' 0, 'opsz' 48;");
    }

    public CssNameMixin<Span, SymbolType> getSymbolTypeMixin() {
        if (symbolTypeMixin == null) {
            symbolTypeMixin = new CssNameMixin<>(span);
        }
        return symbolTypeMixin;
    }
}
