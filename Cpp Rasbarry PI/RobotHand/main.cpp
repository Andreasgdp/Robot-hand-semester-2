#include "MainWindow.h"
#include <QApplication>

#define WIN32_LEAN_AND_MEAN

#include <iostream>
#include <stdexcept>
#include <cassert>
#include <string>
#include <thread>
#include "gripper.h"
#include <wiringPi.h> /* include wiringPi library */
#include <stdio.h>
#include <softPwm.h>  /* include header file for software PWM */

#ifdef _WIN32
#include <windows.h>
#else
#include <unistd.h>
#endif

#include <xmlrpc-c/base.hpp>
#include <xmlrpc-c/registry.hpp>
#include <xmlrpc-c/server_abyss.hpp>

#ifdef _WIN32
#define SLEEP(seconds) SleepEx(seconds * 1000);
#else
#define SLEEP(seconds) sleep(seconds);
#endif

//Create a gripper object
Gripper gripper;

class ConnCheck : public xmlrpc_c::method {
public:
    ConnCheck() {
        this->_signature = "i:";
        this->_help = "Connection check function";
    }
    void execute(xmlrpc_c::paramList const& paramList, xmlrpc_c::value* const retvalP) {
        std::cout << "Connection check!" << std::endl;

        *retvalP = xmlrpc_c::value_int(42);
    }
};

class Grip : public xmlrpc_c::method {
public:
    Grip() {
        this->_signature = "i:";
        this->_help = "Grip function";
    }
    void execute(xmlrpc_c::paramList const& paramList, xmlrpc_c::value* const retvalP) {

        std::string const width(paramList.getString(0));
        std::string const speed(paramList.getString(1));
        std::string const force(paramList.getString(2));
        paramList.verifyEnd(3);

        std::cout << "Grip! - " << "Width: " << width << ", Speed: " << speed << ", Force: " << force << std::endl;

        gripper.grip(width, speed, force);
        SLEEP(2); // Call gripper function with parameters

        *retvalP = xmlrpc_c::value_int(42);
    }
};

class Release : public xmlrpc_c::method {
public:
    Release() {
        this->_signature = "i:";
        this->_help = "Release function";
    }
    void execute(xmlrpc_c::paramList const& paramList, xmlrpc_c::value* const retvalP) {
        std::string const width(paramList.getString(0));
        std::string const speed(paramList.getString(1));
        paramList.verifyEnd(2);

        std::cout << "Release! - " << "Width: " << width << ", Speed: " << speed << std::endl;

        gripper.release(width, speed);
        SLEEP(2); // Call gripper function with parameters

        *retvalP = xmlrpc_c::value_int(42);
    }
};

void server(){
    std::cout << "Starting XML_RPC Server" << std::endl;
    try {
        xmlrpc_c::registry myRegistry;

        xmlrpc_c::methodPtr const connCheck(new ConnCheck);
        xmlrpc_c::methodPtr const grip(new Grip);
        xmlrpc_c::methodPtr const release(new Release);

        myRegistry.addMethod("connCheck", connCheck);
        myRegistry.addMethod("grip", grip);
        myRegistry.addMethod("release", release);

        xmlrpc_c::serverAbyss myAbyssServer(xmlrpc_c::serverAbyss::constrOpt().registryP(&myRegistry).portNumber(5000));

        myAbyssServer.run();

        assert(false);

    }  catch (std::exception const& e) {
        std::cerr << "Something failed. " << e.what() << std::endl;
    }
}

void motorRamp(){
    int PWM_pin = 1;                /* GPIO1 as per WiringPi,GPIO18 as per BCM */
    int intensity = gripper.getSpeed();
    wiringPiSetup();                /* initialize wiringPi setup */
    pinMode(PWM_pin,OUTPUT);        /* set GPIO as output */
    softPwmCreate(PWM_pin,1,100);	/* set PWM channel along with range*/
    if (gripper.getRun() == 1) {
        softPwmWrite (PWM_pin, intensity);
    std::cout << intensity << std::endl; //for testing to see if the variable changes
        delay(1);
    }
}

int main(int argc, char *argv[])
{
    std::thread first (server);
    std::thread second (motorRamp);
    QApplication a(argc, argv);
    MainWindow w;
    w.show();

    return a.exec();
}

