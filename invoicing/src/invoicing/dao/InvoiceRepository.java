package invoicing.dao;

import invoicing.dao.infrastructure.Repository;
import invoicing.model.Invoice;

public interface InvoiceRepository extends Repository<Long, Invoice>{
}
