/********************************************************************************
** Form generated from reading UI file 'MainWindow.ui'
**
** Created by: Qt User Interface Compiler version 6.0.0
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_MAINWINDOW_H
#define UI_MAINWINDOW_H

#include <QtCore/QVariant>
#include <QtWidgets/QApplication>
#include <QtWidgets/QHBoxLayout>
#include <QtWidgets/QLabel>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_MainWindow
{
public:
    QWidget *centralwidget;
    QHBoxLayout *horizontalLayout_4;
    QWidget *widget;
    QPushButton *ManualBottom;
    QPushButton *pushButton;
    QLabel *label;
    QStatusBar *statusbar;
    QMenuBar *menubar;

    void setupUi(QMainWindow *MainWindow)
    {
        if (MainWindow->objectName().isEmpty())
            MainWindow->setObjectName(QString::fromUtf8("MainWindow"));
        MainWindow->resize(672, 539);
        centralwidget = new QWidget(MainWindow);
        centralwidget->setObjectName(QString::fromUtf8("centralwidget"));
        horizontalLayout_4 = new QHBoxLayout(centralwidget);
        horizontalLayout_4->setObjectName(QString::fromUtf8("horizontalLayout_4"));
        widget = new QWidget(centralwidget);
        widget->setObjectName(QString::fromUtf8("widget"));
        ManualBottom = new QPushButton(widget);
        ManualBottom->setObjectName(QString::fromUtf8("ManualBottom"));
        ManualBottom->setEnabled(true);
        ManualBottom->setGeometry(QRect(0, -10, 80, 80));
        QSizePolicy sizePolicy(QSizePolicy::Minimum, QSizePolicy::Fixed);
        sizePolicy.setHorizontalStretch(0);
        sizePolicy.setVerticalStretch(0);
        sizePolicy.setHeightForWidth(ManualBottom->sizePolicy().hasHeightForWidth());
        ManualBottom->setSizePolicy(sizePolicy);
        ManualBottom->setMinimumSize(QSize(80, 80));
        ManualBottom->setMaximumSize(QSize(400, 400));
        ManualBottom->setStyleSheet(QString::fromUtf8("#ManualBottom {\n"
"background-color: transparent;\n"
"border-image: url(:toggle_left.jpg);\n"
"background: none;\n"
"border: none;\n"
"background-repeat: none;\n"
"}\n"
"#ManualBottom:pressed\n"
"{\n"
"   border-image: url(:toggle_right.jpg);\n"
"}"));
        ManualBottom->setCheckable(true);
        ManualBottom->setChecked(false);
        pushButton = new QPushButton(widget);
        pushButton->setObjectName(QString::fromUtf8("pushButton"));
        pushButton->setGeometry(QRect(160, 240, 75, 23));
        pushButton->setCheckable(false);
        label = new QLabel(widget);
        label->setObjectName(QString::fromUtf8("label"));
        label->setGeometry(QRect(160, 220, 81, 16));

        horizontalLayout_4->addWidget(widget);

        MainWindow->setCentralWidget(centralwidget);
        statusbar = new QStatusBar(MainWindow);
        statusbar->setObjectName(QString::fromUtf8("statusbar"));
        MainWindow->setStatusBar(statusbar);
        menubar = new QMenuBar(MainWindow);
        menubar->setObjectName(QString::fromUtf8("menubar"));
        menubar->setGeometry(QRect(0, 0, 672, 21));
        menubar->setDefaultUp(false);
        MainWindow->setMenuBar(menubar);

        retranslateUi(MainWindow);

        QMetaObject::connectSlotsByName(MainWindow);
    } // setupUi

    void retranslateUi(QMainWindow *MainWindow)
    {
        MainWindow->setWindowTitle(QCoreApplication::translate("MainWindow", "MainWindow", nullptr));
        ManualBottom->setText(QString());
        pushButton->setText(QCoreApplication::translate("MainWindow", "Push me ", nullptr));
        label->setText(QCoreApplication::translate("MainWindow", "YAS DADDY", nullptr));
    } // retranslateUi

};

namespace Ui {
    class MainWindow: public Ui_MainWindow {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_MAINWINDOW_H
