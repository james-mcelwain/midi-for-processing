package MidiForProcessing;

import processing.core.PApplet;

import java.util.HashMap;
import java.util.function.Consumer;

public class Sketch extends PApplet {

    /**
     * Go!
     * @param args
     */
    public static void main(String... args) {
        PApplet.main(Sketch.class);
    }


    /**
     * PRELUDE --
     * Midi setup, etc.
     */
    private int w = 1000;
    private int h = 500;


    public Sketch() {

        MidiDeviceContainer midi = new MidiDeviceContainer("mio");
        if (midi.hasDevice()) {
            System.out.println("Connected!");
        }

        /**
         * We can register as many handlers as we want to manipulate state internal to sketch.
         */
        midi.registerHandler(x -> {
            if (MIDI_STATUS.status(x.getStatus()).equals(MIDI_STATUS.NoteOn)) { System.out.println(x.getChannel()); }
        });

        registerSketch("main", sketch -> {
            sketch.background(random(0, 255));
        });
    }

    public void settings() {
        size(w, h);
    }

    public void draw() {
        frameRate(1);
        run("main");
    }

    public void run(String sketch) {
        sketches.get(sketch).accept(this);
    }

    private HashMap<String, Consumer<PApplet>>  sketches = new HashMap<>();
    public void registerSketch(String name, Consumer<PApplet> fn) {
        sketches.put(name, fn);
    }
}
