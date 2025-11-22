package com.parking_sys.park.controller.dto;

public class CheckinResponse {
    public Long transactionId;
    public Long spotId;
    public String spotLabel;
    public Integer floor;

    // ADD THIS EMPTY CONSTRUCTOR
    public CheckinResponse() {}

    public CheckinResponse(Long txId, Long sId, String label, Integer floor) {
        this.transactionId = txId;
        this.spotId = sId;
        this.spotLabel = label;
        this.floor = floor;
    }
}
