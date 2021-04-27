#include "gripper.h"
#include <iostream>
#include <cmath>
#include <wiringPi.h> /* include wiringPi library */
#include <stdio.h>
#include <softPwm.h>  /* include header file for software PWM */

// Initialize static member variables
int Gripper::_width{0};
int Gripper::_speed{25};
int Gripper::_force{0};
int Gripper::run{0};


Gripper::Gripper() {
}

Gripper::~Gripper()
{
    _width = 0;
    _speed = 0;
    _force = 0;
    run = 0;
    wiringPiSetupGpio();                /* initialize wiringPi setup */
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

int Gripper::getWidth(){
    return _width;
}

int Gripper::getRun(){
    return run;
}

int Gripper::getSpeed()
{
    return _speed;
}

void Gripper::setSpeed(int speed)
{
    _speed = speed;
}

void Gripper::setRun(int value)
{
    run = value;
}

void Gripper::grip(std::string width, std::string speed, std::string force) {
    int vWidth = lrint(stod(width)*scale);
    int vSpeed = lrint(stod(speed)*scale);
    int vForce = lrint(stod(force)*scale);
    run = 1;
    _speed = vSpeed;
    while (vWidth != _width && vForce != _force) {
        if (vWidth < _width && vForce < _force) {
            _width--;
        } else if (vWidth > _width && vForce < _force) {
            _width++;
        }
    }
    std::cout << _width << std::endl;
    std::cout << "grip done" << std::endl;
    run = 0;
}

void Gripper::release(std::string width, std::string speed) {
    int vWidth = lrint(stod(width)*scale);
    int vSpeed = lrint(stod(speed)*scale);
    _speed = vSpeed;
    run = 2;
    while (vWidth != _width) {
        if (vWidth < _width) {
            _width--;
        } else if (vWidth > _width) {
            _width++;
        }
    }

    std::cout << "release done" << std::endl;
    run = 0;
}
