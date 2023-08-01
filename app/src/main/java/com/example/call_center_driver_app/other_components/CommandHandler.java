package com.example.call_center_driver_app.other_components;

import com.example.call_center_driver_app.my_interfaces.ChainHandler;
import com.example.call_center_driver_app.my_interfaces.Command;

public class CommandHandler implements ChainHandler {
    private ChainHandler next;
    private Command command;

    private int commandID;

    CommandHandler(int recogCommandID)
    {
        next = null;
        command = null;
        commandID = recogCommandID;
    }

    @Override
    public void handle(int commandIDToExecute) {
        if(commandID == commandIDToExecute)
        {
            command.execute();
        }
        if(next != null)
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
