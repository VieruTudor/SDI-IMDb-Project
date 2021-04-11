package controller;

import converter.ActorConverter;
import dto.ActorDto;
import dto.ActorsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.IActorService;

@RestController
public class ActorController {
    @Autowired
    private IActorService actorService;
    @Autowired
    private ActorConverter converter;

    @RequestMapping(value="/actors")
    public ActorsDto getAllActors(){
        System.out.println("muie poli");
        actorService.getAllActors().forEach(System.out::println);
        return new ActorsDto(converter.convertModelsToDtos(actorService.getAllActors()));
    }

    @RequestMapping(value= "/actors", method = RequestMethod.POST)
    ActorDto addActor(@RequestBody ActorDto dto){
        var actor = converter.convertDtoToModel(dto);
        var result = actorService.addActor(actor);
        return converter.convertModelToDto(result);
    }

    @RequestMapping(value = "/actors/{id}", method = RequestMethod.PUT)
    ActorDto updateActor(@PathVariable int id, @RequestBody ActorDto dto){
        if(actorService.getById(id) == null){
            return null;
        }
        var actor = converter.convertDtoToModel(dto);
        var result = actorService.updateActor(actor);
        return converter.convertModelToDto(result);
    }
    @RequestMapping(value = "/actor/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteActor(@PathVariable int id) {
        actorService.deleteActor(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
