#ifndef GRIPPER_H
#define GRIPPER_H
#include <string>


class Gripper
{
public:
    Gripper();
    void grip(std::string width, std::string speed, std::string force);
    void release(std::string width, std::string speed);
    void setWidth(int width);
    int getWidth();
    bool getRun();
    static int getSpeed();
    static void setSpeed(int speed);

private:
    static int _width, _speed, _force;
    static bool run;
    int scale = 1000;
};

#endif // GRIPPER_H
