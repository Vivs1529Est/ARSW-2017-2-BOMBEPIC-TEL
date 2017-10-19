/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.bombepic.controllers;



import edu.eci.arsw.bombepic.model.Jugador;
import org.springframework.web.bind.annotation.PathVariable;
import edu.eci.arsw.bombepic.services.BombServices;
import edu.eci.arsw.bombepic.services.ServicesException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author tiffany
 */

@RestController
@RequestMapping(value = "/salas")
public class BombRESTController {
    
     @Autowired
        BombServices services;
     
     @RequestMapping(path = "/{salanum}/jugadores",method = RequestMethod.PUT)
     public ResponseEntity<?> agregarJugador(@PathVariable(name="salanum")String salanum,@RequestBody Jugador p){
         synchronized (services){
             try {
                 if(services.getJugadores(Integer.parseInt(salanum)).size()< 4 ){
                     services.registroJugador(Integer.parseInt(salanum), p);
                 }
             } catch (Exception ex) {
                   Logger.getLogger(BombRESTController.class.getName()).log(Level.SEVERE, null, ex);
                   return new ResponseEntity<>(ex.getLocalizedMessage(),HttpStatus.BAD_REQUEST);
                 
             }
             
             return new ResponseEntity<>(HttpStatus.CREATED);
                   
         
         }
     }
     
    @RequestMapping(path = "/{salanum}/jugadores",method = RequestMethod.GET)
    public ResponseEntity<?> getJugador(@PathVariable(name = "salanum") String salanum) {
        
        try {
            return new ResponseEntity<>(services.getJugadores(Integer.parseInt(salanum)),HttpStatus.ACCEPTED);
        } catch (ServicesException ex) {
            Logger.getLogger(BombRESTController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getLocalizedMessage(),HttpStatus.NOT_FOUND);
        } catch (NumberFormatException ex){
            Logger.getLogger(BombRESTController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("/{salanum}/ must be an integer value.",HttpStatus.BAD_REQUEST);
        }
}
       
        @RequestMapping(path = "/{salanum}/tablero",method = RequestMethod.GET)
    public ResponseEntity<?> getTablero(@PathVariable(name = "salanum") String salanum) {
        
        try {
            return new ResponseEntity<>(services.getTablero(),HttpStatus.ACCEPTED);
        } catch (ServicesException ex) {
            Logger.getLogger(BombRESTController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getLocalizedMessage(),HttpStatus.NOT_FOUND);
        } catch (NumberFormatException ex){
            Logger.getLogger(BombRESTController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("/{salanum}/ must be an integer value.",HttpStatus.BAD_REQUEST);
        }
}
       
       
     
     
     
     
     
     
             
     
    
    
}
