package com.example.call_center_driver_app.my_interfaces;

import com.example.call_center_driver_app.constants.GlobalCommandID;

public interface ChainHandler {
    void setNext(ChainHandler nextHandler);
    void handle(GlobalCommandID commandId);
    void setCommand(Command command);

}
