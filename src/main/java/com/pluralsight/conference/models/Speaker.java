package com.pluralsight.conference.models;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity(name = "speakers")
public class Speaker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "speaker_id")
    private Long speakerID;

    @Length(max = 30)
    @Column(name = "first_name")
    private String firstName;

    @Length(max = 30)
    @Column(name = "last_name")
    private String lastName;

    @Length(max = 40)
    @Column(name = "title")
    private String title;

    @Length(max = 50)
    @Column(name = "company")
    private String company;

    @Length(max = 2000)
    @Column(name = "speaker_bio")
    private String speakerBio;

    public Long getSpeakerID() {
        return speakerID;
    }

    public void setSpeakerID(Long speakerID) {
        this.speakerID = speakerID;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getSpeakerBio() {
        return speakerBio;
    }

    public void setSpeakerBio(String speakerBio) {
        this.speakerBio = speakerBio;
    }
}
