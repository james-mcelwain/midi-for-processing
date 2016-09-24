package MidiExtension;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

import java.util.Optional;

public class MidiReceiver implements Receiver {
    private boolean debug = false;
    private ShortMessage[] channels = new ShortMessage[128];

    public MidiReceiver(boolean debug) {
        this.debug = debug;
    }

    public void close() {
        System.out.println("MidiReceiver#close called");
    }

    public void send(MidiMessage midiMessage, long timeStamp) {
        ShortMessage msg = (ShortMessage) midiMessage;
        channels[msg.getData1()] = msg;
    }

    public Optional<ShortMessage> get(int channel) {
        Optional<ShortMessage> msg = Optional.of(channels[channel]);
        return msg;
    }

    private static void debug(ShortMessage msg, long time) {
        System.out.println("[" + time + "]: " + msg.toString());
    }
}
