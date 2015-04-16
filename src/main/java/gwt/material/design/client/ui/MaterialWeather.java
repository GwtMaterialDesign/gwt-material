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
		String name = "weatherPanel";
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
		$wnd.jQuery.getJSON( "http://api.openweathermap.org/data/2.5/weather?q=" + location, function( data ) {
 
		  var items = [];
		  var location = data.sys.country + ", " + data.name;
		  var icon = "http://gwt-material.appspot.com/bin/weather/" +data.weather[0].icon + ".png"
		  var temp = Math.round((data.main.temp - 273.15)* 10 ) / 10;
		  
		  var main = data.weather[0].main;
		  var desc = data.weather[0].description;
		  var humidity = data.main.humidity;
		  var pressure = data.main.pressure;
		  var d = new Date();
		  var weekday = new Array(7);
		  weekday[0]=  "Sunday";
		  weekday[1] = "Monday";
		  weekday[2] = "Tuesday";
		  weekday[3] = "Wednesday";
		  weekday[4] = "Thursday";
		  weekday[5] = "Friday";
		  weekday[6] = "Saturday";
		
		  var today = weekday[d.getDay()];
		  
		  items.push("<div class='center-align card white-text "+color+"'> <div class='row'> <ul id='weatherPanel' > <div class='col s12 m12 l6'> <li style='opacity: 0;'> <h5 >"+main+"</h5> <p style='margin-top: -5px; font-weight: 100;text-transform: capitalize;'>"+desc+"</p> </li> <li style='opacity: 0;'> <div class='row'> <div class='col s12 m4 l4'> <img src='"+icon+"' style='margin-top: 20px;'> </div> <div class='col s12 m8 l8'> <h2 style='font-weight: 100;'>"+temp+"</h2> </div> </div> </li> </div> <div class='col s12 m12 l6'> <li style='opacity: 0;'> <h5>"+location+"</h5> <p style='margin-top: -5px; font-weight: 100;'>"+today+"</p> </li> <div class='left-align'> <li style='opacity: 0;'> <span><i class='mdi-communication-invert-colors-on'> </i> "+humidity+"%</span><br> </li> <li style='opacity: 0;'> <span><i class='mdi-device-wifi-tethering'> </i>3.9  mph</span><br> </li> <li style='opacity: 0;'> <span><i class='mdi-av-timer'></i>"+pressure+"  hPa</span> </li> </div> </div> </ul> </div> </div>");
		
		
		  $wnd.jQuery( "<ul/>", { "class": "my-new-list",  html: items.join( "" ) }).appendTo( "." + div );
			
		  $wnd.Materialize.showStaggeredList('#weatherPanel');
		});
		
		
	}-*/;

}
