package gwt.material.design.client.custom;

import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;

public class MaterialCheckBoxCell extends CheckboxCell {

	private static final SafeHtml INPUT_CHECKED = SafeHtmlUtils.fromSafeConstant("<input type=\"checkbox\" tabindex=\"-1\" checked/><label/>");
	private static final SafeHtml INPUT_UNCHECKED = SafeHtmlUtils.fromSafeConstant("<input type=\"checkbox\" tabindex=\"-1\"/><label/>");

	public MaterialCheckBoxCell() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MaterialCheckBoxCell(boolean dependsOnSelection, boolean handlesSelection) {
		super(dependsOnSelection, handlesSelection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(com.google.gwt.cell.client.Cell.Context context, Boolean value, SafeHtmlBuilder sb) {
		Object key = context.getKey();
		Boolean viewData = getViewData(key);
		if (viewData != null && viewData.equals(value)) {
			clearViewData(key);
			viewData = null;
		}

		if (value != null && ((viewData != null) ? viewData : value)) {
			sb.append(INPUT_CHECKED);
		}
		else {
			sb.append(INPUT_UNCHECKED);
		}
	}

}