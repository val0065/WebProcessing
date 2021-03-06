package cz.val.webprocessing.process;

import com.vividsolutions.jts.geom.*;
import java.io.IOException;
import java.net.URL;
import org.geotools.data.collection.ListFeatureCollection;
import org.geotools.data.shapefile.*;
import org.opengis.feature.simple.*;
import org.geotools.data.simple.*;
import org.geotools.factory.CommonFactoryFinder;
import org.opengis.filter.FilterFactory2;
import org.opengis.filter.Filter;
import org.geotools.data.DataUtilities;
import org.opengis.filter.expression.Expression;

public class Process {

    public String pointsCount() throws Exception {

        FilterFactory2 ff = CommonFactoryFinder.getFilterFactory2();

        int count = 0;

        ShapefileDataStore sfds1 = new ShapefileDataStore(
                new URL("file:///F:\\GeoServer285\\data_dir\\data\\sf\\bugsites.shp"));
        SimpleFeatureSource pointFeatureStore = sfds1.getFeatureSource();

        ShapefileDataStore sfds2 = new ShapefileDataStore(
                new URL("file:///F:\\GeoServer285\\data_dir\\data\\sf\\restricted.shp"));
        SimpleFeatureSource polygonFeatureStore = sfds2.getFeatureSource();

        SimpleFeatureCollection pointCollection = DataUtilities.collection(pointFeatureStore.getFeatures());
        SimpleFeatureCollection polygonCollection = DataUtilities.collection(polygonFeatureStore.getFeatures());

        ListFeatureCollection pointList = new ListFeatureCollection(pointCollection);
        ListFeatureCollection polygonList = new ListFeatureCollection(polygonCollection);

        try (SimpleFeatureIterator polygonIterator = polygonList.features()) {
            while (polygonIterator.hasNext()) {

                SimpleFeature sf = polygonIterator.next();
                MultiPolygon mp = (MultiPolygon) sf.getDefaultGeometry();
                Filter filter = ff.intersects(ff.property("the_geom"), ff.literal(sf.getDefaultGeometry()));

                Polygon p = (Polygon) mp.getGeometryN(0);

                try (SimpleFeatureIterator pointIterator = pointList.subCollection(filter).features()) {
                    while (pointIterator.hasNext()) {

                        SimpleFeature sf2 = pointIterator.next();
                        Point point = (Point) sf2.getDefaultGeometry();
                        Geometry geom = p.intersection(point);

                        count = geom.getNumPoints();
                    }
                }
            }
        }

        return "Number of points: " + count;

    }

    public String lengthOfLineWithFilter() throws Exception {

        FilterFactory2 ff = CommonFactoryFinder.getFilterFactory2();

        String lengths = "Delky linii";

        ShapefileDataStore lineDataStore = new ShapefileDataStore(
                new URL("file:///F:\\GeoServer285\\data_dir\\data\\test_data\\zeleznice_cr.shp"));
        SimpleFeatureSource lineFeatureStore = lineDataStore.getFeatureSource();

        ShapefileDataStore polygonDataStore = new ShapefileDataStore(
                new URL("file:///F:\\GeoServer285\\data_dir\\data\\test_data\\chranene_uzemi_cr.shp"));
        SimpleFeatureSource polygonFeatureStore = polygonDataStore.getFeatureSource();

        SimpleFeatureCollection lineCollection = DataUtilities.collection(lineFeatureStore.getFeatures());
        SimpleFeatureCollection polygonCollection = DataUtilities.collection(polygonFeatureStore.getFeatures());

        ListFeatureCollection lineList = new ListFeatureCollection(lineCollection);
        ListFeatureCollection polygonList = new ListFeatureCollection(polygonCollection);

        double sum;
        try (SimpleFeatureIterator lineIterator = lineList.features()) {
            sum = 0;
            while (lineIterator.hasNext()) {

                SimpleFeature sf = lineIterator.next();
                MultiLineString mls = (MultiLineString) sf.getDefaultGeometry();
                LineString p1 = (LineString) mls.getGeometryN(0);

                Filter filter = ff.intersects(ff.property("the_geom"), ff.literal(sf.getDefaultGeometry()));

                try (SimpleFeatureIterator polygonIterator = polygonList.subCollection(filter).features()) {
                    while (polygonIterator.hasNext()) {

                        SimpleFeature sf2 = polygonIterator.next();
                        MultiPolygon mls2 = (MultiPolygon) sf2.getDefaultGeometry();
                        Polygon ls = (Polygon) mls2.getGeometryN(0);
                        Geometry result = p1.intersection(ls);
                        if (result.getLength() != 0) {
                            sum += result.getLength();
                            lengths = lengths + "\n" + result.getLength();
                        }
                    }
                }
            }
        }
        return "Lines found: " + lengths + "\nSuma: " + sum;
    }

