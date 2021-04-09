package com.cj.sampleproject.three.dtos;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
public class CompleteServerInfoDto {
    private ServerInfoDto localServerInfo;
    private ServerInfoDto remoteServerInfo;
    private ServerInfoDto backendServerInfo;
}
