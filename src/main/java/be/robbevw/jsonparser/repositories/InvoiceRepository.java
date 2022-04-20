package be.robbevw.jsonparser.repositories;

import be.robbevw.jsonparser.models.Invoice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, Long> {
}
