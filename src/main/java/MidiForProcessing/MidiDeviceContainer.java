package MidiForProcessing;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.ShortMessage;

import java.util.Arrays;
import java.util.Optional;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * Deals with initialization logic related to obtaining a listener for a
 * particular named midi device.
 */
class MidiDeviceContainer {
    private String name;
    private Optional<MidiDevice> midiDevice;
    private ArrayList<Listener> listeners = new ArrayList<>();

    MidiDeviceContainer(String name) {
        this.name = name;
        midiDevice = locateDevice(name);
    }

    private static MidiDevice unsafeGetMidiDevice(MidiDevice.Info info) {
        try {
            return MidiSystem.getMidiDevice(info);
        } catch (MidiUnavailableException muex) {
            System.out.println("MUEX: " + muex);
            return null;
        }
    }

    private Optional<MidiDevice> locateDevice(String name) {
        /**
         * Locates a Midi device with supplied name.
         * NB: We break the loop as duplicate devices sometimes appear in error
         * on OSX, particularly IAC. There is probably a cause for this but this
         * works.
         */

        Optional<MidiDevice> midiDevice = Stream.of(MidiSystem.getMidiDeviceInfo())
                .filter(x -> x.getName().contains(name))
                .map(MidiDeviceContainer::unsafeGetMidiDevice)
                .findFirst();

        if (midiDevice.isPresent() && !midiDevice.get().isOpen()) {
            try {
                midiDevice.get().open();
            } catch (MidiUnavailableException muex) {
                System.out.println("Unable to open device " + name);
            }
        }

        if (midiDevice.isPresent()) {
            try {
                midiDevice.get().getTransmitter().setReceiver(new MidiReceiver(this, true));
            } catch (MidiUnavailableException muex) {
                System.out.println("Midi device unavailable " + name);
            }
        }

        return midiDevice;
    }

    public void connect() {
        this.midiDevice = locateDevice(this.name);
    }

    public boolean hasDevice() {
        return midiDevice.isPresent();
    }

    public Optional<MidiDevice> getMidiDevice() {
        return this.midiDevice;
    }

    public void registerListener(Listener listener) {
        listeners.add(listener);
    }

    public void receiveMessage(ShortMessage msg) {
        for (Listener listener : listeners) {
            if (listener.getStatus() != null &&
                    listener.getStatus() != MIDI_STATUS.status(msg)) {
                break;
            }

            if (listener.getStatus() == MIDI_STATUS.ControlChange &&
                    listener.getCC() != msg.getData1()) {
                break;
            }

            // We are using 0 as a default channel
            if (listener.getChannel() != 0 && listener.getChannel() != msg.getChannel()) {
                break;
            }

            listener.send(msg);
        }

    }
}