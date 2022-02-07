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

    }

