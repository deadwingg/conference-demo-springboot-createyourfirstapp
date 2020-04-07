package com.pluralsight.conference.controllers;

import com.pluralsight.conference.models.Session;
import com.pluralsight.conference.models.Speaker;
import com.pluralsight.conference.repositories.SpeakerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/speakers")
public class SpeakersController {
    private SpeakerRepository speakerRepository;

    public SpeakersController(@Autowired SpeakerRepository speakerRepository) {
        this.speakerRepository = speakerRepository;
    }

    @GetMapping
    public List<Speaker> list() {
        return speakerRepository.findAll();
    }

    @GetMapping
    @RequestMapping("/{id}")
    public Speaker get(@PathVariable Long id){
        return speakerRepository.findById(id).get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Speaker create(@RequestBody final Speaker speaker){
        return speakerRepository.saveAndFlush(speaker);
    }
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        speakerRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Speaker update(@PathVariable Long id, @RequestBody Speaker session){
        Speaker existingSpeaker = speakerRepository.getOne(id);
        BeanUtils.copyProperties(session, existingSpeaker, "sessionID");
        return speakerRepository.saveAndFlush(existingSpeaker);
    }
}
