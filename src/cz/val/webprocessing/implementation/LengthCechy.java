/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.val.webprocessing.implementation;

import java.io.IOException;
import org.geoserver.wps.gs.GeoServerProcess;
import org.geotools.process.factory.DescribeProcess;
import org.geotools.process.factory.DescribeResult;
import cz.val.webprocessing.process.Process;

@DescribeProcess(title = "LengthCechy", description = "Overlay two polygons and measure area")
public class LengthCechy implements GeoServerProcess {

    @DescribeResult(name = "result", description = "output result")
    public String execute() throws IOException, Exception {
        Process p = new Process();
        return p.lengthOfLineWithFilterCechy();

    }
}
