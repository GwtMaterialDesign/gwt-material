/*
 * #%L
 * GwtMaterial
 * %%
 * Copyright (C) 2015 - 2017 GwtMaterialDesign
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

import java.io.*;
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

    private static String ijmapSource = "MaterialIcons-Regular.ijmap";
    private static String templateSource = "IconTypeTemplate.txt";
    private static String output = "IconType.java";

    public static void main(String[] args) throws IOException {
        System.out.println("Generating the IconType class...");

        File ijmap = new File(ijmapSource);
        if (!ijmap.isFile()) {
            throw new FileNotFoundException(ijmap.getAbsolutePath());
        }
        File template = new File(templateSource);
        if (!template.isFile()) {
            throw new FileNotFoundException(template.getAbsolutePath());
        }


        StringBuilder builder = new StringBuilder();
        builder.append("DEFAULT(\"\")");

        BufferedReader reader;
        try {
            int count = 0;
            reader = new BufferedReader(new FileReader(ijmap));
            String line = reader.readLine();
            while (line != null) {
                // read next line
                line = reader.readLine();

                if (line != null && !line.isEmpty()) {
                    String typeName = "";
                    typeName = line.replace(line.substring(line.indexOf(" ")), "");
                    System.out.println(typeName);

                    boolean digit = Character.isDigit(typeName.charAt(0));
                    String prefix = "";
                    if (digit) {
                        prefix = "_";
                    }

                    builder.append(",\n    " + prefix).append(typeName.toUpperCase()).append("(\"").append(typeName).append("\")");
                }
                count++;
            }
            builder.append(";");
            reader.close();

            //loading the template java file
            String templateString = new String(Files.readAllBytes(Paths.get(template.toURI())), "UTF-8");
            templateString = templateString.replace("${generationDate}", getDateAsISO8601()).replace("${enumValues}", builder.toString());

            //time to write it to the destination
            File target = new File(output);
            Files.write(Paths.get(target.toURI()), templateString.getBytes("UTF-8"));

            System.out.println("Generation complete with " + count + " IconType constants.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        builder.append(";");
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
