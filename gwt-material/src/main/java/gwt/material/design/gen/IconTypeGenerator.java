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
package gwt.material.design.gen;

import gwt.material.design.client.constants.IconType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Generator for the {@link IconType} class. It parses the MaterialIcons-Regular.ijmap file and creates
 * the IconType.java file.
 *
 * @author gilberto-torrezan
 */
public class IconTypeGenerator {

    public static void main(String[] args) throws IOException {
        System.out.println("Generating the IconType class...");

        File ijmap = new File("src/main/resources/gwt/material/design/public/font/material-icons/MaterialIcons-Regular.ijmap");
        if (!ijmap.isFile()) {
            throw new FileNotFoundException(ijmap.getAbsolutePath());
        }
        File template = new File("src/main/java/gwt/material/design/gen/IconTypeTemplate.txt");
        if (!template.isFile()) {
            throw new FileNotFoundException(template.getAbsolutePath());
        }

        //parses the ijmap file by using a regex
        //to parse it as a json, on Java 7, an external library should be used
        String iconsFile = new String(Files.readAllBytes(Paths.get(ijmap.toURI())), "UTF-8");
        Pattern pattern = Pattern.compile("\\{\\s*\"name\"\\s*:\\s*\"([^\"]*)\"\\s*\\}");
        Matcher matcher = pattern.matcher(iconsFile);

        StringBuilder builder = new StringBuilder();

        //adds the default constant
        builder.append("DEFAULT(\"\")");
        int count = 1;

        while (matcher.find()) {
            String name = matcher.group(1);
            String cssName = name.toLowerCase().replace(' ', '_');

            //when a name starts with a number, the type name must be changed
            while (name.matches("\\d.*")) { //starts with a number
                int firstSpace = name.indexOf(' ');
                String num = name.substring(0, firstSpace);
                name = name.substring(firstSpace + 1) + '_' + num;
            }
            String typeName = name.toUpperCase().replace(' ', '_');

            //appends to the document
            builder.append(",\n    ").append(typeName).append("(\"").append(cssName).append("\")");
            count++;
        }
        builder.append(";");

        //loading the template java file
        String templateString = new String(Files.readAllBytes(Paths.get(template.toURI())), "UTF-8");
        templateString = templateString.replace("${generationDate}", getDateAsISO8601()).replace("${enumValues}", builder.toString());

        //time to write it to the destination
        File target = new File("src/main/java/gwt/material/design/client/constants/IconType.java");
        Files.write(Paths.get(target.toURI()), templateString.getBytes("UTF-8"));

        System.out.println("Generation complete with " + count + " IconType constants.");
    }

    /*
     * Needed by the @Generated annotation 
     */
    private static String getDateAsISO8601() {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        df.setTimeZone(tz);
        return df.format(new Date());
    }

}
