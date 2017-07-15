package com.lagopusempire.lagopuscommandsystem.basic;

import com.lagopusempire.lagopuscommandsystem.CommandResult;
import com.lagopusempire.lagopuscommandsystem.CommandSystem;

/**
 *
 * @author Foomf
 */
public class BasicCommandSystem extends CommandSystem<IBasicCommand> {
    public void runCommand(String input) {
        CommandResult<IBasicCommand> cmdResult = getCommand(input);
        cmdResult.command.ifPresent(cmd -> {
            cmd.execute(cmdResult.preArgs, cmdResult.args);
        });
    }
}
