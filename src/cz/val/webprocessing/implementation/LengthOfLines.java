package cz.val.webprocessing.implementation;

import cz.val.webprocessing.process.OldProcess;
import java.io.IOException;
import org.geoserver.wps.gs.GeoServerProcess;
import org.geotools.process.factory.DescribeProcess;
import org.geotools.process.factory.DescribeResult;

@DescribeProcess(title = "LengthOfLine", description = "Overlay two layers and measure area")
public class LengthOfLines implements GeoServerProcess {

    @DescribeResult(name = "result", description = "output result")
    public String execute() throws IOException, Exception {
        OldProcess p = new OldProcess();
        return p.lengthOfLine();
    }

}
