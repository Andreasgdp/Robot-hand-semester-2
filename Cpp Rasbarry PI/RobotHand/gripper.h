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
private:
    static int _width;
    static int _speed;
    static int _force;
};

#endif // GRIPPER_H
