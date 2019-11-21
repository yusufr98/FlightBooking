package com.example.demo;

import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@Controller
public class HomeController {
    @Autowired
    FlightRepository flightRepository;

    @Autowired
    CloudinaryConfig cloudc;

    @RequestMapping("/")
    public String listFlights(Model model){
        model.addAttribute("flights", flightRepository.findAll());
        return "list";
    }

    @GetMapping("/add")
    public String addFlight(Model model){
        model.addAttribute("flight", new Flight());
        return "addflight";
    }
    @PostMapping("/search")
    public String search(Model model, @RequestParam("search") String s){
        model.addAttribute("flights", flightRepository.findByAirlineContainingIgnoreCaseOrArrivalContainingIgnoreCaseOrDepartureContainingIgnoreCaseOrFlightNumberContainingIgnoreCase(s,s,s,s));
        return "list";
    }
    @PostMapping("/process")
    public String processForm(@Valid @ModelAttribute Flight flight, @RequestParam("file")MultipartFile file){
        if(!file.isEmpty()){
            try {
                Map uploadResult = cloudc.upload(file.getBytes(), ObjectUtils.asMap("resourcetype", "auto"));
                flight.setUrl(uploadResult.get("url").toString());
                flightRepository.save(flight);
            } catch (IOException e) {
                e.printStackTrace();
                return "redirect:/add";
            }
        }
        else{
            flightRepository.save(flight);
        }
        return "redirect:/";
    }
    @RequestMapping("/show/{id}")
    public String showFlight(@PathVariable("id") long id, Model model){
        model.addAttribute("flight", flightRepository.findById(id).get());
        return "show";
    }

    @RequestMapping("/update/{id}")
    public String updateFlight(@PathVariable("id") long id, Model model){
        model.addAttribute("flight", flightRepository.findById(id).get());
        return "addflight";
    }

    @RequestMapping("/delete/{id}")
    public String delFlight(@PathVariable("id") long id){
        flightRepository.deleteById(id);
        return "redirect:/";
    }
}
