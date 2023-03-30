package intern.project.parkingmanagerment.service.impl;

import intern.project.parkingmanagerment.model.Invoice;
import intern.project.parkingmanagerment.repositories.InvoiceRepository;
import intern.project.parkingmanagerment.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    InvoiceRepository invoiceRepository;

    @Override
    public Invoice saveInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }
}
