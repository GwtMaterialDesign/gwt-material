package gwt.material.design.client.ui;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.LinkElement;
import gwt.material.design.client.base.HasSymbols;
import gwt.material.design.client.base.MaterialWidget;
import gwt.material.design.client.base.mixin.ColorsMixin;
import gwt.material.design.client.base.mixin.CssNameMixin;
import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.SymbolType;
import gwt.material.design.client.ui.html.Span;

public class MaterialSymbol extends MaterialWidget implements HasSymbols {

    protected Span span = new Span();
    protected static LinkElement linkElement = Document.get().createLinkElement();
    protected CssNameMixin<Span, SymbolType> symbolTypeMixin;
    protected ColorsMixin<MaterialSymbol> symbolColorsMixin;
    protected boolean filled;
    protected int weight = 400;
    protected int grade = 0;
    protected int opticalSize = 48;

    static {
        linkElement.setRel("stylesheet");
        body().append(linkElement);
    }

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
        span.setText(symbol);
    }

    @Override
    public void setColor(Color color) {
        getSymbolColorsMixin().setTextColor(color);
    }

    @Override
    public void setSymbolSize(String size) {
        span.getElement().getStyle().setProperty("fontSize", size);
    }

    @Override
    public void setType(SymbolType type) {
        getSymbolTypeMixin().setCssName(type);
        linkElement.setHref(type.getCssLink());
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
        span.getElement().getStyle().setProperty("fontVariationSettings", "'FILL' " + (filled ? 1 : 0) + ", 'wght' " + weight + ", 'GRAD' " + grade + ", 'opsz' " + opticalSize);
    }

    public ColorsMixin<MaterialSymbol> getSymbolColorsMixin() {
        if (symbolColorsMixin == null) {
            symbolColorsMixin = new ColorsMixin<>(this);
        }
        return symbolColorsMixin;
    }

    public CssNameMixin<Span, SymbolType> getSymbolTypeMixin() {
        if (symbolTypeMixin == null) {
            symbolTypeMixin = new CssNameMixin<>(span);
        }
        return symbolTypeMixin;
    }
}
