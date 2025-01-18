package com.example.CanvasTLV_Hotel.modules;

import java.time.LocalDateTime;

public class ServerState {
    private String ClientService;
    private LocalDateTime lastUpdated;

    public ServerState(String clientService, LocalDateTime lastUpdated) {
        ClientService = clientService;
        this.lastUpdated = lastUpdated;


    }

    public String getClientService() {
        return ClientService;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setClientService(String clientService) {
        ClientService = clientService;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
