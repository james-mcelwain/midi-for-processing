package MidiForProcessing;

import javax.sound.midi.ShortMessage;

/**
 * Each midi message carries a byte representing its status. This enum allows
 * us to represent that status.
 */
enum MIDI_STATUS {
    None(0),
    NoteOff(128),
    NoteOn(144),
    AfterTouch(160),
    ControlChange(176),
    ProgramChange(192),
    ChannelPressure(208),
    PitchBend(224),
    SystemMsgs(240),
    MidiTimeCode(241),
    SongPosition(242),
    SongSelect(243),
    TuneRequest(246),
    EOX(247),
    Clock(248),
    Start(250),
    Continue(251),
    Stop(252),
    ActiveSense(254),
    Reset(255);

    private int value;

    MIDI_STATUS(int value) {
        this.value = value;
    }

    public static MIDI_STATUS status(int statusCode) {
        for(MIDI_STATUS value : MIDI_STATUS.values()) {
            if (value.getValue() == statusCode) {
                return value;
            }
        }

        return MIDI_STATUS.None;
    }

    public int getValue() {
        return value;
    }

    public static MIDI_STATUS status(ShortMessage msg) {
       return MIDI_STATUS.status(msg.getStatus());
    }
}
