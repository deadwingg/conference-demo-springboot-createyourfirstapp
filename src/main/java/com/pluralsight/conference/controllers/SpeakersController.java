package com.pluralsight.conference.controllers;

import com.pluralsight.conference.exceptions.MissingParameterException;
import com.pluralsight.conference.exceptions.ResourceNotFoundException;
import com.pluralsight.conference.models.Speaker;
import com.pluralsight.conference.repositories.SpeakerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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
    public Speaker create(@Valid @RequestBody final Speaker speaker, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new MissingParameterException("You must provide all fields");
        }
        return speakerRepository.saveAndFlush(speaker);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        speakerRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Speaker update(@PathVariable Long id, @Valid @RequestBody Speaker speaker, BindingResult result){
        if (result.hasErrors()){
            throw new MissingParameterException("You must provide all fields");
        }
        Optional<Speaker> existingSpeaker = speakerRepository.findById(id);
        if (existingSpeaker.isPresent() == false){
            throw new ResourceNotFoundException("Speaker not found");
        }
        BeanUtils.copyProperties(speaker, existingSpeaker.get(), "speakerID");
        return speakerRepository.saveAndFlush(existingSpeaker.get());
    }
}