    public String lengthOfLineWithFilterMSK() throws Exception {

        FilterFactory2 ff = CommonFactoryFinder.getFilterFactory2();

        String lengths = "Delky linii";

        ShapefileDataStore sfds1 = new ShapefileDataStore(
                new URL("file:///F:\\GeoServer285\\data_dir\\data\\test_data\\zeleznice_msk.shp"));
        SimpleFeatureSource lineFeatureStore = sfds1.getFeatureSource();

        ShapefileDataStore sfds2 = new ShapefileDataStore(
                new URL("file:///F:\\GeoServer285\\data_dir\\data\\test_data\\chranene_uzemi_msk.shp"));
        SimpleFeatureSource polygonFeatureStore = sfds2.getFeatureSource();

        SimpleFeatureCollection lineCollection = DataUtilities.collection(lineFeatureStore.getFeatures());
        SimpleFeatureCollection polygonCollection = DataUtilities.collection(polygonFeatureStore.getFeatures());

        ListFeatureCollection lineList = new ListFeatureCollection(lineCollection);
        ListFeatureCollection polygonList = new ListFeatureCollection(polygonCollection);

        double sum;
        try (SimpleFeatureIterator lineIterator = lineList.features()) {
            sum = 0;
            while (lineIterator.hasNext()) {

                SimpleFeature sf = lineIterator.next();
                MultiLineString mls = (MultiLineString) sf.getDefaultGeometry();
                LineString p1 = (LineString) mls.getGeometryN(0);

                Filter filter = ff.intersects(ff.property("the_geom"), ff.literal(sf.getDefaultGeometry()));

                try (SimpleFeatureIterator polygonIterator = polygonList.subCollection(filter).features()) {
                    while (polygonIterator.hasNext()) {

                        SimpleFeature sf2 = polygonIterator.next();
                        MultiPolygon mls2 = (MultiPolygon) sf2.getDefaultGeometry();
                        Polygon ls = (Polygon) mls2.getGeometryN(0);
                        Geometry result = p1.intersection(ls);
                        if (result.getLength() != 0) {
                            sum += result.getLength();
                            lengths = lengths + "\n" + result.getLength();
                        }
                    }
                }
            }
        }

        return "Lines found: " + lengths + "\nSuma: " + sum;
    }
    
