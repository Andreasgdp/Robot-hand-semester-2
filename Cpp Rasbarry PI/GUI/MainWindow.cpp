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
    std::cout << "boo" << std::endl;
}

void MainWindow::on_pushButton_clicked()
{
    std::cout << "Hoo" << std::endl;
}
