package com.example.railwaymanagement;

public class ticket {
    String tkID,psName,psAge,psGender,platform,tkClass,tkDate,amt,tkRTID,tkStartST,tkEndST,seatNo;

    public ticket(String tkID, String psName, String psAge, String psGender, String platform, String tkClass, String tkDate, String amt, String tkRTID, String tkStartST, String tkEndST,String seatNo) {
        this.tkID = tkID;
        this.psName = psName;
        this.psAge = psAge;
        this.psGender = psGender;
        this.platform = platform;
        this.tkClass = tkClass;
        this.tkDate = tkDate;
        this.amt = amt;
        this.tkRTID = tkRTID;
        this.tkStartST = tkStartST;
        this.tkEndST = tkEndST;
        this.seatNo=seatNo;
    }

    public String getTkID() {
        return tkID;
    }

    public String getPsName() {
        return psName;
    }

    public String getPsAge() {
        return psAge;
    }

    public String getPsGender() {
        return psGender;
    }

    public String getPlatform() {
        return platform;
    }

    public String getTkClass() {
        return tkClass;
    }

    public String getTkDate() {
        return tkDate;
    }

    public String getAmt() {
        return amt;
    }

    public String getTkRTID() {
        return tkRTID;
    }

    public String getTkStartST() {
        return tkStartST;
    }

    public String getTkEndST() {
        return tkEndST;
    }

    public String getSeatNo() {
        return seatNo;
    }
}
