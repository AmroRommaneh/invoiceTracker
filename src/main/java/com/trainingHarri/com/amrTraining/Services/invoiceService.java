package com.trainingHarri.com.amrTraining.Services;

import com.trainingHarri.com.amrTraining.DTOs.invoiceDto;
import com.trainingHarri.com.amrTraining.Model.invoice;
import com.trainingHarri.com.amrTraining.Model.invoiceItem;
import com.trainingHarri.com.amrTraining.Model.sUser;
import com.trainingHarri.com.amrTraining.Repositries.invoiceRepo;
import com.trainingHarri.com.amrTraining.Status;
import com.trainingHarri.com.amrTraining.config.JwtTokenUtil;
import com.trainingHarri.com.amrTraining.exceptions.customExeption;
import com.trainingHarri.com.amrTraining.roleName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

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
    com.trainingHarri.com.amrTraining.Repositries.invoiceItemRepo invoiceItemRepo;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    com.trainingHarri.com.amrTraining.Repositries.roleRepo roleRepo;
    @Autowired
    invoiceItemService invoiceItemService;

    public void addInvoice(invoice invoice, String token) {
        sUser user = getUserId(token);
        invoice.setUser(user);
        invoice.setStatus(Status.EXIST);

        invoice x = invoiceRepo.findByexid(invoice.getExternalInvoiceId());
        if (x != null)
            throw new customExeption("THIS EXTERNAL INVOICE ID HAS BEEN USED BEFORE");
        else
        invoiceRepo.save(invoice);
    }

    public Page<invoice> findAll(Pageable page ,String token) {
        sUser user = getUserId(token);
        Long roleId = userRepo.findRolebyUserId(user.getUserid());
        String role = roleRepo.findbyid(roleId);
        if (role.equals(roleName.ROLE_SUPER.toString()) || role.equals(roleName.ROLE_AUDITOR.toString())) {
            Page<invoice> x = invoiceRepoPa.findAll(page);
            for (int i = 0; i < x.getNumberOfElements(); i++) {
                x.getContent().get(i).setUser(null);
            }
            return x;

        } else {
            Page<invoice> x = invoiceRepoPa.findAll(page);
            Page<invoice> y;
            List<invoice> mod = x.toList();
            List<invoice> mod2 = new ArrayList<>();

            System.out.println(mod);
            System.out.println("in esle satment");

            for (int i = 0; i < x.getNumberOfElements(); i++) {
                System.out.println("in for satment");

                if (!(x.getContent().get(i).getUser().getUserid() == user.getUserid())) {

                    System.out.println("in if satment");

                    //mod.remove(i);
                    System.out.println("after if satment");


                } else {
                    mod2.add(mod.get(i));

                }



            }

            Page<invoice> pages = new PageImpl<invoice>(mod2, page, mod2.size());

            System.out.println(x);

            return pages;
        }
    }

    public sUser getUserId(String token) {
        token = token.substring(7);

        String username = jwtTokenUtil.getUsernameFromToken(token);
        sUser user = userRepo.findUserIdByUsername(username);


        return user;

    }

    public String deleteInvoice(invoice invoiceDto, String token) {


        sUser user = getUserId(token);
        Long roleId = userRepo.findRolebyUserId(user.getUserid());
        String role = roleRepo.findbyid(roleId);
        Date date = new Date();

        if(invoiceDto ==null){
            return "NO INVOICE EXIST WITH THIS EXTERNAL INVOICE ID";
        }else {
            invoice x= invoiceRepo.findByexid(invoiceDto.getExternalInvoiceId());

                    Long userId = invoiceRepo.getuserId(invoiceDto.getInvoiceId());
        if (role.equals(roleName.ROLE_SUPER.toString())) {
//            invoiceRepo.deleteById(invoiceDto.getInvoiceId());
           invoiceItemRepo.deleteAll(invoiceDto.getInvoiceId());
            x.setStatus(Status.DELETED);
            x.setDateOfDeletion(date);
            invoiceRepo.save(x);
            return  "INVOICE HAS BEEN DELETED";

        } else {
            if (userId == user.getUserid()) {
//                invoiceRepo.deleteById(invoiceDto.getInvoiceId());
             invoiceItemRepo.deleteAll(invoiceDto.getInvoiceId());
                x.setStatus(Status.DELETED);
                x.setDateOfDeletion(date);
                invoiceRepo.save(x);

                return  "INVOICE HAS BEEN DELETED";
            } else
                return  "YOU ARE NOT ALLOWED TO DO DELETE";
        }
    }}

    public String update(invoiceDto invoiceDto, String token) {

        sUser user = getUserId(token);
        Long roleId = userRepo.findRolebyUserId(user.getUserid());
        String role = roleRepo.findbyid(roleId);
        Long userId = invoiceRepo.getuserId(invoiceDto.getInvoiceId());
        invoice inv = invoiceRepo.findByexid(invoiceDto.getExternalInvoiceId());
        if(inv == null){
return "NO INVOICE FOUND WITH THIS EXTERNAL INVOICE ID";
        }else {
            if (invoiceDto.getDateOfCreation() != null) {
                System.out.println(invoiceDto.getDateOfCreation() + "daaaaaateeeeeeeee");
                System.out.println(inv.getDateOfCreation() + "asjodjaojdoasjdoasdjoasdja");
                inv.setDateOfCreation(invoiceDto.getDateOfCreation());
            }
            if (invoiceDto.getExternalInvoiceId() != null)
                inv.setExternalInvoiceId(invoiceDto.getExternalInvoiceId());
            if (invoiceDto.getAmount() != 0)
                inv.setAmount(invoiceDto.getAmount());
            if (invoiceDto.getAttachmentList() != null)
                inv.setAttachmentList(invoiceDto.getAttachmentList());

            if (role.equals(roleName.ROLE_SUPER.toString())) {
                invoiceRepo.save(inv);
                saveItems(invoiceDto.getItems(), inv.getInvoiceId());


            } else {
                if (userId == user.getUserid()) {
                    invoiceRepo.save(inv);
                    saveItems(invoiceDto.getItems(), inv.getInvoiceId());
                    return "INVOICE HAS BEEN UPDATED";


                } else
                    return "YOU ARE NOT ALLOWED TO MAKE UPDATE";
            }
        }
        return null;
    }

    public Map<String, Object> getInvoice(Long externalInvoiceId) {
        invoice x =invoiceRepo.findByexid(externalInvoiceId);
        Map<String, Object> y = new HashMap<String, Object>();
        if (x==null){
            y=null;
            return y;
        }else {
            y.put("invoiceId", x.getInvoiceId());
            y.put("externalInvoiceId", x.getExternalInvoiceId());
            y.put("amount", x.getAmount());
            y.put("dateOfCreation", x.getDateOfCreation());
            y.put("dateOfDeletion",x.getDateOfDeletion());
            y.put("attachmentList",x.getAttachmentList());
            y.put("status",x.getStatus());
        }

        return y;
    }

    public List<invoiceItem> findd(Long invoiceId) {
        return invoiceItemRepo.findd(invoiceId);

    }

    public void saveItems(List<Long> items,long invoiceId){
System.out.println(items);
        invoiceItemRepo.deleteAll(invoiceId);

        for (int i =0;i<items.size();i++){

            invoiceItemService.saveInvoiceItem(invoiceId,items.get(i));
        }

    }

    public void checkItems(List<Long> items) {
        for (int i =0;i<items.size();i++){

            invoiceItemService.checkItem(items.get(i));
        }
    }
}
