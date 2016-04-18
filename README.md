# What is it good for?

This tool reads all XML files in a directory (and its subdirectories) and provides a list of all 
distinctive XML elements. For each element, its name and the names of its attributes are returned.
The same element can appear several times if it has several different combinations of attributes.
The whole list is written into a text file.

# Usage

You need to make sure that Java version 6 or higher is installed on your system.

To run the tool, you need to provide the parent directory containing your XML files and also 
the name for the destination text file:

java -jar fwb-xml-reader.jar <input-dir> <output-file>

For example:

java -jar fwb-xml-reader.jar my-files/xml all-elements.txt

# Compilation

To compile the project, you need to have Maven 3 installed. Navigate into the project directory 
and execute the command:

mvn clean package

The .jar file will be placed into the target/ subdirectory.