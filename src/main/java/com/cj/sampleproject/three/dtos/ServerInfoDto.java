package com.cj.sampleproject.three.dtos;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
public class ServerInfoDto {
    private String hostName;
    private String ip;
    private String port;
    private String applicationName;
}
