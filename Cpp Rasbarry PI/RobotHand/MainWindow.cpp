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

void MainWindow::on_lConnect_textEdited(const QString &arg1)
{
    ipInputString = arg1.toUtf8().constData();
}
