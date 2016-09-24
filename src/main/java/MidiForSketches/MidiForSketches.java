package MidiForSketches;

import java.util.HashMap;

/**
 *  Midi plugin for Processing, to enable the use of
 *  variables whose values are controlled by external midi
 *  input.
 */
public class MidiForSketches {
   private String[] deviceNames;
   private HashMap<String, MidiDeviceContainer> midiDevices = new HashMap<>();

   public MidiForSketches(String... devices) {
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
