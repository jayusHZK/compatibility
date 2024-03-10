package com.jayus.designPattern.command.command;

public class UpdateCommand extends Command{

    @Override
    public void exec() {
        super.requirementGroup.update();
        super.pageGroup.update();
        super.codeGroup.update();
    }
}
