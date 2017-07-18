package com.groggyman.main

import com.groggyman.parsers.CSVParseUtils
import org.apache.commons.csv.CSVRecord

class CSVParsingPrimer {
    static void main(String... args) {
        // Parse a line
        println CSVParseUtils.parseLine("ANCESTOR,ASSETNUM,ASSETTAG,ASSETTYPE,AUTOWOGEN,BINNUM,BUDGETCOST,CALNUM,CHANGEBY,CHANGEDATE,CHANGEPMSTATUS,CHILDREN,CONDITIONCODE,AS_DESCRIPTION,AS_DESCRIPTION_LD,DIRECTION,DISABLED,ENDDESCRIPTION,ENDMEASURE,EQ1,EQ10,EQ11,EQ12,EQ2,EQ23,EQ24,EQ3,EQ4,EQ5,EQ6,EQ7,EQ8,EQ9,AS_EXTERNALREFID,FAILURECODE,GLACCOUNT,GROUPNAME,HIERARCHYPATH,INSTALLDATE,INVCOST,ISLINEAR,ISRUNNING,AS_ITEMNUM,AS_ITEMSETID,ITEMTYPE,AS_LOCATION,LRM,MAINTHIERCHY,AS_MANUFACTURER,MOVED,AS_ORGID,AS_OWNERSYSID,PARENT,PRIORITY,PURCHASEPRICE,REMOVEFROMACTIVEROUTES,REMOVEFROMACTIVESP,REPLACECOST,ROLLTOALLCHILDREN,ROTSUSPACCT,AS_SENDERSYSID,SERIALNUM,SHIFTNUM,AS_SITEID,AS_SOURCESYSID,STARTDESCRIPTION,STARTMEASURE,AS_STATUS,AS_STATUSDATE,TOOLCONTROLACCOUNT,TOOLRATE,TOTALCOST,TOTDOWNTIME,TOTUNCHARGEDCOST,UNCHARGEDCOST,USAGE,VENDOR,WARRANTYEXPDATE,YTDCOST,ACTIVE,ASSETMETERID,AVERAGE,AVGCALCMETHOD,BASEMEASUREUNITID,AM_CHANGEBY,AM_CHANGEDATE,ENDASSETFEATUREID,ENDBASEMEASURE,ENDFEATURE,ENDFEATUREID,ASM_ENDMEASURE,ENDMEASUREUNITID,ENDOFFSET,ENDOFFSETUNITID,ENDYOFFSET,ASM_ENDYOFFSETREF,ENDZOFFSET,ASM_ENDZOFFSETREF,LASTREADING,LASTREADINGDATE,LASTREADINSPECTOR,LIFETODATE,LINEARASSETMETERID,MEASUREUNITID,METERNAME,AM_ORGID,POINTNUM,READINGTYPE,REMARKS,ROLLDOWNSOURCE,ROLLOVER,SEQUENCE,SINCEINSTALL,SINCELASTINSPEC,SINCELASTOVERHAUL,SINCELASTREPAIR,SLIDINGWINDOWSIZE,STARTASSETFEATUREID,STARTBASEMEASURE,STARTFEATURE,STARTFEATUREID,ASM_STARTMEASURE,STARTMEASUREUNITID,STARTOFFSET,STARTOFFSETUNITID,STARTYOFFSET,ASM_STYOFFSETREF,STARTZOFFSET,ASM_STZOFFSETREF,ADDPERSON,ISCUSTODIAN,ISPRIMARY,ISUSER,LOCATION,MODIFYPERSON,MULTIID,AC_ORGID,PERSONID,REMOVEPERSON,WILLBECUSTODIAN,WILLBEPRIMARY,WILLBEUSER,ALNVALUE,ASSETATTRID,ASSETSPECID,AS_BASEMEASURUTID,ASPEC_CHANGEBY,ASPEC_CHANGEDATE,CLASSSTRUCTUREID,CONTINUOUS,DISPLAYSEQUENCE,AS_ENDASSETFEATID,AS_ENDBASEMSURE,ASPEC_ENDMEASURE,AS_ENDMEASURUTID,ASPEC_ENDOFFSET,AS_ENDOFFSETUTID,AS_ENDYOFFSET,AS_ENDYOFFSETREF,AS_ENDZOFFSET,AS_ENDZOFFSETREF,ES01,ES02,ES03,ES04,ES05,INHERITEDFROMITEM,ITEMSPECVALCHANGED,LINEARASSETSPECID,LINKEDTOATTRIBUTE,LINKEDTOSECTION,MANDATORY,ASPC_MESRUNITID,NUMVALUE,ORGID,SECTION,AS_STASSETFEATID,AS_STBASEMEASURE,ASPEC_STARTMEASURE,AS_STMEASUREUNITID,ASPEC_STARTOFFSET,AS_STATOFFSETUTID,AS_STYOFFSET,AS_STYOFFSETREF,AS_STZOFFSET,AS_STZOFFSETREF,TABLEVALUE")

        String fileName = "AssetsImportCompleteSample.csv"
        String fileLocation = CSVParsingPrimer.class.getClassLoader().getResource(fileName).getFile()

        // Parse headers
        println CSVParseUtils.parseHeaders(fileLocation)
        // Parse whole file
        println CSVParseUtils.parse(fileLocation)
        // Parse with headers
        println CSVParseUtils.parseWithHeader(fileLocation)
        // custom transformation of lines
        def parser = new CSVParseUtils(fileLocation)
        def result = [:]
        parser.eachLine([max: 2]) { CSVRecord record ->
            result.put(record.recordNumber, record.values.size() > 4 ? record.values[0..4] : record.values[0..record.values.size()])
        }
        println result
    }
}