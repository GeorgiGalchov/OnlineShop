package com.example.online_store.model.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "user_activation_codes")
public class UserActivationCodeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String activationCode;

    private Instant created;

    @ManyToOne
    private UserEntity user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getActivationCode() {
        return activationCode;
    }

    public UserActivationCodeEntity setActivationCode(String activationCode) {
        this.activationCode = activationCode;
        return this;
    }

    public Instant getCreated() {
        return created;
    }

    public UserActivationCodeEntity setCreated(Instant created) {
        this.created = created;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public UserActivationCodeEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }
}
