package MidiForSketches;

import java.util.HashMap;

/**
 *  Midi plugin for Processing, to enable the use of
 *  variables whose values are controlled by external midi
 *  input.
        */
public class MidiForSketches {
   private HashMap<String, MidiDeviceContainer> midiDevices = new HashMap<>();

   public MidiForSketches(String... devices) {
      for (String device : devices) {
         midiDevices.put(device, new MidiDeviceContainer(device));
      }
   }
}
