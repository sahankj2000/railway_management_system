package backend;

public class station {
    String stID,stName,stTel;
    public String getStID(){return stID;}
    public String getStName(){return stName;}
    public String getStTel(){return stTel;}
    public station(String stID,String stName,String stTel){
        this.stID=stID;
        this.stTel=stTel;
        this.stName=stName;
    }
}
