/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.val.webprocessing.implementation;

import java.io.IOException;
import org.geotools.process.factory.DescribeProcess;
import org.geotools.process.factory.DescribeResult;
import org.geoserver.wps.gs.GeoServerProcess;

@DescribeProcess(title = "AreaCechy", description = "Overlay two polygons and measure area")
public class AreaCechy implements GeoServerProcess {

    @DescribeResult(name = "result", description = "output result")
    public String execute() throws IOException {
        cz.val.webprocessing.process.Process p = new cz.val.webprocessing.process.Process();
        return p.overlayPolygonsWithFilterCechy();
    }

}
