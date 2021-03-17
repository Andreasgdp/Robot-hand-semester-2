#include "MainWindow.h"
#include "ui_MainWindow.h"

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
    gripperState = 1;
}

void MainWindow::on_btClose_clicked()
{
    gripperState = 0;
}