    public String lengthOfLineWithFilterCechy() throws Exception {

        FilterFactory2 ff = CommonFactoryFinder.getFilterFactory2();

        String lengths = "Delky linii";

        ShapefileDataStore sfds1 = new ShapefileDataStore(
                new URL("file:///F:\\GeoServer285\\data_dir\\data\\test_data\\zelezniceCechy.shp"));
        SimpleFeatureSource lineFeatureStore = sfds1.getFeatureSource();

        ShapefileDataStore sfds2 = new ShapefileDataStore(
                new URL("file:///F:\\GeoServer285\\data_dir\\data\\test_data\\uzemiCechy.shp"));
        SimpleFeatureSource polygonFeatureStore = sfds2.getFeatureSource();

        SimpleFeatureCollection lineCollection = DataUtilities.collection(lineFeatureStore.getFeatures());
        SimpleFeatureCollection polygonCollection = DataUtilities.collection(polygonFeatureStore.getFeatures());

        ListFeatureCollection lineList = new ListFeatureCollection(lineCollection);
        ListFeatureCollection polygonList = new ListFeatureCollection(polygonCollection);

        double sum;
        try (SimpleFeatureIterator lineIterator = lineList.features()) {
            sum = 0;
            while (lineIterator.hasNext()) {

                SimpleFeature sf = lineIterator.next();
                MultiLineString mls = (MultiLineString) sf.getDefaultGeometry();
                LineString p1 = (LineString) mls.getGeometryN(0);

                Filter filter = ff.intersects(ff.property("the_geom"), ff.literal(sf.getDefaultGeometry()));

                try (SimpleFeatureIterator polygonIterator = polygonList.subCollection(filter).features()) {
                    while (polygonIterator.hasNext()) {

                        SimpleFeature sf2 = polygonIterator.next();
                        MultiPolygon mls2 = (MultiPolygon) sf2.getDefaultGeometry();
                        Polygon ls = (Polygon) mls2.getGeometryN(0);
                        Geometry result = p1.intersection(ls);
                        if (result.getLength() != 0) {
                            sum += result.getLength();
                            lengths = lengths + "\n" + result.getLength();
                        }
                    }
                }
            }
        }

        return "Lines found: " + lengths + "\nSuma: " + sum;
    }

    public String overlayPolygonsWithFilter() throws IOException {

        long startTime = System.currentTimeMillis();

        FilterFactory2 ff = CommonFactoryFinder.getFilterFactory2();

        String areas = "Object : Area of overlay";

        ShapefileDataStore sfds = new ShapefileDataStore(
                new URL("file:///F:\\GeoServer285\\data_dir\\data\\test_data\\chranene_uzemi_cr.shp"));
        SimpleFeatureSource fs = sfds.getFeatureSource();

        ShapefileDataStore sfds2 = new ShapefileDataStore(new URL("file:///F:\\GeoServer285\\data_dir\\data\\test_data\\lesy_cr.shp"));
        SimpleFeatureSource fs2 = sfds2.getFeatureSource();

        SimpleFeatureCollection sfc = DataUtilities.collection(fs.getFeatures());
        SimpleFeatureCollection sfc2 = DataUtilities.collection(fs2.getFeatures());

        ListFeatureCollection sfcList = new ListFeatureCollection(sfc);
        ListFeatureCollection sfcList2 = new ListFeatureCollection(sfc2);

        double sum;
        try (SimpleFeatureIterator sfi = sfcList.features()) {
            sum = 0;
            while (sfi.hasNext()) {

                SimpleFeature sf = sfi.next();
                MultiPolygon mp2 = (MultiPolygon) sf.getDefaultGeometry();

                Filter filter = ff.intersects(ff.property("the_geom"), ff.literal(sf.getDefaultGeometry()));

                Polygon p2 = (Polygon) mp2.getGeometryN(0);

                try (SimpleFeatureIterator sfi2 = sfcList2.subCollection(filter).features()) {

                    while (sfi2.hasNext()) {
                        SimpleFeature sf2 = sfi2.next();
                        MultiPolygon mp3 = (MultiPolygon) sf2.getDefaultGeometry();
                        Polygon p3 = (Polygon) mp3.getGeometryN(0);
                        Geometry p4 = p2.intersection(mp3);
                        if (p4.getArea() != 0) {
                            sum += p4.getArea();
                            areas = areas + "\n" + p4.getArea() + " : " + p2.getArea() + " : " + p3.getArea();
                        }
                    }
                }
            }
        }

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println(elapsedTime);

        // result should be 7.5473089340863285E9
        return "Objects found: " + areas + "\nTotal sum: " + sum;

    }

