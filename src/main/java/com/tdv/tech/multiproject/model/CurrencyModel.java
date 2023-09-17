package com.tdv.tech.multiproject.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Data
@ToString
public class CurrencyModel {
    String cur_ID;

    @JsonProperty("disclaimer")
    String disclaimer;

    @JsonProperty("date")
    String date;

    @JsonProperty("timestamp")
    String timestamp;

    @JsonProperty("base")
    String base;

    @JsonProperty("rates")
    Rates rates;
}
