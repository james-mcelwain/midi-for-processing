package MidiForProcessing;


import processing.core.PApplet;

import javax.sound.midi.ShortMessage;

public class Sketch extends PApplet {

    private int[] colors = new int[3];

    public Sketch() {
        colors[0] = 100;
        colors[1] = 100;
        colors[2] = 100;

        MidiForProcessing midi = new MidiForProcessing("mio");
        if (midi.getDevice("mio").hasDevice()) {
            System.out.println("Connected");
        }

        midi.registerGlobalListener(new Listener() {
            @Override
            public int getChannel() {
                return 0;
            }

            @Override
            public int getCC() {
                return 0;
            }

            @Override
            public MIDI_STATUS getStatus() {
                return MIDI_STATUS.NoteOn;
            }

            @Override
            public void send(ShortMessage msg) {
                colors[0] = floor(random(0, 255));
            }
        });
    }

    public void settings() {
        size(1920,1080);
    }

    public void draw() {
        background(colors[0]);
    }

    public static void main(String... args) {
        PApplet.main("MidiForProcessing.Sketch");
    }
}
