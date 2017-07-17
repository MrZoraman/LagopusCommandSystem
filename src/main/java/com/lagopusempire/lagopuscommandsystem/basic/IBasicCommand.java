package com.lagopusempire.lagopuscommandsystem.basic;

/**
 * Basic command. To be used with the BasicCommandSystem.
 */
@FunctionalInterface
public interface IBasicCommand {
    
    /**
     * Executes the command-specific code.
     * @param preArgs The arguments that were filled with wildcards.
     * @param args The arguments occurring after the matched command.
     */
    void execute(String[] preArgs, String[] args);
}
