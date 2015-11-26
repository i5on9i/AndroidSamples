

set javah=c:\Program Files\Java\jdk1.7.0_25\bin\javah.exe
set classpath=d:\Android\sdk\platforms\android-23\android.jar
set v7appcompat=d:\Android\sdk\extras\android\support\v7\appcompat\libs\android-support-v7-appcompat.jar
set v4appcompat=d:\Android\sdk\extras\android\support\v7\appcompat\libs\android-support-v4.jar
set classname=com.namh.ndkhelloworld2.MainActivity


"%javah%" -d jni -classpath "%classpath%";"%v7appcompat%";"%v4appcompat%";..\..\build\intermediates\classes\debug %classname%