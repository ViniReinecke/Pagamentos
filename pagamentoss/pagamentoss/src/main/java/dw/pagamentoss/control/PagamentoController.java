package dw.pagamentoss.control;

import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dw.pagamentoss.repository.*;
import java.util.ArrayList;
import dw.pagamentoss.model.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class PagamentoController {
    
    @Autowired
    PagamentoRepository rep;

    // GET /api/pagamento : listou todos os pagamentos
    @GetMapping("/pagamento")
    public ResponseEntity<List<Pagamento>> getAllPagamento()
    {
        try{
            List<Pagamento> la = new ArrayList<Pagamento>();

            rep.findAll().forEach(la::add);

            if (la.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(la, HttpStatus.OK);

        } catch (Exception e){
            // TODO: handle exception
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

          
    // GET /api/pagamentos/id lista pagamento especifico
    @GetMapping("/pagamento/{cod_jogador}")
    public ResponseEntity<Pagamento> getPagamentoById(@PathVariable("cod_pagamento") int cod_pagamento) {

        try {

            Optional<Pagamento> pagamento = rep.findById(cod_pagamento);

            if (pagamento.isPresent())
                return new ResponseEntity<>(pagamento.get(), HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } catch (Exception e){
            // TODO: handle exception
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // POST /api/pagamento -- cria um novo pagamento
    @PostMapping("/pagamento")
    public ResponseEntity<Pagamento> createPagamento(@RequestBody Pagamento pay) {

        try {
            Pagamento p = rep.save(new Pagamento(pay.getAno(), pay.getMes(), pay.getValor(), pay.getCod_jogador()));

            return new ResponseEntity<>(p, HttpStatus.CREATED);

        } catch (Exception e){
            // TODO: handle exception
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // PUT /api/pagamento/id  edita um pagamento
    @PutMapping("/pagamentos/{cod_pagamento}")
    public ResponseEntity<Pagamento> updatePagamento(@PathVariable("cod_pagamento") int cod_pagamento, @RequestBody Pagamento p)
    {
        Optional<Pagamento> data = rep.findById(cod_pagamento);

        if (data.isPresent())
        {
            Pagamento pay = data.get();
            pay.setAno(p.getAno());
            pay.setMes(p.getMes());
            pay.setValor(p.getValor());
            pay.setCod_jogador(p.getCod_jogador());

            return new ResponseEntity<>(rep.save(pay), HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    






}
