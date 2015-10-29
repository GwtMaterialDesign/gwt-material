package gwt.material.design.client.ui;

import gwt.material.design.client.custom.ComplexWidget;
import gwt.material.design.client.custom.CustomDiv;
import gwt.material.design.client.custom.HasColors;

import com.google.gwt.dom.client.Document;
import com.google.gwt.user.client.ui.Widget;


//@formatter:off
/**
* Footers are a great way to organize a lot of site navigation and information at the end of a page. This is where the user will look once hes finished scrolling through the current page or is looking for additional information about your website.
* <h3>UiBinder Usage:</h3>
* 
* <pre>
* {@code 
<m:MaterialFooter backgroundColor="blue">
	<m:MaterialRow>
		<m:MaterialColumn grid="s12 m6 l6">
			<m:MaterialTitle fontSize="0.7" color="white" title="Join The Discussion" description="We provide Gitter Chat rooms in order for GWT Developers discussed and collaborate about GWT Material Design and Phonegap Integration."/>
			<m:MaterialButton ui:field="btnChat" text="CHAT" backgroundColor="blue lighten-2" waves="light"/>
		</m:MaterialColumn>
		<m:MaterialColumn grid="s12 m6 l6">
			<m:MaterialTitle fontSize="0.7" color="white" title="GWT Phonegap" description="We provide Gitter Chat rooms in order for GWT Developers discussed and collaborate about GWT Material Design and Phonegap Integration."/>
			<m:MaterialButton ui:field="btnDownloadPhonegap" text="GWT Material APK" backgroundColor="blue lighten-2" waves="light"/>
		</m:MaterialColumn>
	</m:MaterialRow>
					
	<m:MaterialFooterCopyright backgroundColor="blue darken-1">
		<m:MaterialLabel text=" © 2014 Copyright Text"/>
	</m:MaterialFooterCopyright>
</m:MaterialFooter> }
* </pre>
* </p>
* 
* @author kevzlou7979
* @see <a href="http://gwt-material-demo.herokuapp.com/#footer">Material Footer</a>
*/
//@formatter:on
public class MaterialFooter extends ComplexWidget implements HasColors{

	
	CustomDiv container = new CustomDiv();
	
	public MaterialFooter() {
		setElement(Document.get().createElement("footer"));
		setStyleName("page-footer");
		container.setStyleName("container");
	}

	@Override
	public void add(Widget child) {
		if(child.getElement().getClassName().contains("footer-copyright")){
			super.add(child);
		}else{
			container.add(child);
			super.add(container);
		}
		
	}

	@Override
	public void setBackgroundColor(String bgColor) {
		// TODO Auto-generated method stub
		addStyleName(bgColor);
	}

	@Override
	public void setTextColor(String textColor) {
		// TODO Auto-generated method stub
		addStyleName(textColor + "-text");
	}
	
}
