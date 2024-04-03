package com.communityHubSystem.communityHub.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "share")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Share implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String caption;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "resource_id")
    private Resource resource;
}
