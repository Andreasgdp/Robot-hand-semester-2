#define WIN32_LEAN_AND_MEAN

#include <iostream>
#include <stdexcept>
#include <cassert>
#ifdef _WIN32
#include <windows.h>
#else
#include <unistd.h>
#endif

#include <xmlrpc-c/base.hpp>
#include <xmlrpc-c/registry.hpp>
#include <xmlrpc-c/server_abyss.hpp>

class ConnCheck : public xmlrpc_c::method {
public:
    ConnCheck() {
        this->_signature = "i:";
        this->_help = "Connection check function";
    }
    void execute(xmlrpc_c::paramList const& paramList, xmlrpc_c::value* const retvalP) {
        std::cout << "Hello there!" << std::endl;

        *retvalP = xmlrpc_c::value_int(42);
    }
};

int main()
{
    std::cout << "Starting XML_RPC Server" << std::endl;
    try {
        xmlrpc_c::registry myRegistry;

        xmlrpc_c::methodPtr const connCheck(new ConnCheck);

        myRegistry.addMethod("connCheck", connCheck);
        xmlrpc_c::serverAbyss myAbyssServer(xmlrpc_c::serverAbyss::constrOpt().registryP(&myRegistry).portNumber(5000));

        myAbyssServer.run();

        assert(false);

    }  catch (std::exception const& e) {
        std::cerr << "Something failed. " << e.what() << std::endl;
    }

    return 0;
}
