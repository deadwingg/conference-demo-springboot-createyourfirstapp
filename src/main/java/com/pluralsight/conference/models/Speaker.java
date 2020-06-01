package com.pluralsight.conference.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

// with jsonidentity info you can avopid jackson infinite recursion problems
/*@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "speakerID")*/
//another option is to ignore one side of the relationchip, in this case the speaker one
//you also need to exclude hibernate properties from serialization
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity(name = "speakers")
public class Speaker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "speaker_id")
    private Long speakerID;

    @Length(max = 30)
    @Column(name = "first_name")
    @NotNull
    private String firstName;

    @Length(max = 30)
    @Column(name = "last_name")
    @NotNull
    private String lastName;

    @Length(max = 40)
    @Column(name = "title")
    @NotNull
    private String title;

    @Length(max = 50)
    @Column(name = "company")
    @NotNull
    private String company;

    @Length(max = 2000)
    @Column(name = "speaker_bio")
    @NotNull
    private String speakerBio;

    @Lob
    @org.hibernate.annotations.Type(type = "org.hibernate.type.BinaryType")
    private byte[] speaker_photo;

    @ManyToMany(mappedBy = "speakers")
    @JsonIgnore
    private List<Session> sessions;

    public Speaker() {
    }

    public byte[] getSpeaker_photo() {
        return speaker_photo;
    }

    public void setSpeaker_photo(byte[] speaker_photo) {
        this.speaker_photo = speaker_photo;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

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
