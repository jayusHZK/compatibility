package com.jayus.designPattern.command.command;

public class DelCommand extends Command{

    @Override
    public void exec() {
        super.requirementGroup.del();
        super.pageGroup.del();
        super.codeGroup.del();
    }
}
