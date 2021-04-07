#include <iostream>
#include <string>

using namespace std;

int main()
{
    string cmd;
    cout << "Write command" << endl;
    cin >> cmd;

    //______________________________
    if (cmd == "test") {
        cout << "Hello" << endl;
    }
    //______________________________
    else if (cmd == "test2") {
        cout << "World" << endl;
    }
    //______________________________
    else {
        cout << "Invalid command";
    }
    //______________________________
    return 0;
}
