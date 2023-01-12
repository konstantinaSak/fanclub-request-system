package gr.hua.ds.fanclubrequestsystem.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "request_elas")
public class RequestELAS {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
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
    @JoinColumn(name="ELAS_ID")
    private ELAS ELAS;

    public RequestELAS() {
    }

    public RequestELAS(int ID, Date date, String text, String state, Fanclub fanclub, ELAS ELAS) {
        this.ID = ID;
        this.date = date;
        this.text = text;
        this.state = state;
        this.fanclub = fanclub;
        this.ELAS = ELAS;
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

    public gr.hua.ds.fanclubrequestsystem.entity.ELAS getELAS() {
        return ELAS;
    }

    public void setELAS(ELAS ELAS) {
        this.ELAS = ELAS;
    }

    @Override
    public String toString() {
        return "RequestELAS{" +
                "ID=" + ID +
                ", date=" + date +
                ", text='" + text + '\'' +
                ", state='" + state + '\'' +
                ", fanclub=" + fanclub +
                ", ELAS=" + ELAS +
                '}';
    }

}
