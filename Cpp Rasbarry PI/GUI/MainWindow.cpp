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


void MainWindow::on_btConnect_clicked()
{
    succesString = ipInputString + " proof";
}

void MainWindow::on_btOpen_clicked()
{
    gripperState = 1;
}

void MainWindow::on_btClose_clicked()
{
    gripperState = -1;
}

void MainWindow::on_lConnect_textEdited(const QString &arg1)
{
    ipInputString = arg1.toUtf8().constData();
}
