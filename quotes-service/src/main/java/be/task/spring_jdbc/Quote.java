package be.task.spring_jdbc;

public class Quote {

    private long id;
    private String isin;
    private float bid;
    private float ask;
    private float elvl = -1f;

    public Quote(String isin, float bid, float ask) {
        this.isin = isin;
        this.bid = bid;
        this.ask = ask;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }

    public float getBid() {
        return bid;
    }

    public void setBid(float bid) {
        this.bid = bid;
    }

    public float getAsk() {
        return ask;
    }

    public void setAsk(float ask) {
        this.ask = ask;
    }

    public float getElvl() {
        return elvl;
    }

    public void setElvl(float elvl) {
        this.elvl = elvl;
    }
    
    
     @Override
    public String toString() {
        return "Quote{" + "id=" + id + ", isin=" + isin + ", bid='" 
                + bid + '\'' + ", ask='" + ask
                + ", elvl='" + elvl + '\'' + '}';
    }
    
}
