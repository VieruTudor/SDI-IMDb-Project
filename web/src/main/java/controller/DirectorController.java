package controller;

import converter.DirectorConverter;
import dto.DirectorDto;
import dto.DirectorsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.DirectorService;
import service.IDirectorService;

@RestController
public class DirectorController {
    @Autowired
    private IDirectorService directorService;
    @Autowired
    private DirectorConverter converter;

    @RequestMapping(value="/directors")
    public DirectorsDto getAllDirectors(){
        return new DirectorsDto(converter.convertModelsToDtos(directorService.getAllDirectors()));
    }

    @RequestMapping(value= "/directors", method = RequestMethod.POST)
    DirectorDto addDirector(@RequestBody DirectorDto dto){
        var director = converter.convertDtoToModel(dto);
        var result = directorService.addDirector(director);
        return converter.convertModelToDto(result);
    }

    @RequestMapping(value = "/directors/{id}", method = RequestMethod.PUT)
    DirectorDto updateDirector(@PathVariable int id, @RequestBody DirectorDto dto){

        if(directorService.getById(id) == null){
            return null;
        }

        var director = converter.convertDtoToModel(dto);
        var result = directorService.updateDirector(director);
        return converter.convertModelToDto(result);
    }

    @RequestMapping(value = "/directors/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteDirector(@PathVariable int id) {
        directorService.deleteDirector(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
