#include "gripper.h"
#include <iostream>
#include <cmath>

// Initialize static member variables
int Gripper::_width{0};
int Gripper::_speed{0};
int Gripper::_force{0};


Gripper::Gripper() {
}

void Gripper::setWidth(int width) {
    _width = width;
}

int Gripper::getWidth(){
    return _width;
}

void Gripper::grip(std::string width, std::string speed, std::string force) {
    int vWidth = lrint(stod(width)*scale);
    int vSpeed = lrint(stod(speed)*scale);
    int vForce = lrint(stod(force)*scale);

    while (vWidth != _width && vForce != _force) {
        if (vWidth < _width && vForce < _force) {
            _width--;
        } else if (vWidth > _width && vForce < _force) {
            _width++;
        }
    }
    std::cout << _width << std::endl;
    std::cout << "grip done" << std::endl;
}

void Gripper::release(std::string width, std::string speed) {
    int vWidth = lrint(stod(width)*scale);
    int vSpeed = lrint(stod(speed)*scale);

    while (vWidth != _width) {
        if (vWidth < _width) {
            _width--;
        } else if (vWidth > _width) {
            _width++;
        }
    }

    std::cout << "release done" << std::endl;
}
