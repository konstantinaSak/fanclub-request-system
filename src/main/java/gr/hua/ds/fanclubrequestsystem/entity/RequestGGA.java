package gr.hua.ds.fanclubrequestsystem.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "request_gga")
public class RequestGGA {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(name = "text")
    private String text;

    @Column(name = "state")
    private String state;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="fanclub_ID")
    private Fanclub fanclub;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="GGA_ID")
    private GGA GGA;

    public RequestGGA() {
    }

    public RequestGGA(int ID, Date date, String text, String state, Fanclub fanclub, GGA GGA) {
        this.ID = ID;
        this.date = date;
        this.text = text;
        this.state = state;
        this.fanclub = fanclub;
        this.GGA = GGA;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Fanclub getFanclub() {
        return fanclub;
    }

    public void setFanclub(Fanclub fanclub) {
        this.fanclub = fanclub;
    }

    public gr.hua.ds.fanclubrequestsystem.entity.GGA getGGA() {
        return GGA;
    }

    public void setGGA(gr.hua.ds.fanclubrequestsystem.entity.GGA GGA) {
        this.GGA = GGA;
    }

    @Override
    public String toString() {
        return "RequestGGA{" +
                "ID=" + ID +
                ", date=" + date +
                ", text='" + text + '\'' +
                ", state='" + state + '\'' +
                ", fanclub=" + fanclub +
                ", GGA=" + GGA +
                '}';
    }
    
}
