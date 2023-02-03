package gr.hua.ds.fanclubrequestsystem.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "fanclub")
public class Fanclub {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int ID;

    @Column(name = "name")
    private String name;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "leader")
    private String leader;

    @Column(name = "sports_club")
    private String sportsClub;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "date_foundation", nullable = false)
    private String dateFoundation;

    public Fanclub() {
    }

    public Fanclub(int ID, String name, String username, String password, String leader, String sportsClub, String address, String email, String dateFoundation) {
        this.ID = ID;
        this.name = name;
        this.username = username;
        this.password = password;
        this.leader = leader;
        this.sportsClub = sportsClub;
        this.address = address;
        this.email = email;
        this.dateFoundation = dateFoundation;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getSportsClub() {
        return sportsClub;
    }

    public void setSportsClub(String sportsClub) {
        this.sportsClub = sportsClub;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateFoundation() {
        return dateFoundation;
    }

    public void setDateFoundation(String dateFoundation) {
        this.dateFoundation = dateFoundation;
    }

    @Override
    public String toString() {
        return "Fanclub{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", leader='" + leader + '\'' +
                ", sportsClub='" + sportsClub + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", dateFoundation=" + dateFoundation +
                '}';
    }

}
