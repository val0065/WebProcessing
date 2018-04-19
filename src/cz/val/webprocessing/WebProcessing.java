package cz.val.webprocessing;

import cz.val.webprocessing.process.OldProcess;
import cz.val.webprocessing.process.Process;


public class WebProcessing {

    public static void main(String[] args) throws Exception {
        
        Process proc = new Process();
        OldProcess oldProc = new OldProcess();

//        System.out.println(oldProc.lengthOfLine());
//        System.out.println(proc.lengthOfLineWithFilter());
//        System.out.println(proc.overlayPolygons());
//        System.out.println(proc.overlayPolygonsWithFilter());
//        System.out.println(proc.lengthOfLineWithFilterMSK());
//        System.out.println(proc.overlayPolygonsWithFilterMSK());
//        System.out.println(proc.lengthOfLineWithFilter2());
        System.out.println(proc.pointsCount());

    }

}
