package com.billmate.mybillmate.services;

import com.billmate.mybillmate.models.Bill;
import com.billmate.mybillmate.repositories.BillRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {

    private final BillRepository billRepository;

    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public List<Bill> getAllBills () {
        return billRepository.findAll();
    }

}
