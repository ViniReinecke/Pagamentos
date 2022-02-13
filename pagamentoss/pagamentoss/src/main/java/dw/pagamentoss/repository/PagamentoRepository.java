package dw.pagamentoss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import dw.pagamentoss.model.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {
    
}
