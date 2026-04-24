package fcu.remote;

public class AirConditioner implements ControllableDevice {
    private static final int MIN_TEMP = 20;
    private static final int MAX_TEMP = 30;

    private boolean on;
    private int temperature = 25;

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
        if (temperature < MAX_TEMP) temperature++;
    }

    @Override
    public void down() {
        if (!on) return;
        if (temperature > MIN_TEMP) temperature--;
    }

    @Override
    public boolean isOn() {
        return on;
    }

    @Override
    public String getName() {
        return "AirConditioner";
    }

    public int getTemperature() {
        return temperature;
    }

    @Override
    public String getStatusText() {
        return String.format("%s: %s, TEMP=%d°C", getName(), on ? "ON" : "OFF", temperature);
    }
}
