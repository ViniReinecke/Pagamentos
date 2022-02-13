package dw.pagamentoss.control;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;



import dw.pagamentoss.repository.JogadorRepository;
import java.util.ArrayList;
import dw.pagamentoss.model.Jogador;
import java.util.List;
import java.util.Optional;



@RestController
@RequestMapping("/api")
public class JogadorController{
    
    @Autowired
    JogadorRepository rep;

    // GET /api/Jogadores : listou todos os jogadores
    @GetMapping("/jogador")
    public ResponseEntity<List<Jogador>> getAllJogador(@RequestParam(required = false)String nome)
    {
        try{
            List<Jogador> la = new ArrayList<Jogador>();

            if (nome == null)
                rep.findAll().forEach(la::add);
            else
                rep.findByNomeContaining(nome).forEach(la::add);

            if (la.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            
            return new ResponseEntity<>(la, HttpStatus.OK);

        }
        catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
    }

    //GET /api/jogadores : lista jogador 

    @GetMapping("/jogador/{cod_jogador}")
    public ResponseEntity<Jogador> getJogadorById(@PathVariable("cod_jogador") int cod_jogador)
    {   
        try {

            Optional<Jogador> jogador = rep.findById(cod_jogador);

            if (jogador.isPresent())
                return new ResponseEntity<>(jogador.get(), HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e){
            // TODO: handle exception
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Metodo POST /api/jogador   para criar jogador.
    @PostMapping("/jogador")
    public ResponseEntity<Jogador> createJogador(@RequestBody Jogador jog) {

        try {
            System.out.println(jog);
            Jogador _j = rep.save( new Jogador(jog.getCod_jogador(), jog.getNome(), jog.getEmail(), jog.getDatanasc()));

            return new ResponseEntity<>(jog, HttpStatus.CREATED);

        } catch (Exception e){
            // TODO: handle exception
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Metodo PUT /api/jogador/id -- edita um jogador
    @PutMapping("/jogador/{cod_jogador}")
    public ResponseEntity<Jogador> updateJogador(@PathVariable("cod_jogador") int cod_jogador, @RequestBody Jogador j)
    {
        Optional<Jogador> data = rep.findById(cod_jogador);

        if (data.isPresent())
        {
            Jogador player = data.get();
            player.setNome(j.getNome().isEmpty() ? player.getNome() : j.getNome());
            player.setEmail(j.getEmail().isEmpty() ? player.getEmail() : j.getEmail());
            player.setDatanasc(j.getDatanasc().toString().isEmpty() ? player.getDatanasc() : j.getDatanasc());

            return new ResponseEntity<>(rep.save(player), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


     // Método DELETE /api/jogadores -- deletr um jogador
        @DeleteMapping("/jogador/{cod_jogador}")
        public ResponseEntity<HttpStatus> deleteJogador(@PathVariable("cod_jogador") int cod_jogador) {
            try {
                rep.deleteById(cod_jogador);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    
            } catch (Exception e) {
                //TODO: handle exception
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }






}