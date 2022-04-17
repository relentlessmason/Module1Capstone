//package com.techelevator.view;
//
//import java.io.*;
//
//public class Log {
//
//    public static void log(String message)throws FileNotFoundException{
//
//        String logPath = "capstone/log.txt";
//        File logFile = new File(logPath);
//
//        try (PrintWriter writer = new PrintWriter(new FileWriter(logFile, true))){
//            writer.println(message + "\n");
//        } catch (IOException e){
//            System.err.println(e.getMessage());
//        }
//    }
//}
