package gwt.material.design.client.ui;

import com.google.gwt.safehtml.shared.SafeHtml;

public class MaterialWeather extends MaterialPanel {

	private String location;
	private String color;

	public MaterialWeather(SafeHtml safeHtml) {
		super(safeHtml);
		// TODO Auto-generated constructor stub
	}

	public MaterialWeather(String tag, String html) {
		super(tag, html);
		// TODO Auto-generated constructor stub
	}

	public MaterialWeather(String html) {
		super(html);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onAttach() {
		// TODO Auto-generated method stub
		super.onAttach();
		String name = String.valueOf(hashCode());
		this.addStyleName(name);
		showWeather(location, name, color);
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public static native void showWeather(String location, String div, String color)/*-{
		$wnd.jQuery
				.getJSON(
						"http://api.openweathermap.org/data/2.5/weather?q="
								+ location,
						function(data) {
							var items = [];
							var location = data.sys.country + ", " + data.name;
							var icon = "http://openweathermap.org/img/w/"
									+ data.weather[0].icon + ".png"
							var temp = data.main.temp - 273.15;

							var main = data.weather[0].main;
							var desc = data.weather[0].description;
							var humidity = data.main.humidity;
							var pressure = data.main.pressure;
							items
									.push("<div class='row'> <div class='col s12 m12'> <div class='card white'> <h5 class='"
											+ color
											+ " white-text'  style='padding: 20px; margin-top: -5px;'>"
											+ location
											+ "</h5> <div class='card-content'> <div class='row "
											+ color
											+ "-text'> <div class='col s6 m6 l6'> <div class='col s3 m3 l3'> <p><img id='1' style='margin-top: 10px;' src='"
											+ icon
											+ "'></p> </div> <div class='col s8 m8 l8'> <h4>"
											+ main
											+ "</h4> <p>"
											+ desc
											+ "</p> </div> </div> <div class='col s6 m6 l6'> <h4>"
											+ Math.round(temp * 10) / 10
											+ "&#8451</h4> </div> </div> </div> <div class='card-action'> <a class='"
											+ color
											+ "-text' style='font-size: 0.7em;'>Humidity: "
											+ humidity
											+ "&#37</a> <a class='"
											+ color
											+ "-text' style='font-size: 0.7em;'>Pressure: "
											+ pressure
											+ "</a> </div> </div> </div> </div>");

							$wnd.jQuery("<ul/>", {
								"class" : "my-new-list",
								html : items.join("")
							}).appendTo("." + div);
						});
	}-*/;

}
