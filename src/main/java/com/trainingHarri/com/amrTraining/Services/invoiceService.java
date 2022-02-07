package com.trainingHarri.com.amrTraining.Services;

import com.trainingHarri.com.amrTraining.DTOs.invoiceDto;
import com.trainingHarri.com.amrTraining.Model.invoice;
import com.trainingHarri.com.amrTraining.Model.sUser;
import com.trainingHarri.com.amrTraining.Repositries.invoiceRepo;
import com.trainingHarri.com.amrTraining.config.JwtTokenUtil;
import com.trainingHarri.com.amrTraining.roleName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class invoiceService {
@Autowired
com.trainingHarri.com.amrTraining.Repositries.invoiceRepoPa invoiceRepoPa;
    @Autowired
    invoiceRepo invoiceRepo;
    @Autowired
    com.trainingHarri.com.amrTraining.Repositries.userRepo userRepo;
    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    com.trainingHarri.com.amrTraining.Repositries.roleRepo roleRepo;

    public void addInvoice(invoice invoice ,String token) {
        sUser user = getUserId(token);
        invoice.setUser(user);
        invoiceRepo.save(invoice);
    }

    public Page<invoice> findAll(Pageable page) {
      return   invoiceRepoPa.findAll(page);

    }


    public sUser getUserId (String token){
            token = token.substring(7);

               String username = jwtTokenUtil.getUsernameFromToken(token);
sUser user =userRepo.findUserIdByUsername(username);




return user;

    }

    public void deleteInvoice(invoiceDto invoiceDto,String token) {
        sUser user = getUserId(token);
Long roleId = userRepo.findRolebyUserId(user.getUserid());
String role =roleRepo.findbyid(roleId);
Long userId= invoiceRepo.getuserId(invoiceDto.getInvoiceId());

if(role.equals(roleName.ROLE_SUPER.toString())){
    invoiceRepo.deleteById(invoiceDto.getInvoiceId());

}
else {
    if(userId == user.getUserid()){
    invoiceRepo.deleteById(invoiceDto.getInvoiceId());

}else throw  new ResponseStatusException(HttpStatus.UNAUTHORIZED,"YOU ARE NOT ALLOWED TO DO DELETE");}

    }

    public void update(invoiceDto invoiceDto ,String token) {

        sUser user = getUserId(token);
        Long roleId = userRepo.findRolebyUserId(user.getUserid());
        String role =roleRepo.findbyid(roleId);
        Long userId= invoiceRepo.getuserId(invoiceDto.getInvoiceId());
        invoice inv=invoiceRepo.getById(invoiceDto.getInvoiceId());
        if(invoiceDto.getDateOfCreation()!= null)
            inv.setDateOfCreation(invoiceDto.getDateOfCreation());
        if(invoiceDto.getExternalInvoiceId()!= null)
            inv.setExternalInvoiceId(invoiceDto.getExternalInvoiceId());
        if(Integer.valueOf((int) invoiceDto.getAmount())!= null)
            inv.setAmount(invoiceDto.getAmount());
        if(invoiceDto.getAttachmentList()!= null)
            inv.setAttachmentList(invoiceDto.getAttachmentList());


        if(role.equals(roleName.ROLE_SUPER.toString())){
            invoiceRepo.save(inv);

        }else { if(userId == user.getUserid()){
            invoiceRepo.save(inv);

        }else throw  new ResponseStatusException(HttpStatus.UNAUTHORIZED,"YOU ARE NOT ALLOWED TO DO EDIT");}



}}
