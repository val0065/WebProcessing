package cz.val.webprocessing.implementation;

import cz.val.webprocessing.process.OldProcess;
import cz.val.webprocessing.process.Process;
import java.io.IOException;
import org.geotools.process.factory.DescribeParameter;
import org.geotools.process.factory.DescribeProcess;
import org.geotools.process.factory.DescribeResult;
import org.geoserver.wps.gs.GeoServerProcess;

@DescribeProcess(title="overlayWPS", description="Creates buffer around point and overlays it with polygon layer. Returns areas of overlay.")
public class OverlayPolygons implements GeoServerProcess {
    
    @DescribeResult(name="result", description="output result")
    public String execute() throws IOException{
        OldProcess p = new OldProcess();
        return p.overlayPolygons();
    }    
}
