package cz.val.webprocessing.implementation;

import java.io.IOException;
import org.geotools.process.factory.DescribeProcess;
import org.geotools.process.factory.DescribeResult;
import org.geoserver.wps.gs.GeoServerProcess;
import cz.val.webprocessing.process.Process;

@DescribeProcess(title = "LengthMSK", description = "Overlay two polygons and measure area")
public class LengthMsk implements GeoServerProcess {

    @DescribeResult(name = "result", description = "output result")
    public String execute() throws IOException, Exception {
        Process p = new Process();
        return p.lengthOfLineWithFilterMSK();
    }

}
