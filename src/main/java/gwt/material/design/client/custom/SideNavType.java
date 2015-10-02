package gwt.material.design.client.custom;

/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 GwtMaterialDesign
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



/**
 * Type of the Sidenav
 * - open
 * - close
 * - minu
 * - clip
 * - float
 * - card
 * @author kevzlou7979
 *
 */
public enum SideNavType{

	OPEN("open"), 
	CLOSE("close"), 
	MINI("mini"),
	CLIP("clip"),
	FLOAT("float"),
	CARD("card");
	
	String value;
	SideNavType(String value){
		this.value = value;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	/**
	 * Get Type from String
	 * @param text
	 * @return Type
	 */
	public static SideNavType fromString(String text) {
	    if (text != null) {
	      for (SideNavType b : SideNavType.values()) {
	        if (text.equalsIgnoreCase(b.getValue())) {
	          return b;
	        }
	      }
	    }
	    return null;
	  }
	
}