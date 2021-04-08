#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>

QT_BEGIN_NAMESPACE
namespace Ui { class MainWindow; }
QT_END_NAMESPACE

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    MainWindow(QWidget *parent = nullptr);
    ~MainWindow();

    int gripperState = 0;
    std::string ipInputString = "";
    std::string succesString = "";

private slots:
    void on_btConnect_clicked();

    void on_btOpen_clicked();

    void on_btClose_clicked();

    void on_lConnect_textEdited(const QString &arg1);

private:
    Ui::MainWindow *ui;
};
#endif // MAINWINDOW_H
