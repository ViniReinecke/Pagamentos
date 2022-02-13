package dw.pagamentoss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import dw.pagamentoss.model.Jogador;
import java.util.List;

public interface JogadorRepository extends JpaRepository<Jogador, Integer>{
    

    List<Jogador> findByNomeContaining(String nome);

}
