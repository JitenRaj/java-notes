package DesignPatterns.Structural.Adapter;

// Adaptee Interface: A separate, incompatible interface

public interface AdvancedMediaPlayer {
    void playVlc(String filename);
    void playMp4(String filename);
}
