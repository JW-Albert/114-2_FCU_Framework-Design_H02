package fcu.remote;

import java.util.Objects;

public class RemoteController {
    private ControllableDevice device;

    public RemoteController(ControllableDevice device) {
        this.device = Objects.requireNonNull(device, "device");
    }

    public void setDevice(ControllableDevice device) {
        this.device = Objects.requireNonNull(device, "device");
    }

    public ControllableDevice getDevice() {
        return device;
    }

    public void pressOn() {
        device.on();
    }

    public void pressOff() {
        device.off();
    }

    public void pressUp() {
        device.up();
    }

    public void pressDown() {
        device.down();
    }

    public String getStatusText() {
        return device.getStatusText();
    }
}
