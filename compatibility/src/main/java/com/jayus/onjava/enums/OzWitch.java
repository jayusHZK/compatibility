package com.jayus.onjava.enums;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author : h zk
 * @date : 2022/8/11 19:24
 * @description :
 **/
public enum OzWitch {
    WEST("Miss Gulch, aka the Wicked Witch of the West"),
    NORTH("Glinda, the Good Witch of the North"),
    EAST("Wicked Witch of the East, wearer of the Ruby " +
                 "Slippers, crushed by Dorothy's house"),
    SOUTH("Good by inference, but missing");

    private String description;

    private OzWitch(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
       String id = name();
       String lower = id.substring(1).toLowerCase();
       return id.charAt(0)+lower;
    }

    public static void main(String[] args) {
        for (OzWitch witch : OzWitch.values()) {
            System.out.println(witch + ": " +witch.getDescription());
        }
        System.out.println("========");
        Stream.of(values()).forEach(System.out::println);
        Stream.of(OzWitch.class.getMethods()).forEach(System.out::println);
    }
}
