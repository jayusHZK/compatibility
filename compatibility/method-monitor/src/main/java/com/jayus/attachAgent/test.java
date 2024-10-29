package com.jayus.attachAgent;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

import java.util.List;

/**
 * @ClassName test
 * @Description:
 * @date: 2024/10/29 23:00
 */
public class test {

    public static void main(String[] args) throws Exception {
        // 将当前项目打成jar
        List<VirtualMachineDescriptor> virtualMachineDescriptors = VirtualMachine.list();
        // 这里要获取对应的 虚拟机id 才能正常 调用探针
        String id=virtualMachineDescriptors.get(0).id();
        //String id=virtualMachineDescriptors.stream().filter(item -> item.displayName().startsWith("com.jayus.attachAgent")).findFirst().get().id();
        //连接虚拟机
        VirtualMachine vm=VirtualMachine.attach(id);
        //提供代理jar包路径 传参就是后面跟 = 号
        vm.loadAgent("D:\\idea\\ccc\\compatibility\\compatibility\\method-monitor\\target\\method-monitor-jar-with-dependencies.jar=123");
        vm.detach();
        System.out.println("ends-------------------");
    }

}
