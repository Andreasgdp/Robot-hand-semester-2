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
#include <QtGui/QIcon>
#include <QtWidgets/QApplication>
#include <QtWidgets/QGridLayout>
#include <QtWidgets/QHBoxLayout>
#include <QtWidgets/QLCDNumber>
#include <QtWidgets/QLabel>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QSpacerItem>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QVBoxLayout>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_MainWindow
{
public:
    QWidget *centralwidget;
    QHBoxLayout *horizontalLayout_4;
    QWidget *widget;
    QHBoxLayout *horizontalLayout_5;
    QWidget *widget_2;
    QVBoxLayout *verticalLayout_3;
    QWidget *Connectwidget;
    QHBoxLayout *horizontalLayout;
    QLabel *SpeedLabel;
    QLineEdit *leSpeed;
    QPushButton *btSetSpeed;
    QLabel *label;
    QWidget *Gripperwidget;
    QHBoxLayout *horizontalLayout_2;
    QPushButton *btOpen;
    QPushButton *btConnect;
    QPushButton *btClose;
    QLabel *label_2;
    QWidget *Advancetwidget;
    QHBoxLayout *horizontalLayout_3;
    QLabel *label_3;
    QLineEdit *lSetWidth;
    QLabel *label_5;
    QPushButton *btGoto;
    QLabel *label_6;
    QWidget *Variabelwidget;
    QGridLayout *gridLayout_3;
    QLabel *label_10;
    QLabel *label_9;
    QLabel *label_7;
    QLCDNumber *lcdSpeed;
    QLCDNumber *lcdWidth;
    QLabel *label_8;
    QSpacerItem *verticalSpacer;
    QStatusBar *statusbar;
    QMenuBar *menubar;

    void setupUi(QMainWindow *MainWindow)
    {
        if (MainWindow->objectName().isEmpty())
            MainWindow->setObjectName(QString::fromUtf8("MainWindow"));
        MainWindow->resize(800, 600);
        centralwidget = new QWidget(MainWindow);
        centralwidget->setObjectName(QString::fromUtf8("centralwidget"));
        horizontalLayout_4 = new QHBoxLayout(centralwidget);
        horizontalLayout_4->setObjectName(QString::fromUtf8("horizontalLayout_4"));
        widget = new QWidget(centralwidget);
        widget->setObjectName(QString::fromUtf8("widget"));
        horizontalLayout_5 = new QHBoxLayout(widget);
        horizontalLayout_5->setObjectName(QString::fromUtf8("horizontalLayout_5"));
        horizontalLayout_5->setContentsMargins(0, 0, 0, 0);
        widget_2 = new QWidget(widget);
        widget_2->setObjectName(QString::fromUtf8("widget_2"));
        verticalLayout_3 = new QVBoxLayout(widget_2);
        verticalLayout_3->setObjectName(QString::fromUtf8("verticalLayout_3"));
        Connectwidget = new QWidget(widget_2);
        Connectwidget->setObjectName(QString::fromUtf8("Connectwidget"));
        horizontalLayout = new QHBoxLayout(Connectwidget);
        horizontalLayout->setObjectName(QString::fromUtf8("horizontalLayout"));
        horizontalLayout->setContentsMargins(0, 0, 0, 0);
        SpeedLabel = new QLabel(Connectwidget);
        SpeedLabel->setObjectName(QString::fromUtf8("SpeedLabel"));
        QFont font;
        font.setBold(true);
        SpeedLabel->setFont(font);
        SpeedLabel->setTextFormat(Qt::RichText);

        horizontalLayout->addWidget(SpeedLabel);

        leSpeed = new QLineEdit(Connectwidget);
        leSpeed->setObjectName(QString::fromUtf8("leSpeed"));

        horizontalLayout->addWidget(leSpeed);

        btSetSpeed = new QPushButton(Connectwidget);
        btSetSpeed->setObjectName(QString::fromUtf8("btSetSpeed"));

        horizontalLayout->addWidget(btSetSpeed);


        verticalLayout_3->addWidget(Connectwidget);

        label = new QLabel(widget_2);
        label->setObjectName(QString::fromUtf8("label"));
        label->setFont(font);
        label->setTextFormat(Qt::RichText);
        label->setAlignment(Qt::AlignBottom|Qt::AlignHCenter);

        verticalLayout_3->addWidget(label);

        Gripperwidget = new QWidget(widget_2);
        Gripperwidget->setObjectName(QString::fromUtf8("Gripperwidget"));
        Gripperwidget->setAutoFillBackground(false);
        horizontalLayout_2 = new QHBoxLayout(Gripperwidget);
        horizontalLayout_2->setObjectName(QString::fromUtf8("horizontalLayout_2"));
        horizontalLayout_2->setContentsMargins(0, 0, 0, 0);
        btOpen = new QPushButton(Gripperwidget);
        btOpen->setObjectName(QString::fromUtf8("btOpen"));
        QPalette palette;
        QBrush brush(QColor(0, 255, 0, 255));
        brush.setStyle(Qt::SolidPattern);
        palette.setBrush(QPalette::Active, QPalette::ButtonText, brush);
        palette.setBrush(QPalette::Inactive, QPalette::ButtonText, brush);
        QBrush brush1(QColor(120, 120, 120, 255));
        brush1.setStyle(Qt::SolidPattern);
        palette.setBrush(QPalette::Disabled, QPalette::ButtonText, brush1);
        btOpen->setPalette(palette);
        btOpen->setAutoFillBackground(true);

        horizontalLayout_2->addWidget(btOpen);

        btConnect = new QPushButton(Gripperwidget);
        btConnect->setObjectName(QString::fromUtf8("btConnect"));
        QPalette palette1;
        QBrush brush2(QColor(0, 249, 0, 255));
        brush2.setStyle(Qt::SolidPattern);
        palette1.setBrush(QPalette::Active, QPalette::Button, brush2);
        QBrush brush3(QColor(0, 0, 255, 255));
        brush3.setStyle(Qt::SolidPattern);
        palette1.setBrush(QPalette::Active, QPalette::ButtonText, brush3);
        palette1.setBrush(QPalette::Inactive, QPalette::Button, brush2);
        palette1.setBrush(QPalette::Inactive, QPalette::ButtonText, brush3);
        palette1.setBrush(QPalette::Disabled, QPalette::Button, brush2);
        palette1.setBrush(QPalette::Disabled, QPalette::ButtonText, brush1);
        btConnect->setPalette(palette1);
        QIcon icon;
        QString iconThemeName = QString::fromUtf8("Green");
        if (QIcon::hasThemeIcon(iconThemeName)) {
            icon = QIcon::fromTheme(iconThemeName);
        } else {
            icon.addFile(QString::fromUtf8("."), QSize(), QIcon::Normal, QIcon::Off);
        }
        btConnect->setIcon(icon);

        horizontalLayout_2->addWidget(btConnect);

        btClose = new QPushButton(Gripperwidget);
        btClose->setObjectName(QString::fromUtf8("btClose"));
        QPalette palette2;
        QBrush brush4(QColor(255, 38, 0, 255));
        brush4.setStyle(Qt::SolidPattern);
        palette2.setBrush(QPalette::Active, QPalette::Button, brush4);
        palette2.setBrush(QPalette::Active, QPalette::ButtonText, brush4);
        palette2.setBrush(QPalette::Inactive, QPalette::Button, brush4);
        palette2.setBrush(QPalette::Inactive, QPalette::ButtonText, brush4);
        palette2.setBrush(QPalette::Disabled, QPalette::Button, brush4);
        palette2.setBrush(QPalette::Disabled, QPalette::ButtonText, brush1);
        btClose->setPalette(palette2);

        horizontalLayout_2->addWidget(btClose);


        verticalLayout_3->addWidget(Gripperwidget);

        label_2 = new QLabel(widget_2);
        label_2->setObjectName(QString::fromUtf8("label_2"));
        label_2->setFont(font);
        label_2->setTextFormat(Qt::RichText);

        verticalLayout_3->addWidget(label_2);

        Advancetwidget = new QWidget(widget_2);
        Advancetwidget->setObjectName(QString::fromUtf8("Advancetwidget"));
        horizontalLayout_3 = new QHBoxLayout(Advancetwidget);
        horizontalLayout_3->setObjectName(QString::fromUtf8("horizontalLayout_3"));
        horizontalLayout_3->setContentsMargins(0, 0, 0, 0);
        label_3 = new QLabel(Advancetwidget);
        label_3->setObjectName(QString::fromUtf8("label_3"));
        label_3->setTextFormat(Qt::PlainText);

        horizontalLayout_3->addWidget(label_3);

        lSetWidth = new QLineEdit(Advancetwidget);
        lSetWidth->setObjectName(QString::fromUtf8("lSetWidth"));

        horizontalLayout_3->addWidget(lSetWidth);

        label_5 = new QLabel(Advancetwidget);
        label_5->setObjectName(QString::fromUtf8("label_5"));

        horizontalLayout_3->addWidget(label_5);

        btGoto = new QPushButton(Advancetwidget);
        btGoto->setObjectName(QString::fromUtf8("btGoto"));

        horizontalLayout_3->addWidget(btGoto);


        verticalLayout_3->addWidget(Advancetwidget);

        label_6 = new QLabel(widget_2);
        label_6->setObjectName(QString::fromUtf8("label_6"));
        label_6->setFont(font);

        verticalLayout_3->addWidget(label_6);

        Variabelwidget = new QWidget(widget_2);
        Variabelwidget->setObjectName(QString::fromUtf8("Variabelwidget"));
        gridLayout_3 = new QGridLayout(Variabelwidget);
        gridLayout_3->setObjectName(QString::fromUtf8("gridLayout_3"));
        gridLayout_3->setContentsMargins(0, 0, 0, 0);
        label_10 = new QLabel(Variabelwidget);
        label_10->setObjectName(QString::fromUtf8("label_10"));

        gridLayout_3->addWidget(label_10, 1, 2, 1, 1);

        label_9 = new QLabel(Variabelwidget);
        label_9->setObjectName(QString::fromUtf8("label_9"));

        gridLayout_3->addWidget(label_9, 1, 0, 1, 1);

        label_7 = new QLabel(Variabelwidget);
        label_7->setObjectName(QString::fromUtf8("label_7"));

        gridLayout_3->addWidget(label_7, 0, 0, 1, 1);

        lcdSpeed = new QLCDNumber(Variabelwidget);
        lcdSpeed->setObjectName(QString::fromUtf8("lcdSpeed"));

        gridLayout_3->addWidget(lcdSpeed, 1, 1, 1, 1);

        lcdWidth = new QLCDNumber(Variabelwidget);
        lcdWidth->setObjectName(QString::fromUtf8("lcdWidth"));
        lcdWidth->setSmallDecimalPoint(false);

        gridLayout_3->addWidget(lcdWidth, 0, 1, 1, 1);

        label_8 = new QLabel(Variabelwidget);
        label_8->setObjectName(QString::fromUtf8("label_8"));

        gridLayout_3->addWidget(label_8, 0, 2, 1, 1);


        verticalLayout_3->addWidget(Variabelwidget);

        verticalSpacer = new QSpacerItem(20, 40, QSizePolicy::Minimum, QSizePolicy::Expanding);

        verticalLayout_3->addItem(verticalSpacer);


        horizontalLayout_5->addWidget(widget_2);


        horizontalLayout_4->addWidget(widget);

        MainWindow->setCentralWidget(centralwidget);
        statusbar = new QStatusBar(MainWindow);
        statusbar->setObjectName(QString::fromUtf8("statusbar"));
        MainWindow->setStatusBar(statusbar);
        menubar = new QMenuBar(MainWindow);
        menubar->setObjectName(QString::fromUtf8("menubar"));
        menubar->setGeometry(QRect(0, 0, 800, 21));
        menubar->setDefaultUp(false);
        MainWindow->setMenuBar(menubar);

        retranslateUi(MainWindow);

        QMetaObject::connectSlotsByName(MainWindow);
    } // setupUi

    void retranslateUi(QMainWindow *MainWindow)
    {
        MainWindow->setWindowTitle(QCoreApplication::translate("MainWindow", "MainWindow", nullptr));
        SpeedLabel->setText(QCoreApplication::translate("MainWindow", "Set speed:", nullptr));
        btSetSpeed->setText(QCoreApplication::translate("MainWindow", "Set", nullptr));
        label->setText(QCoreApplication::translate("MainWindow", "Direct control of gripper:", nullptr));
        btOpen->setText(QCoreApplication::translate("MainWindow", "Open", nullptr));
        btConnect->setText(QCoreApplication::translate("MainWindow", "Stop", nullptr));
        btClose->setText(QCoreApplication::translate("MainWindow", "Close", nullptr));
        label_2->setText(QCoreApplication::translate("MainWindow", "Advanced control of gripper:", nullptr));
        label_3->setText(QCoreApplication::translate("MainWindow", "Set gripper width between fingers", nullptr));
        label_5->setText(QCoreApplication::translate("MainWindow", "mm", nullptr));
        btGoto->setText(QCoreApplication::translate("MainWindow", "Goto width", nullptr));
        label_6->setText(QCoreApplication::translate("MainWindow", "Watch variables:", nullptr));
        label_10->setText(QCoreApplication::translate("MainWindow", "%", nullptr));
        label_9->setText(QCoreApplication::translate("MainWindow", "Current gripper speed:", nullptr));
        label_7->setText(QCoreApplication::translate("MainWindow", "Current gripper width:", nullptr));
        label_8->setText(QCoreApplication::translate("MainWindow", "x", nullptr));
    } // retranslateUi

};

namespace Ui {
    class MainWindow: public Ui_MainWindow {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_MAINWINDOW_H
