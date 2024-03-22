package com.example.demo.session;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data

public class SessionEntity {

    @Id
    private String sessionId;

    private String value;
    protected SessionEntity() {
    }

    public SessionEntity(String sessionId,String value) {
        this.sessionId = sessionId;
        this.value = value;
    }
}
