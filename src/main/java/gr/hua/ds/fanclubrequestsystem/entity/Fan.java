package gr.hua.ds.fanclubrequestsystem.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "fan")
public class Fan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int ID;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birth_date", nullable = false)
    private String birthDate;

    @Column(name = "AFM", nullable = false)
    private int AFM;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="fanclub_ID")
    private Fanclub fanclub;

    public Fan() {
    }

    public Fan(int ID, String firstName, String lastName, String birthDate, int AFM, Fanclub fanclub) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.AFM = AFM;
        this.fanclub = fanclub;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public int getAFM() {
        return AFM;
    }

    public void setAFM(int AFM) {
        this.AFM = AFM;
    }

    public Fanclub getFanclub() {
        return fanclub;
    }

    public void setFanclub(Fanclub fanclub) {
        this.fanclub = fanclub;
    }

    @Override
    public String toString() {
        return "Fan{" +
                "ID=" + ID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", AFM=" + AFM +
                ", fanclub=" + fanclub +
                '}';
    }

}
