package MidiForProcessing;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.ShortMessage;

interface Listener {
    public boolean eval(ShortMessage msg);
    public void send (ShortMessage msg);
}
