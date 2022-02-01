package com.trainingHarri.com.amrTraining.Services;
import com.trainingHarri.com.amrTraining.DTOs.invoiceDto;
import com.trainingHarri.com.amrTraining.Model.Role;
import com.trainingHarri.com.amrTraining.Model.User;
import com.trainingHarri.com.amrTraining.Model.invoice;
import com.trainingHarri.com.amrTraining.Repositries.invoiceRepo;
import com.trainingHarri.com.amrTraining.exceptions.customExeption;
import com.trainingHarri.com.amrTraining.roleName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class invoiceService {

    @Autowired
    invoiceRepo invoiceRepo;
    @Autowired
    com.trainingHarri.com.amrTraining.Repositries.userRepo userRepo;
    public void addInvoice(invoice invoice) {
        invoiceRepo.save(invoice);
    }

    public void deleteInvoice(invoiceDto invoiceDto) {
        User user =userRepo.findByEmail(invoiceDto.getUser().getEmail());
        List<Role> roles =user.getRoles();
        List<String> rolenames = null;
        for(int i=0;i<roles.size();i++)
            rolenames.add(roles.get(i).getRoleName().toString());
        if(rolenames.contains(roleName.SUPER.toString() )|| rolenames.contains(roleName.SUPPORT.toString())){
            if(rolenames.get(0).equals(roleName.SUPER.toString())){
                invoiceRepo.deleteById(invoiceDto.getInvoiceId());
            }else {if(invoiceDto.getUser().getUserid()==invoiceDto.getUserId()){
                invoiceRepo.deleteById(invoiceDto.getInvoiceId());

            }else throw new customExeption("YOU ARE NOY ALLOWED TO DO DELETE");}



        }else throw new customExeption("YOU ARE NOY ALLOWED TO DO DELETE ");


    }

    public void update(invoiceDto invoiceDto) {
        User user =userRepo.findByEmail(invoiceDto.getUser().getEmail());
        List<Role> roles =user.getRoles();
        List<String> rolenames = null;
        for(int i=0;i<roles.size();i++)
            rolenames.add(roles.get(i).getRoleName().toString());
        if(rolenames.contains(roleName.SUPER.toString() )|| rolenames.contains(roleName.SUPPORT.toString())){
            invoice inv=invoiceRepo.getById(invoiceDto.getInvoiceId());
            if(invoiceDto.getDateOfCreation()!= null)
            inv.setDateOfCreation(invoiceDto.getDateOfCreation());
            if(invoiceDto.getExternalInvoiceId()!= null)
                inv.setExternalInvoiceId(invoiceDto.getExternalInvoiceId());
            if(Integer.valueOf((int) invoiceDto.getAmount())!= null)
inv.setAmount(invoiceDto.getAmount());
            if(invoiceDto.getAttachmentList()!= null)
                inv.setAttachmentList(invoiceDto.getAttachmentList());

            if(invoiceDto.getItems()!= null)
                inv.setItems(invoiceDto.getItems());


                if(rolenames.get(0).equals(roleName.SUPER.toString())){
                    invoiceRepo.save(inv);

                }else {if(invoiceDto.getUser().getUserid()==invoiceDto.getUserId()){
invoiceRepo.save(inv);
            }else throw new customExeption("YOU ARE NOY ALLOWED TO DO DELETE");}



        }else throw new customExeption("YOU ARE NOY ALLOWED TO DO DELETE ");


    }
}
