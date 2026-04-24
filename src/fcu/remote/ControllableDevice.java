package fcu.remote;

public interface ControllableDevice {
    void on();

    void off();

    void up();

    void down();

    boolean isOn();

    String getName();

    String getStatusText();
}
