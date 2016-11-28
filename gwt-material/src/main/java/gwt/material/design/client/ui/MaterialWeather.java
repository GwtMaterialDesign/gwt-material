/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2016 GwtMaterialDesign
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
package gwt.material.design.client.ui;

//@formatter:off

import com.google.gwt.user.client.ui.HTML;

/**
 * Material Weather - used OpenWeatherAPI to get results about location based  weather
 * <p>
 * <p>
 * <h3>UiBinder Usage:</h3>
 * <pre>
 * {@code
 *     <m:MaterialWeather location="Makati, Philippines" />
 * }
 * </pre>
 * </p>
 *
 * @author kevzlou7979
 * @author Ben Dol
 * @author paulux84
 *
 * @see <a href="http://gwtmaterialdesign.github.io/gwt-material-demo/#media">Material Weather</a>
 */
//@formatter:on
public class MaterialWeather extends MaterialPanel {

    public MaterialWeather() {
        super();
        HTML html = new HTML("<div id='weatherPanel' class='center-align card white-text'> <div class='row'> <ul id='weatherPanel' > <div class='col s12 m12 l6'> <li style='opacity: 0;'> <h5 >0</h5> <p style='margin-top: -5px; font-weight: 100;text-transform: capitalize;'>0</p> </li> <li style='opacity: 0;'> <div class='row'> <div class='col s12 m4 l4'> <img style='margin-top: 20px;'> </div> <div class='col s12 m8 l8'> <h2 style='font-weight: 100;'>0</h2> </div> </div> </li> </div> <div class='col s12 m12 l6'> <li style='opacity: 0;'> <h5>0</h5> <p style='margin-top: -5px; font-weight: 100;'>0</p> </li> <div class='left-align'> <li style='opacity: 0;'> <span><i > </i>0</span><br> </li> <li style='opacity: 0;'> <span><i class='mdi-device-wifi-tethering'> </i>3.9  mph</span><br> </li> <li style='opacity: 0;'> <span><i class='mdi-av-timer'></i>0  hPa</span> </li> </div> </div> </ul> </div> </div>");
        getElement().appendChild(html.getElement());
    }

    private String location;
    private String color;
    private String name;

    @Override
    protected void onLoad() {
        super.onLoad();

        name = "weatherContainer";
        addStyleName(name);
        getElement().setId("weatherContainer");
        showWeather(location, name, color);
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
        showWeather(location, name, color);
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public static native void showWeather(String location, String div, String color)/*-{
        $wnd.jQuery.getJSON("http://api.openweathermap.org/data/2.5/weather?q=" + location + "&APPID=53455a3a8a8a46135396f0272314f49d", function (data) {
            var items = [];
            var location = data.sys.country + ", " + data.name;
            var icon = "http://gwt-material.appspot.com/bin/weather/" + data.weather[0].icon + ".png";
            var temp = Math.round((data.main.temp - 273.15) * 10) / 10;

            var main = data.weather[0].main;
            var desc = data.weather[0].description;
            var humidity = data.main.humidity;
            var pressure = data.main.pressure;
            var wind = data.wind.speed;
            var d = new Date();
            var weekday = new Array(7);
            weekday[0] = "Sunday";
            weekday[1] = "Monday";
            weekday[2] = "Tuesday";
            weekday[3] = "Wednesday";
            weekday[4] = "Thursday";
            weekday[5] = "Friday";
            weekday[6] = "Saturday";

            var today = weekday[d.getDay()];

            items.push("<div id='weatherPanel' class='center-align card white-text " + color + "'> <div class='row'> <ul id='weatherPanel' > <div class='col s12 m12 l6'> <li style='opacity: 0;'> <h5 >" + main + "</h5> <p style='margin-top: -5px; font-weight: 100;text-transform: capitalize;'>" + desc + "</p> </li> <li style='opacity: 0;'> <div class='row'> <div class='col s12 m4 l4'> <img src='" + icon + "' style='margin-top: 20px;'> </div> <div class='col s12 m8 l8'> <h2 style='font-weight: 100;'>" + temp + "</h2> </div> </div> </li> </div> <div class='col s12 m12 l6'> <li style='opacity: 0;'> <h5>" + location + "</h5> <p style='margin-top: -5px; font-weight: 100;'>" + today + "</p> </li> <div class='left-align'> <li style='opacity: 0;'> <span><i class='material-icons left' style='font-size: medium;'>invert_colors</i> " + humidity + "%</span><br> </li> <li style='opacity: 0;'> <span><i class='material-icons left' style='font-size: medium;'>wifi_tethering</i>" + wind + " mph</span><br> </li> <li style='opacity: 0;'> <span><i class='material-icons left' style='font-size: medium;'>av_timer</i>" + pressure + "  hPa</span> </li> </div> </div> </ul> </div> </div>");
            $wnd.document.getElementById('weatherContainer').innerHTML = '';
            $wnd.jQuery("<ul/>", {"class": "my-new-list", html: items.join("")}).appendTo("." + div);

            $wnd.Materialize.showStaggeredList('#weatherPanel');
        });
    }-*/;
}
