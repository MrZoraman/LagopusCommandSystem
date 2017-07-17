package com.lagopusempire.lagopuscommandsystem.basic;

import com.lagopusempire.lagopuscommandsystem.CommandResult;
import com.lagopusempire.lagopuscommandsystem.CommandSystem;

/**
 * Basic Command System. This class will probably satisfy 95% of the
 * use cases for this library.
 */
public class BasicCommandSystem extends CommandSystem<IBasicCommand> {
    
    /**
     * Runs a command given the input string.
     * @param input The input string to parse and match to a command,
     * if a command is found. If no command is found and no unknown
     * command is set, then nothing happens.
     */
    public void runCommand(String input) {
        CommandResult<IBasicCommand> cmdResult = getCommand(input);
        cmdResult.command.ifPresent(cmd -> {
            cmd.execute(cmdResult.preArgs, cmdResult.args);
        });
    }
}
