package me.phqsh.ns4j.request.dump;

import me.phqsh.ns4j.containers.nation.Nation;
import me.phqsh.ns4j.containers.region.Region;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.zip.GZIPInputStream;

public class DataDumpDownloader {
    private static final String DEFAULT_DIRECTORY = "./ns4j/";
    private static final SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");

    private static void downloadFiles(String directory, String url, String destination) throws IOException {
        File dir = new File(directory);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File oldFile = new File(destination);

        if (oldFile.exists()) {
            // there is already a dump from this date
            return;
        }

        // download the file
        Files.copy(new URL(url).openStream(), Paths.get(destination));
    }

    private static Object parseWrapper(String path, Class<?> clazz) throws IOException, JAXBException {
        FileInputStream fis = new FileInputStream(path);
        GZIPInputStream gzip = new GZIPInputStream(fis);

        JAXBContext context = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        Object wrapper = unmarshaller.unmarshal(gzip);

        fis.close();
        gzip.close();

        return wrapper;
    }

    private static String getFileName(Date date, String type) {
        String base = formatter.format(date) + "-" + type;
        Calendar cutoff = Calendar.getInstance();
        cutoff.set(2018, Calendar.SEPTEMBER, 30);
        if (date.before(cutoff.getTime())) {
            base += ".xml.bz2";
        } else {
            base += "-xml.gz";
        }

        return base;
    }

    public static List<Nation> downloadNationDump(String directory, Date date) throws IOException, JAXBException {
        String url = "https://www.nationstates.net/archive/nations/" + getFileName(date, "nations");
        String destination = directory + getFileName(date, "nations");

        downloadFiles(directory, url, destination);
        NationWrapper wrapper = (NationWrapper) parseWrapper(destination, NationWrapper.class);

        return wrapper.nations;
    }

    public static List<Nation> downloadNationDump(Date date) throws IOException, JAXBException {
        return downloadNationDump(DEFAULT_DIRECTORY, date);
    }

    public static List<Region> downloadRegionDump(String directory, Date date) throws IOException, JAXBException {
        String url = "https://www.nationstates.net/archive/regions/" + getFileName(date, "regions");
        String destination = directory + getFileName(date, "regions");

        downloadFiles(directory, url, destination);
        RegionWrapper wrapper = (RegionWrapper) parseWrapper(destination, RegionWrapper.class);

        return wrapper.regions;
    }

    public static List<Region> downloadRegionDump(Date date) throws JAXBException, IOException {
        return downloadRegionDump(DEFAULT_DIRECTORY, date);
    }

    @XmlRootElement(name="REGIONS")
    @XmlAccessorType(XmlAccessType.FIELD)
    private static class RegionWrapper {
        @XmlElement(name = "REGION")
        private List<Region> regions;
    }

    @XmlRootElement(name="NATIONS")
    @XmlAccessorType(XmlAccessType.FIELD)
    private static class NationWrapper {
        @XmlElement(name = "NATION")
        private List<Nation> nations;
    }
}
