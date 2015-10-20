package gwt.material.design.client.custom;

import gwt.material.design.client.ui.MaterialLabel;

import com.google.gwt.dom.client.Document;

public class CustomPicker extends ComplexWidget implements HasGrid, HasError{

	private MaterialLabel lblError = new MaterialLabel();
	
	public CustomPicker(){
		setElement(Document.get().createElement("input"));
	}
	
	@Override
	public void setGrid(String grid) {
		this.addStyleName("col " + grid);
	}

	@Override
	public void setOffset(String offset) {
		String tobeadded = "";
		String[] vals = offset.split(" ");
		for(String val : vals){
			tobeadded = tobeadded + " offset-" +  val;
		}
		this.addStyleName(tobeadded);
	}

	@Override
	public void setError(String error) {
		lblError.setText(error);
		lblError.addStyleName("field-error-label");
		lblError.removeStyleName("field-success-label");
		addStyleName("field-error-picker");
		removeStyleName("field-success-picker");
		lblError.setVisible(true);
	}

	@Override
	public void setSuccess(String success) {
		lblError.setText(success);
		lblError.addStyleName("field-success-label");
		lblError.removeStyleName("field-error-label");
		addStyleName("field-success-picker");
		removeStyleName("field-error-picker");
		lblError.setVisible(true);
	}



}
