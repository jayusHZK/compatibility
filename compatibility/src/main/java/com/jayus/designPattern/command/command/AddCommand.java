package com.jayus.designPattern.command.command;

public class AddCommand extends Command{

    @Override
    public void exec() {
        super.requirementGroup.add();
        super.pageGroup.add();
        super.codeGroup.add();
    }
}
