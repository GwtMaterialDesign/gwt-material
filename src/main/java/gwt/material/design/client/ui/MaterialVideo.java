package gwt.material.design.client.ui;

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

import gwt.material.design.client.custom.HasGrid;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.HTMLPanel;

public class MaterialVideo extends HTMLPanel implements HasGrid{

	private String url="";
	private Frame frame =  new Frame();
	
	public MaterialVideo(SafeHtml safeHtml) {
		super(safeHtml);
		// TODO Auto-generated constructor stub
	}

	public MaterialVideo(String tag, String html) {
		super(tag, html);
		// TODO Auto-generated constructor stub
	}

	public MaterialVideo(String html) {
		super(html);
		// TODO Auto-generated constructor stub
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		frame.setUrl(url);
		this.addStyleName("video-container");
		this.add(frame);
		this.url = url;
	}

	@Override
	public void setGrid(String grid) {
		this.addStyleName("col " + grid);
	}

	/**
	 * @param fullscreen the fullscreen to set
	 */
	public void setFullscreen(boolean fullscreen) {
		frame.getElement().setAttribute("allowfullscreen", "true");
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
}
