package kg.ebooks.eBook.db.service;

import kg.ebooks.eBook.db.domain.model.users.Vendor;

import java.util.List;

public interface VendorService {

    List<Vendor> findAll();
    Vendor saveVendor(Vendor vendor);
}
