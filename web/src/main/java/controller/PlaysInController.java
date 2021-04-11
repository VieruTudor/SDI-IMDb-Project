package controller;

import converter.PlaysInConverter;
import dto.PlaysInDto;
import dto.PlaysInsDto;
import model.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.IPlaysInService;

public class PlaysInController {
    @Autowired
    private IPlaysInService playsInService;
    @Autowired
    private PlaysInConverter converter;

    @RequestMapping(value="/playin")
    public PlaysInsDto getAllPlaysIn(){
        return new PlaysInsDto(converter.convertModelsToDtos(playsInService.getAllPlaysIns()));
    }

    @RequestMapping(value= "/playin", method = RequestMethod.POST)
    PlaysInDto addPlaysIn(@RequestBody PlaysInDto dto){
        var playsIn = converter.convertDtoToModel(dto);
        var result = playsInService.addPlaysIn(playsIn);
        return converter.convertModelToDto(result);
    }

    @RequestMapping(value = "/playin/{id1}&{id2}", method = RequestMethod.PUT)
    PlaysInDto updatePlaysIn(@PathVariable int id1 ,@PathVariable int id2, @RequestBody PlaysInDto dto){
        var id = new Pair<>(id1, id2);
        if(playsInService.getById(id) == null){
            return null;
        }
        dto.setId(new Pair<>(id1, id2));
        var playsIn = converter.convertDtoToModel(dto);
        var result = playsInService.updatePlaysIn(playsIn);
        return converter.convertModelToDto(result);
    }
    @RequestMapping(value = "/movie/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deletePlaysIn(@PathVariable Pair<Integer,Integer> id) {
        playsInService.deletePlaysIn(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
