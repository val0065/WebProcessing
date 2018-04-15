package cz.val.webprocessing;

import cz.val.webprocessing.process.Process;


public class WebProcessing {

    public static void main(String[] args) throws Exception {
        
        Process proc = new Process();

        //System.out.println(proc.lengthOfLine());
        //System.out.println(proc.lengthOfLineWithFilter());
        System.out.println(proc.overlayPolygons());
        //System.out.println(proc.overlayPolygonsWithFilter());

    }

}
