package gwt.material.design.client.base;

import gwt.material.design.client.constants.Color;
import gwt.material.design.client.constants.SymbolType;

public interface HasSymbols {

    void setType(SymbolType type);

    void setSymbol(String symbol);

    void setColor(Color color);

    void setSymbolSize(String size);

    void setFilled(boolean filled);

    void setWeight(int weight);

    void setGrade(int grade);

    void setOpticalSize(int size);
}
