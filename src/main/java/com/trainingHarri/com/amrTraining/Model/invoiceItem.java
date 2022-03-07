package com.trainingHarri.com.amrTraining.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "invoice_item")
public class invoiceItem {


        @Id
        @GeneratedValue()
        @Column(name = "id")
        private long id;

        @Column(name="itemId")
        private long itemId;

        @Column(name = "invoiceId")
        private  long invoiceId;

        @org.springframework.data.annotation.Transient
        String name;
        @org.springframework.data.annotation.Transient
        double price;
        @Column(name = "quantity")
        private double quantity;


        @Override
        public String toString() {
                return "invoiceItem{" +
                        "id=" + id +
                        ", itemId=" + itemId +
                        ", invoiceId=" + invoiceId +
                        ", itemName='" + name + '\'' +
                        ", itemPrice=" + price +
                        ", quantity=" + quantity +
                        '}';
        }
}









