#include "MainWindow.h"
#include "ui_MainWindow.h"
#include <iostream>
#include "gripper.h"


//Create a gripper object
Gripper g;


MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow)
{
    ui->setupUi(this);
    ui->lcdSpeed->display(g.getSpeed());
    ui->lcdWidth->display(g.getWidth());
    ui->leSpeed->setMaxLength(3);
}

MainWindow::~MainWindow()
{
    g.~Gripper();
    delete ui;
}

void MainWindow::on_btConnect_clicked()
{
    if(g.getRun() == 0) {

    } else {
        g.setRun(0);
        std::cout << g.getRun() << std::endl;
    }
}

void MainWindow::on_btOpen_clicked()
{
    if(g.getRun() == 2) {
        g.setRun(0);
        std::cout << g.getRun() << std::endl;
    } else {
        g.setRun(2);
        std::cout << g.getRun() << std::endl;
    }
}

void MainWindow::on_btClose_clicked()
{
    if(g.getRun() == 1) {
        g.setRun(0);
        std::cout << g.getRun() << std::endl;
    } else {
        g.setRun(1);
        std::cout << g.getRun() << std::endl;
    }
}

void MainWindow::on_leSpeed_textChanged(const QString &arg1)
{
    ipInputString = arg1.toUtf8().constData();
}




void MainWindow::on_leSpeed_returnPressed()
{
    bool ok;
    int speed = (ui->leSpeed->text()).toInt(&ok);
    if (ok){
    ui->lcdSpeed->display(speed);
    g.setSpeed(speed);
    }
    else {
        std::cout << "Invalid speed" << std::endl;
    }
}

void MainWindow::on_btSetSpeed_clicked()
{
    bool ok;
    int speed = (ui->leSpeed->text()).toInt(&ok);
    if (ok){
        if (speed > 100) {
            std::cout << "Speed capped at 100%" << std::endl;
            speed = 100;
        }
        else if (speed < 0) {
            std::cout << "Speed cannot be negative" << std::endl;
        }
        else {
            ui->lcdSpeed->display(speed);
            g.setSpeed(speed);
            ui->leSpeed->clear();
        }
    }
    else {
        std::cout << "Invalid speed" << std::endl;
    }
}


void MainWindow::on_pushButton_clicked()
{
    ui->lcdSpeed->display(g.getSpeed());
    ui->lcdWidth->display(g.getWidth());
}
