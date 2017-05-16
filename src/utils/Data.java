package utils;

import java.sql.Time;
import java.sql.Timestamp;

/**
 * Created by bobby on 16-05-2017.
 */
public class Data {

    private String Serial;
    private Integer calatoriiRamase;
    private Timestamp validareInitiala;
    private Timestamp dataExpirarii;
    private Timestamp ultimaValidare;

    public Data(String serial, Integer calatoriiRamase, Timestamp validareInitiala, Timestamp dataExpirarii, Timestamp ultimaValidare) {
        Serial = serial;
        this.calatoriiRamase = calatoriiRamase;
        this.validareInitiala = validareInitiala;
        this.dataExpirarii = dataExpirarii;
        this.ultimaValidare = ultimaValidare;
    }

    public String getSerial() {
        return Serial;
    }

    public Integer getCalatoriiRamase() {
        return calatoriiRamase;
    }

    public Timestamp getValidareInitiala() {
        return validareInitiala;
    }

    public Timestamp getDataExpirarii() {
        return dataExpirarii;
    }

    public Timestamp getUltimaValidare() {
        return ultimaValidare;
    }
}
