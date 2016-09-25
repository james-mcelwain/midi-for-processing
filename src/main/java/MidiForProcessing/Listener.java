package MidiForProcessing;

import javax.sound.midi.ShortMessage;

public interface Listener {
    // Criteria
    void setChannel(int channel);
    int getChannel();
    void setCC(int cc);
    int getCC();
    void setStatus(MIDI_STATUS status);
    MIDI_STATUS getStatus();

    void send (ShortMessage msg);
}
