package fcu.remote;

public class Tv implements ControllableDevice {
    private static final int MIN_CHANNEL = 1;
    private static final int MAX_CHANNEL = 15;

    private boolean on;
    private int channel = 7;

    @Override
    public void on() {
        on = true;
    }

    @Override
    public void off() {
        on = false;
    }

    @Override
    public void up() {
        if (!on) return;
        if (channel < MAX_CHANNEL) channel++;
    }

    @Override
    public void down() {
        if (!on) return;
        if (channel > MIN_CHANNEL) channel--;
    }

    @Override
    public boolean isOn() {
        return on;
    }

    @Override
    public String getName() {
        return "TV";
    }

    public int getChannel() {
        return channel;
    }

    @Override
    public String getStatusText() {
        return String.format("%s: %s, CH=%d", getName(), on ? "ON" : "OFF", channel);
    }
}
