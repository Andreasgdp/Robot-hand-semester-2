#include "MainWindow.h"
#include "ui_MainWindow.h"
#include <iostream>

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

void MainWindow::on_ManualBottom_clicked(bool checked)
{
    if (checked == true){
        std::cout << "boo" << std::endl;
    }
    else {
        std::cout << "Hoo" << std::endl;
    }
}
void MainWindow::on_ManualBottom_released()
{

}

void MainWindow::on_pushButton_clicked()
{
    std::cout << "Push me daddy" << std::endl;
}


