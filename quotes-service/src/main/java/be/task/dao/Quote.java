package be.task.dao;

public class Quote {

    private long id;
    private String isin;
    private double bid;
    private double ask;
    private double elvl = 0f;

    public Quote(String isin, double bid, double ask) {
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

    public double getBid() {
        return bid;
    }

    public void setBid(double bid) {
        this.bid = bid;
    }

    public double getAsk() {
        return ask;
    }

    public void setAsk(double ask) {
        this.ask = ask;
    }

    public double getElvl() {
        return elvl;
    }

    public void setElvl(double elvl) {
        this.elvl = elvl;
    }

    @Override
    public String toString() {
        return "Quote{" + "id=" + id + ", isin=" + isin + ", bid='"
                + bid + '\'' + ", ask='" + ask
                + ", elvl='" + elvl + '\'' + '}';
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        } else if (o == null || !(o instanceof Quote)) {
            return false;
        } else {
            return areEqual(this, (Quote) o);
        }
    }

    private boolean areEqual(Quote q1, Quote q2) {

        boolean result;

        if (q1.getIsin() == null) {
            result = q2.getIsin() == null;
        } else {
            result = q1.getIsin().equals(q2.getIsin());
        }

        result = result && q1.getId() == q2.getId();
        result = result && q1.getBid() == q2.getBid();
        result = result && q1.getAsk() == q2.getAsk();
        result = result && q1.getElvl() == q2.getElvl();

        return result;
    }

}
