package gwt.material.design.client.ui;

import gwt.material.design.client.custom.ComplexWidget;
import gwt.material.design.client.custom.CustomH4;
import gwt.material.design.client.custom.HasActive;

import com.google.gwt.dom.client.Document;

//@formatter:off
/**
* Collections allow you to group list objects together.
* @author kevzlou7979
*<p>
*<h3>UiBinder Usage:</h3>
*<pre>
*{@code 
*Simple
<m:MaterialCollection >
	<m:MaterialCollectionItem><m:MaterialLabel text="Collecton 1"/></m:MaterialCollectionItem>
	<m:MaterialCollectionItem><m:MaterialLabel text="Collecton 2"/></m:MaterialCollectionItem>
	<m:MaterialCollectionItem><m:MaterialLabel text="Collecton 3"/></m:MaterialCollectionItem>
	<m:MaterialCollectionItem><m:MaterialLabel text="Collecton 4"/></m:MaterialCollectionItem>
</m:MaterialCollection>

Links
<m:MaterialCollection >
	<m:MaterialCollectionItem><m:MaterialLink text="Collecton 1"/></m:MaterialCollectionItem>
	<m:MaterialCollectionItem><m:MaterialLink text="Collecton 2"/></m:MaterialCollectionItem>
	<m:MaterialCollectionItem><m:MaterialLink text="Collecton 3"/></m:MaterialCollectionItem>
	<m:MaterialCollectionItem><m:MaterialLink text="Collecton 4"/></m:MaterialCollectionItem>
</m:MaterialCollection>

Header
<m:MaterialCollection header="Header Title">
	<m:MaterialCollectionItem><m:MaterialLink text="Collecton 1"/></m:MaterialCollectionItem>
	<m:MaterialCollectionItem><m:MaterialLink text="Collecton 2"/></m:MaterialCollectionItem>
	<m:MaterialCollectionItem><m:MaterialLink text="Collecton 3"/></m:MaterialCollectionItem>
	<m:MaterialCollectionItem><m:MaterialLink text="Collecton 4"/></m:MaterialCollectionItem>
</m:MaterialCollection>

Secondary Content
<m:MaterialCollection header="Header Title">
	<m:MaterialCollectionItem>
		<m:MaterialLink text="Collecton 1"/>
		<m:MaterialSecondaryContent><m:MaterialIcon icon="polymer" iconPosition="right" waves="default"/></m:MaterialSecondaryContent>
	</m:MaterialCollectionItem>
	<m:MaterialCollectionItem>
		<m:MaterialLink text="Collecton 2"/>
		<m:MaterialSecondaryContent><m:MaterialIcon icon="polymer" iconPosition="right" waves="default"/></m:MaterialSecondaryContent>
	</m:MaterialCollectionItem>
	<m:MaterialCollectionItem>
		<m:MaterialLink text="Collecton 3"/>
		<m:MaterialSecondaryContent><m:MaterialIcon icon="polymer" iconPosition="right" waves="default"/></m:MaterialSecondaryContent>	
	</m:MaterialCollectionItem>
	<m:MaterialCollectionItem>
		<m:MaterialLink text="Collecton 4"/>
		<m:MaterialSecondaryContent><m:MaterialIcon icon="polymer" iconPosition="right" waves="default"/></m:MaterialSecondaryContent>
	</m:MaterialCollectionItem>
</m:MaterialCollection>

*}
*</pre>
* </p>
* @see <a href="http://gwt-material-demo.herokuapp.com/#collections">Material Collections</a>
*/

//@formatter:on
public class MaterialCollection2 extends ComplexWidget implements HasActive{
	
	private CustomH4 span = new CustomH4();
	private int index;
	
	/**
	 * Creates an empty collection component
	 */
	public MaterialCollection2() {
		setElement(Document.get().createULElement());
		setStyleName("collection");		
	}
	
	/**
	 * Sets the header of the collection component
	 * @param header
	 */
	public void setHeader(String header){
		span.getElement().setInnerHTML(header);
		addStyleName("with-header");
		ListItem item = new ListItem(span);
		MaterialUiHelper.addMousePressedHandlers(item);
		item.setStyleName("collection-header");
		insert(item, 0);
	}

	@Override
	public void setActive(int index) {
		this.index = index;
		getWidget(index).addStyleName("active");
	}

	@Override
	public int getActive() {
		// TODO Auto-generated method stub
		return index;
	}
	
}
