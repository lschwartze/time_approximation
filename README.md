# time_approximation
Creates a digital clock that displays the approximated time with a standard distribution of 60 seconds.

# screensaver
This project wraps the time approximation file into a screensaver.
First, the java file has been converted to a .jar file by exporting the main class directly from eclipse.
The jar file is executable but can not be converted to a .scr file, which can be used as a screensaver.
For that, the jar file needs to be converted to a .exe which is executable by windows. 
I used launch4j for this process which can be downloaded here: https://sourceforge.net/projects/launch4j/files/launch4j-3/
At first the conversion produced an .exe that couldn't be run by windows. I found that the problem can be solved 
by setting the JAVA_HOME system-variable to the jdk. This can be done like here: https://confluence.atlassian.com/doc/setting-the-java_home-variable-in-windows-8895.html
When the .exe was created, I simply renamed the file with a .scr file format. The screensaver will be dependent on the JRE because I couldn't find a better wrapper.

The previous steps can be skipped, if one downloads the screensaver.scr file in this repo.
To select this file as a screensaver, copy it into the C:\Windows\System32 folder. Navigate to the screensaver settings and the file should be available there.
Other screensaver applications are able to show a preview, this is not currently possible but may be added in the future.
