package MidiForProcessing;

import javax.sound.midi.ShortMessage;

interface Listener {
    boolean eval(ShortMessage msg);
    void send(ShortMessage msg);
}
