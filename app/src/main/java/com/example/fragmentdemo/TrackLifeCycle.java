package com.example.fragmentdemo;

public class TrackLifeCycle {
    private static String track = "";

    public static void Commit(String t) {
        track = track + t + "\n";

    }

    public static String GetCommit() {
        return track;
    }

}
