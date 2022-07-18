package com.in28minutes.rest.webservices.restfulwebservices.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"code", "reason", "message", "status", "referenceError", "type", "schemaLocation"})
public class ErrorDetails {

    @JsonProperty("code")
    private String code;

    @JsonProperty("reason")
    private String reason;

    @JsonProperty("message")
    private String message;

    @JsonProperty("status")
    private String status;

    @JsonProperty("referenceError")
    private String referenceError;

    @JsonProperty("@type")
    private String type;

    @JsonProperty("@schemaLocation")
    private String schemaLocation;


}
