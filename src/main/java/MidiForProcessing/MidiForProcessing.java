package MidiForProcessing;

import java.util.HashMap;

/**
 *  Midi plugin for Processing, to enable the use of
 *  variables whose values are controlled by external midi
 *  input.
 */
public class MidiForProcessing {
    private String[] deviceNames;
    private HashMap<String, MidiDeviceContainer> midiDevices = new HashMap<>();

    public static void main(String... args) {
        MidiForProcessing me = new MidiForProcessing("bus 1");
        System.out.println(me.getDevice("bus 1").hasDevice());
        while (true) {
        }

    }

    public MidiForProcessing(String... devices) {
        for (String device : devices) {
            this.deviceNames = devices;
            midiDevices.put(device, new MidiDeviceContainer(device));
        }
    }

    public void refreshDevice(String name) {
        this.midiDevices.get(name).connect();
    }

    public void refreshAll() {
        for (String device : deviceNames) {
            refreshDevice(device);
        }
    }

    public void setDevice(String name) {
        this.midiDevices.put(name, new MidiDeviceContainer(name));
    }

    public MidiDeviceContainer getDevice(String name) {
        return this.midiDevices.get(name);
    }
}
