package org.sid.ebankservice.web;

import org.sid.ebankservice.dto.CurrencyTransferResponse;
import org.sid.ebankservice.dto.NewWalletTransferRequest;
import org.sid.ebankservice.entities.CurrencyDeposit;
import org.sid.ebankservice.service.EBankServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EBankRestController {
    @Autowired
    private EBankServiceImpl eBankService;
    @PostMapping("/currencyTransfer")
    // Only users with ADMIN role can access this endpoint
    @PreAuthorize("hasAuthority('ADMIN')")
    public CurrencyTransferResponse currencyTransfer(@RequestBody NewWalletTransferRequest request){
        return this.eBankService.newWalletTransaction(request);
    }

    @GetMapping("/currencyDeposits")
    // Only users with USER role can access this endpoint
    @PreAuthorize("hasAuthority('USER')")
    public List<CurrencyDeposit> currencyDepositList(){
        return eBankService.currencyDeposits();
    }
}
