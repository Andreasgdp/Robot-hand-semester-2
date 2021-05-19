#include "gripper.h"
#include <iostream>
#include <cmath>
#include <wiringPi.h> /* include wiringPi library */
#include <stdio.h>
#include <softPwm.h>  /* include header file for software PWM */

// Initialize static member variables
int Gripper::_width{0};
int Gripper::_speed{100};
int Gripper::_force{0};
int Gripper::_run{0};


Gripper::Gripper() {
}

Gripper::~Gripper() {
    _width = 0;
    _speed = 0;
    _force = 0;
    _run = 0;
    wiringPiSetupGpio();       /* initialize wiringPi setup */
    pinMode(18,OUTPUT);        /* set GPIO as output */
    pinMode(17, OUTPUT);
    pinMode(27, OUTPUT);

    softPwmCreate(0,1,100);
    softPwmWrite (0, 0);
    std::cout << "Gripper is destructed" << std::endl;
}

void Gripper::setWidth(int width) {
    _width = width;
}

int Gripper::getWidth() {
    return _width;
}

int Gripper::getRun() {
    return _run;
}

int Gripper::getSpeed() {
    return _speed;
}

void Gripper::setSpeed(int speed) {
    _speed = speed;
}

void Gripper::setRun(int value)
{
    _run = value;
}

void Gripper::grip(std::string width, std::string speed, std::string force) {
    int vWidth = lrint(stod(width)*scale);
    int vSpeed = lrint(stod(speed)*scale);
    int vForce = lrint(stod(force)*scale);
    _run = 1;
    _speed = vSpeed;
    while (_run == 1) {

    }
    std::cout << "grip done" << std::endl;

}

void Gripper::release(std::string width, std::string speed) {
    int vWidth = lrint(stod(width)*scale);
    int vSpeed = lrint(stod(speed)*scale);
    _speed = vSpeed;
    _run = 2;
    while (_run == 2) {

    }

    std::cout << "release done" << std::endl;

}
