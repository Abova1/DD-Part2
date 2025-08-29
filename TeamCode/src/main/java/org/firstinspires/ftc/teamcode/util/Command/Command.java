package org.firstinspires.ftc.teamcode.util.Command;

/*
I didn't want to use FTC libs Command class
 */

public interface Command {
    void init();
    void execute();
    boolean isFinished();
    void end();

    //I plan on doing something with this to make sure nothing goes wrong if 2 commands collide
    default void cancel(){
        end();
    }

}
