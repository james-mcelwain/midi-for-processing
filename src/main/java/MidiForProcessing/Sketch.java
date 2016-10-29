package MidiForProcessing;


import processing.core.PApplet;

public class Sketch extends PApplet {
    private int[] colors = new int[3];

    public Sketch() {
        colors[0] = 100;
        colors[1] = 100;
        colors[2] = 100;

        MidiDeviceContainer midi = new MidiDeviceContainer("mio");
        if (midi.hasDevice()) {
            System.out.println("Connected!");
        }

        midi.registerHandler(x -> {
            System.out.println(MIDI_STATUS.status(x).toString());
        });
    }

    public void settings() {
        size(1920, 1080);
    }

    public void draw() {
        background(colors[0]);
    }

    public static void main(String... args) {
        PApplet.main("MidiForProcessing.Sketch");
    }

}
