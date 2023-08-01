package com.example.call_center_driver_app.my_interfaces;

public interface ChainHandler {
    void setNext(ChainHandler nextHandler);
    void handle(int commandId);
    void setCommand(Command command);

}