    public String overlayPolygonsWithFilterMSK() throws IOException {

        long startTime = System.currentTimeMillis();

        FilterFactory2 ff = CommonFactoryFinder.getFilterFactory2();

        String areas = "Object : Area of overlay";

        ShapefileDataStore sfds = new ShapefileDataStore(
                new URL("file:///F:\\GeoServer285\\data_dir\\data\\test_data\\chranene_uzemi_msk.shp"));
        SimpleFeatureSource fs = sfds.getFeatureSource();

        ShapefileDataStore sfds2 = new ShapefileDataStore(new URL("file:///F:\\GeoServer285\\data_dir\\data\\test_data\\lesy_msk.shp"));
        SimpleFeatureSource fs2 = sfds2.getFeatureSource();

        SimpleFeatureCollection sfc = DataUtilities.collection(fs.getFeatures());
        SimpleFeatureCollection sfc2 = DataUtilities.collection(fs2.getFeatures());

        ListFeatureCollection sfcList = new ListFeatureCollection(sfc);
        ListFeatureCollection sfcList2 = new ListFeatureCollection(sfc2);

        double sum;
        try (SimpleFeatureIterator sfi = sfcList.features()) {
            sum = 0;
            while (sfi.hasNext()) {

                SimpleFeature sf = sfi.next();
                MultiPolygon mp2 = (MultiPolygon) sf.getDefaultGeometry();

                Filter filter = ff.intersects(ff.property("the_geom"), ff.literal(sf.getDefaultGeometry()));

                Polygon p2 = (Polygon) mp2.getGeometryN(0);

                try (SimpleFeatureIterator sfi2 = sfcList.subCollection(filter).features()) {

                    while (sfi2.hasNext()) {
                        SimpleFeature sf2 = sfi2.next();
                        MultiPolygon mp3 = (MultiPolygon) sf2.getDefaultGeometry();
                        Polygon p3 = (Polygon) mp3.getGeometryN(0);
                        Geometry p4 = p2.intersection(mp3);
                        if (p4.getArea() != 0) {
                            sum += p4.getArea();
                            areas = areas + "\n" + p4.getArea() + " : " + p2.getArea() + " : " + p3.getArea();
                        }
                    }
                }
            }
        }

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println(elapsedTime);

        return "Objects found: " + areas + "\nTotal sum: " + sum;

    }
    
    public String overlayPolygonsWithFilterCechy() throws IOException {

        long startTime = System.currentTimeMillis();

        FilterFactory2 ff = CommonFactoryFinder.getFilterFactory2();

        String areas = "Object : Area of overlay";

        ShapefileDataStore sfds = new ShapefileDataStore(
                new URL("file:///F:\\GeoServer285\\data_dir\\data\\test_data\\uzemiCechy.shp"));
        SimpleFeatureSource fs = sfds.getFeatureSource();

        ShapefileDataStore sfds2 = new ShapefileDataStore(new URL("file:///F:\\GeoServer285\\data_dir\\data\\test_data\\lesyCechy.shp"));
        SimpleFeatureSource fs2 = sfds2.getFeatureSource();

        SimpleFeatureCollection sfc = DataUtilities.collection(fs.getFeatures());
        SimpleFeatureCollection sfc2 = DataUtilities.collection(fs2.getFeatures());

        ListFeatureCollection sfcList = new ListFeatureCollection(sfc);
        ListFeatureCollection sfcList2 = new ListFeatureCollection(sfc2);

        double sum;
        try (SimpleFeatureIterator sfi = sfcList.features()) {
            sum = 0;
            while (sfi.hasNext()) {

                SimpleFeature sf = sfi.next();
                MultiPolygon mp2 = (MultiPolygon) sf.getDefaultGeometry();

                Filter filter = ff.intersects(ff.property("the_geom"), ff.literal(sf.getDefaultGeometry()));

                Polygon p2 = (Polygon) mp2.getGeometryN(0);

                try (SimpleFeatureIterator sfi2 = sfcList.subCollection(filter).features()) {

                    while (sfi2.hasNext()) {
                        SimpleFeature sf2 = sfi2.next();
                        MultiPolygon mp3 = (MultiPolygon) sf2.getDefaultGeometry();
                        Polygon p3 = (Polygon) mp3.getGeometryN(0);
                        Geometry p4 = p2.intersection(mp3);
                        if (p4.getArea() != 0) {
                            sum += p4.getArea();
                            areas = areas + "\n" + p4.getArea() + " : " + p2.getArea() + " : " + p3.getArea();
                        }
                    }
                }
            }
        }

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println(elapsedTime);

        return "Objects found: " + areas + "\nTotal sum: " + sum;

    }
}
