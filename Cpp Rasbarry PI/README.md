# Cpp program to run on Rasberry PI and PC
1. Create a QT application to get a GUI.
2. In the .pro file of the project you must include this:
-----------------------------------------------
  # WiringPi Libary
  INCLUDEPATH += /home/pi/sysroot/usr/include/
  LIBS += -L"/home/pi/sysroot/usr/lib"
  LIBS += -lwiringPi
-----------------------------------------------
This is the the library that gives control of the PI.
3. This library may need to be build in the terminal. Follow this: http://wiringpi.com/download-and-install/
4. To get the server working you must build the XML-RPC library. Follow this: http://xmlrpc-c.sourceforge.net 
5. Again you must include the library in the .pro file
-----------------------------------------------
   # XML-RPC Library
  LIBS += -Lusr/local/lib/ -lxmlrpc++
  LIBS += -Lusr/local/lib/ -lxmlrpc_util++
  LIBS += -Lusr/local/lib/ -lxmlrpc_server++
  LIBS += -Lusr/local/lib/ -lxmlrpc_server_abyss++
-----------------------------------------------
6. Now you should have everything needed to make the program.
