package org.firstinspires.ftc.teamcode.util.Command;

import java.util.*;

public class CommandScheduler {
    private final List<Command> activeCommands = new ArrayList<>();

    public void schedule(Command command){

        command.init();
        activeCommands.add(command);

    }

    public void run(){

        Iterator<Command> iterator = activeCommands.iterator();

        while (iterator.hasNext()){

            Command command = iterator.next();
            command.execute();

            if(command.isFinished()){

                command.end();

                iterator.remove();
            }

        }

    }

    public void cancel(Command command) {
        if (activeCommands.remove(command)) {
            command.cancel();
        }
    }

    public void cancelAll() {
        for (Command cmd : new ArrayList<>(activeCommands)) {
            cancel(cmd);
        }
    }





}