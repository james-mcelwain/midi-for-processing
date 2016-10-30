package MidiForProcessing;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

/**
 * Primary class that exists as the boundary between our listener and the actual
 * midi device. When we receive a message on the midi bus, we "send" a midi short
 * message, the values of which we store in a fixed length array representing the
 * 128 possible midi cc channels.
 */
class MidiReceiver implements Receiver {
    private MidiDeviceContainer container;
    private boolean debug = false;
    private int count = 0;

    MidiReceiver(MidiDeviceContainer container, boolean debug) {
        this.container = container;
        this.debug = debug;
    }

    public void close() {
        System.out.println("MidiReceiver::close called");
    }

    public void send(MidiMessage midiMessage, long timeStamp) {
        ShortMessage msg = (ShortMessage) midiMessage;
        debug(msg, timeStamp);
        container.receiveMessage(msg);
    }

    private void debug(ShortMessage msg, long time) {
        if (MIDI_STATUS.status(msg) != MIDI_STATUS.Clock) {
            this.count ++;
            System.out.println("#" + this.count + " [" + time + "]: channel=" + msg.getChannel() + " command=" + msg.getCommand() +
                    " data1=" + msg.getData1() + " data2=" + msg.getData2() + " status=" + MIDI_STATUS.status(msg).name());
        }

    }
}
