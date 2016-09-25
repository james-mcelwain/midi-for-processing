package MidiForProcessing;

import javax.sound.midi.ShortMessage;

public interface Listener {
    // Criteria
    int getChannel();
    int getCC();
    MIDI_STATUS getStatus();
    void send (ShortMessage msg);
}
