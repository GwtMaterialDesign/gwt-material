package gwt.material.design.client.ui;

import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.user.client.ui.Frame;
import com.google.gwt.user.client.ui.HTMLPanel;

public class MaterialVideo extends HTMLPanel{

	private String url="";
	
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
		this.addStyleName("video-container");
		this.add(new Frame(url));
		this.url = url;
	}

	
}
