package com.groggyman.parsers

import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import org.apache.commons.csv.CSVRecord

import java.nio.file.Paths

class CSVParseUtils {

    //
    final static char delimiter = ','
    final static int maxLines = 100000

    // if closure is used
    CSVParser csvFile
    def record
    def headers


    CSVParseUtils(String fileLocation) {
        def reader = Paths.get(fileLocation).newReader()
        CSVFormat format = CSVFormat.DEFAULT.withHeader().withDelimiter(delimiter)
        csvFile = new CSVParser(reader, format)
        def header = csvFile.headerMap.keySet().first()
        headers = header.split(delimiter as String)
    }

    CSVParseUtils(String fileLocation, boolean hasHeader) {
        def reader = Paths.get(fileLocation).newReader()
        CSVFormat format = hasHeader ? CSVFormat.DEFAULT.withHeader().withDelimiter(delimiter) : CSVFormat.DEFAULT.withDelimiter(delimiter)
        csvFile = new CSVParser(reader, format)
        def header = csvFile.headerMap.keySet().first()
        headers = header.split(delimiter as String)
    }

    CSVParseUtils(String fileLocation, char delim) {
        def reader = Paths.get(fileLocation).newReader()
        CSVFormat format = CSVFormat.DEFAULT.withHeader().withDelimiter(delim)
        csvFile = new CSVParser(reader, format)
        def header = csvFile.headerMap.keySet().first()
        headers = header.split(delim as String)
    }

    CSVParseUtils(String fileLocation, hasHeader, char delim) {
        def reader = Paths.get(fileLocation).newReader()
        CSVFormat format = hasHeader ? CSVFormat.DEFAULT.withHeader().withDelimiter(delim) : CSVFormat.DEFAULT.withDelimiter(delim)
        csvFile = new CSVParser(reader, format)
        def header = csvFile.headerMap.keySet().first()
        headers = header.split(delim as String)
    }

    /**
     * List each line of the csv and execute closure
     * @param params
     * @param closure
     */
    def eachLine(Map params = [:], Closure closure) {
        def max = params.max ?: maxLines
        int linesRead = 0
        def rowIterator = csvFile.iterator()
        closure.setDelegate(this)

        while (rowIterator.hasNext() && linesRead++ < max) {
            record = rowIterator.next()
            closure.call(record)
        }
    }

    /**
     * Default parser that returns map with row number and row data
     * @param fileLocation
     * @return
     */
    static Map parse(String fileLocation) {
        def records = [:]
        Paths.get(fileLocation).withReader { reader ->
            CSVParser csv = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())
            csv.iterator().each { CSVRecord record ->
                records.put(record.recordNumber, record.values)
            }
        }
        return records
    }

    /**
     * Default parser that returns map with row number and row data with the header line
     * @param fileLocation
     * @return
     */
    static Map parseWithHeader(String fileLocation) {
        def records = [:]
        Paths.get(fileLocation).withReader { reader ->
            CSVParser csv = new CSVParser(reader, CSVFormat.DEFAULT)
            csv.iterator().each { CSVRecord record ->
                records.put(record.recordNumber, record.values)
            }
        }
        return records
    }

    /**
     * Returns a list of the headers (Default parser)
     * @param fileLocation
     * @return
     */
    static List parseHeaders(String fileLocation) {
        def listing = []
        Paths.get(fileLocation).withReader { reader ->
            CSVParser csv = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())
            listing.addAll(csv.headerMap.keySet())
        }
        return listing
    }

    /**
     * Returns a list of values from the string (Default parser)
     * @param fileLocation
     * @return
     */
    static List parseLine(String line) {
        def listing = []
        CSVParser csv = CSVParser.parse(line, CSVFormat.DEFAULT)
        csv.iterator().each { CSVRecord record ->
            listing.addAll(record.values)
        }
        return listing
    }
}
