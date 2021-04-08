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
    delete ui;
}


void MainWindow::on_btConnect_clicked()
{

}

void MainWindow::on_btOpen_clicked()
{
    g.setWidth(g.getWidth()+1);
    std::cout << g.getWidth() << std::endl;
}

void MainWindow::on_btClose_clicked()
{
    g.setWidth(g.getWidth()-1);
    std::cout << g.getWidth() << std::endl;
}

void MainWindow::on_lConnect_textEdited(const QString &arg1)
{
    ipInputString = arg1.toUtf8().constData();
}
