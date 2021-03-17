#include "MyTcpServer.h"
#include <iostream>

MyTcpServer::MyTcpServer(QObject *parent) :
    QObject(parent)
{
    server = new QTcpServer(this);

    // whenever a user connects, it will emit signal
    connect(server, SIGNAL(newConnection()),
            this, SLOT(newConnection()));

    if(!server->listen(QHostAddress::Any, 9999))
    {
        qDebug() << "Server could not start";
    }
    else
    {
        qDebug() << "Server started!";
    }
}

void MyTcpServer::newConnection(){
    char buffer[256]{};

    // need to grab the socket
    QTcpSocket *socket = server->nextPendingConnection();

    socket->write("ER I SNART FAERDIGE!?\r\n");
    socket->flush();
    socket->waitForBytesWritten(3000);
    socket->waitForReadyRead(3000);

    socket->read(buffer, 255);

    for (size_t i = 0; i < 256 ; i++) {
        if (buffer[i] != NULL){
            std::cout << buffer[i];
        }
    }

    if (buffer[0] != NULL){
        std::cout << std::endl;
    }

    socket->close();
}
