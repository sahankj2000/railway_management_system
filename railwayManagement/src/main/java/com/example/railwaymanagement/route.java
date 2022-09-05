package com.example.railwaymanagement;

public class route {
    String rtID,dist,fares,depTime,startSTID,startSTName,endSTID,endSTName,trainID,trainName;

    public route(String rtID, String dist, String fares, String depTime, String startSTID, String startSTName, String endSTID, String endSTName, String trainID, String trainName) {
        this.rtID = rtID;
        this.dist = dist;
        this.fares = fares;
        this.depTime = depTime;
        this.startSTID = startSTID;
        this.startSTName = startSTName;
        this.endSTID = endSTID;
        this.endSTName = endSTName;
        this.trainID = trainID;
        this.trainName = trainName;
    }

    public String getRtID() {
        return rtID;
    }

    public String getDist() {
        return dist;
    }

    public String getFares() {
        return fares;
    }

    public String getDepTime() {
        return depTime;
    }

    public String getStartSTID() {
        return startSTID;
    }

    public String getStartSTName() {
        return startSTName;
    }

    public String getEndSTID() {
        return endSTID;
    }

    public String getEndSTName() {
        return endSTName;
    }

    public String getTrainID() {
        return trainID;
    }

    public String getTrainName() {
        return trainName;
    }
}
