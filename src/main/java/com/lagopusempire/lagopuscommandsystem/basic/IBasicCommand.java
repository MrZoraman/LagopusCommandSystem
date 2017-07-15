package com.lagopusempire.lagopuscommandsystem.basic;

/**
 *
 * @author Foomf
 */
@FunctionalInterface
public interface IBasicCommand {
    void execute(String[] preArgs, String[] args);
}
