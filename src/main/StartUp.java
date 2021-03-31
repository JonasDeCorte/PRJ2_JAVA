package main;

import domein.DomeinController;

public class StartUp {
    public static void main(String [] arg) {
        new StartUp().run();
    }

    private void run() {
    	DomeinController dc = new DomeinController();
    	dc.test();
    }
    
}
