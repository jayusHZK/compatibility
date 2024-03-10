package com.jayus.designPattern.command.command;

import com.jayus.designPattern.command.group.CodeGroup;
import com.jayus.designPattern.command.group.PageGroup;
import com.jayus.designPattern.command.group.RequirementGroup;

/**
 * 命令抽象类
 */
public abstract class Command {

    CodeGroup codeGroup = new CodeGroup();

    PageGroup pageGroup = new PageGroup();

    RequirementGroup requirementGroup = new RequirementGroup();

    public abstract void exec();

}
