package ProcessingMidiPlugin;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiUnavailableException;

import java.util.Optional;

/**
 * Deals with initialization logic related to obtaining a listener for a
 * particular named midi device.
 */
public class MidiDeviceContainer {
    private Optional<MidiDevice> midiDevice;

    public MidiDeviceContainer(String name) {
        /**
         * Locates a Midi device with supplied name.
         * NB: We break the loop as duplicate devices sometimes appear in error
         * on OSX, particularly IAC. There is probably a cause for this but this
         * works.
         */

        for (MidiDevice.Info info : MidiSystem.getMidiDeviceInfo()) {
            String infoName = info.getName().toLowerCase();
            if (infoName.equals((name.toLowerCase()))) {
                try {
                    midiDevice = Optional.of(MidiSystem.getMidiDevice(info));
                    System.out.println("Connected to " + info.getName());
                } catch (MidiUnavailableException muex) {
                    midiDevice = Optional.empty();
                }

                break;
            }
        }
    }

    public Optional<MidiDevice> getMidiDevice() {
        return this.midiDevice;
    }
}