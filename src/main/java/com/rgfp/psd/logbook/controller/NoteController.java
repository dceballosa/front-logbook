package com.rgfp.psd.logbook.controller;

import com.rgfp.psd.logbook.domain.Note;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class NoteController {

    // displays all notes
    @RequestMapping(value={"/", "notes"})
    public String noteList(Model model, @RequestParam(required = false, name = "filter") String filter) {
        // filter feature not yet implemented
        RestTemplate restTemplate = new RestTemplate();
        List<Note> notes = restTemplate.getForObject("http://localhost:8080/notes", List.class);
        model.addAttribute("noteList", notes);
        return "noteList";
    }

    // display one note with details
    @RequestMapping(value={"noteView/{id}"}, method = RequestMethod.GET)
    public String noteView(Model model, @PathVariable(name = "id") Long id) {
        RestTemplate restTemplate = new RestTemplate();
        Note note = restTemplate.getForObject("http://localhost:8080/notes/"+id, Note.class);
        model.addAttribute("note", note);
        return "noteView";
    }

    // displays edit note template
    @RequestMapping(value={"/noteEdit","/noteEdit/{id}"}, method = RequestMethod.GET)
    public String noteEditForm(Model model, @PathVariable(required = false, name = "id") Long id) {
        if (null != id) {
            RestTemplate restTemplate = new RestTemplate();
            Note note = restTemplate.getForObject("http://localhost:8080/notes/"+id, Note.class);
            model.addAttribute("note", note);
        } else {
            model.addAttribute("note", new Note());
        }
        return "noteEdit";
    }

    // creates a new note
    @RequestMapping(value="/noteEdit", method = RequestMethod.POST)
    public String noteEdit(Model model, Note note) {
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.postForObject("http://localhost:8080/notes", note, String.class);
        List<Note> notes = restTemplate.getForObject("http://localhost:8080/notes", List.class);
        model.addAttribute("noteList", notes);
        return "noteList";
    }

    // clones a note. creating a new one
    @RequestMapping(value={"/noteClone","/noteClone/{id}"}, method = RequestMethod.GET)
    public String noteClone(Model model, @PathVariable(name = "id") Long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject("http://localhost:8080/noteClone/"+id, List.class);
        List<Note> notes = restTemplate.getForObject("http://localhost:8080/notes", List.class);
        model.addAttribute("noteList", notes);
        return "noteList";

    }

    // deletes a note
    @RequestMapping(value="/noteDelete/{id}", method = RequestMethod.GET)
    public String noteDelete(Model model, @PathVariable(required = true, name = "id") Long id) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject("http://localhost:8080/noteDelete/"+id, List.class);
        List<Note> notes = restTemplate.getForObject("http://localhost:8080/notes", List.class);
        model.addAttribute("noteList", notes);
        return "noteList";
    }

}