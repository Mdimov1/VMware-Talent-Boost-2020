package org.vmware.finaltask.networkOfGiving.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.vmware.finaltask.networkOfGiving.service.CharityServiceImpl;
import org.vmware.finaltask.networkOfGiving.service.ParticipantsService;
import org.vmware.finaltask.networkOfGiving.model.Charity;
import org.vmware.finaltask.networkOfGiving.interfaces.IDonationService;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/charity")
public class CharityController {

    CharityServiceImpl charityService;
    IDonationService iDonationService;
    ParticipantsService participantsService;

    @Autowired
    public CharityController(CharityServiceImpl charityService, IDonationService iDonationService, ParticipantsService participantsService) {
        this.charityService = charityService;
        this.iDonationService = iDonationService;
        this.participantsService = participantsService;
    }

    @GetMapping()
    public List<Charity> getAllCharities(){
        return charityService.getAllCharities();
    }

    @PostMapping(path = "/filtered")
    public List<Charity> getCharitiesByTitle(@RequestBody String filter) {
        return charityService.getFiteredCharitiesByTitle(filter);
    }

    @GetMapping(value = "/{id}")
    public Charity getCharityById(@PathVariable("id") int id) {
        return charityService.getCharity(id);
    }

    @GetMapping(value = "/{id}/author")
    public String getCharityAuthor(@PathVariable("id") int id) {
        return charityService.getCharityAuthorName(id);
    }

    @PostMapping("/{id}/participants")
    public void addParticipant(@PathVariable("id") int charity_id, @RequestBody int user_id) {
        participantsService.addParticipantInCharity(charity_id, user_id);
    }

    @GetMapping("/{id}/participants")
    public int getCountOfParticipantsInCharity(@PathVariable("id") int id) {
        return participantsService.getCountOfParticipantsInCharity(id);
    }

    @GetMapping("/{id}/donated")
    public int getCharityDonatedAmount(@PathVariable("id") int id) {
        return iDonationService.getDonatedAmount(id);
    }

    @PostMapping("/{id}/donate")
    public void donateAmount(@PathVariable("id") int id, @RequestBody int amount){
        iDonationService.donate(id, amount);
    }

    @PostMapping("/add")
    public void addCharity(@RequestBody Charity charity) throws IOException {
        charityService.addCharity(charity);
    }


    @DeleteMapping("/{id}/delete")
    public void deleteCharity(@PathVariable("id") int charity_id){
        charityService.deleteCharity(charity_id);
    }

}
