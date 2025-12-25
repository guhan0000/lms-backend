package com.lms.demo.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;

public class TestData {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println(LocalDate.now());
        System.out.println(LocalTime.now());
        System.out.println(LocalDateTime.now());
        LocalTime localTime = LocalTime.of(5, 10, 30);
        System.out.println(localTime);
        System.out.println("enter id");
        Long id=scanner.nextLong();
        System.out.println(id);
    }


}
