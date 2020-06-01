package com.pluralsight.conference.controllers;

import com.pluralsight.conference.exceptions.MissingParameterException;
import com.pluralsight.conference.exceptions.ResourceNotFoundException;
import com.pluralsight.conference.models.Session;
import com.pluralsight.conference.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionsController {
    private SessionRepository sessionRepository;

    public SessionsController(@Autowired SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @GetMapping()

    public List<Session> list(){
        return sessionRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Session get(@PathVariable Long id){
        return sessionRepository.findById(id).get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Session create(@Valid @RequestBody final Session session, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new MissingParameterException("You must provide all fields");
        }
        return sessionRepository.saveAndFlush(session);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        sessionRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Session update(@PathVariable Long id, @RequestBody @Valid Session session, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            //throw bad request exception
            throw new MissingParameterException("You must provide all fields");
        }
        Optional<Session> existingSession = sessionRepository.findById(id);
        if (existingSession.isPresent() == false){
            throw new ResourceNotFoundException("Session not found");
        }
        System.out.println("aca no deberia llegar en invalido");
        BeanUtils.copyProperties(session, existingSession.get(), "sessionID");
        return sessionRepository.saveAndFlush(existingSession.get());
    }
}
