package com.example.call_center_driver_app.other_components;

import com.example.call_center_driver_app.activities.login.Login;
import com.example.call_center_driver_app.constants.GlobalCommandID;
import com.example.call_center_driver_app.my_interfaces.ChainHandler;
import com.example.call_center_driver_app.my_interfaces.Command;

public class CommandHandler implements ChainHandler {
    private ChainHandler next;
    private Command command;

    private GlobalCommandID commandID;

    public CommandHandler(GlobalCommandID regCommandID)
    {
        next = null;
        command = null;
        commandID = regCommandID;
    }


    @Override
    public void handle(GlobalCommandID commandIDToExecute) {
        if(commandID == commandIDToExecute && command != null)
        {
            command.execute();
        }
        else if(next != null)
        {
            next.handle(commandIDToExecute);
        }
    }

    @Override
    public void setCommand(Command command) {
        this.command = command;
    }

    @Override
    public void setNext(ChainHandler nextHandler)
    {
        this.next = nextHandler;
    }
}
