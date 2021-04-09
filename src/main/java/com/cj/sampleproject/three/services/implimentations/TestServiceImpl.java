package com.cj.sampleproject.three.services.implimentations;

import com.cj.sampleproject.three.dtos.AdvanceServerInfoDto;
import com.cj.sampleproject.three.dtos.CompleteServerInfoDto;
import com.cj.sampleproject.three.dtos.ServerInfoDto;
import com.cj.sampleproject.three.exception.ErrorCode;
import com.cj.sampleproject.three.exception.ServiceException;
import com.cj.sampleproject.three.services.interfaces.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Service
@Slf4j
public class TestServiceImpl implements TestService {

    @Value("${server.port}")
    private int port;

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${remote.info.url1}")
    private String remoteUrl1;

    @Value("${remote.info.url2}")
    private String remoteUrl2;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ServerInfoDto getServerInfo() throws ServiceException {
        log.debug("Inside getServerInfo method");
        try {
            String ip = InetAddress.getLocalHost().getHostAddress();
            String hostName = InetAddress.getLocalHost().getHostName();
            log.debug("Host name : {}  , ip: {}  , port: {}", hostName, ip, port);
            return new ServerInfoDto(hostName, ip, String.valueOf(port), applicationName);
        } catch (UnknownHostException e) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.SVR001, "Unable to retrieve local " +
                    "server data", "Unable to retrieve local server data");
        }
    }

    @Override
    public AdvanceServerInfoDto getAdvanceServerInfo() throws ServiceException {
        log.debug("Inside getServerInfo method");
        AdvanceServerInfoDto result = new AdvanceServerInfoDto();
        try {
            String ip = InetAddress.getLocalHost().getHostAddress();
            String hostName = InetAddress.getLocalHost().getHostName();
            log.debug("Host name : {}  , ip: {}  , port: {}", hostName, ip, port);
            result.setLocalServerInfo(new ServerInfoDto(hostName, ip, String.valueOf(port), applicationName));
        } catch (UnknownHostException e) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.SVR001, "Unable to retrieve local " +
                    "server data", "Unable to retrieve local server data");
        }

        try {
            ResponseEntity<ServerInfoDto> remoteResult = restTemplate.exchange(remoteUrl1, HttpMethod.GET, null,
                    ServerInfoDto.class);
            log.debug("Result from remote server: {}", remoteResult.getBody());
            result.setRemoteServerInfo(remoteResult.getBody());
        } catch (RestClientException e) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.HTTP001, "Unable to retrieve " +
                    "remote server data", "Unable to retrieve remote server data");
        }

        return result;
    }

    @Override
    public CompleteServerInfoDto getCompleteServerInfo() throws ServiceException {
        CompleteServerInfoDto result = new CompleteServerInfoDto();

        try {
            String ip = InetAddress.getLocalHost().getHostAddress();
            String hostName = InetAddress.getLocalHost().getHostName();
            log.debug("Host name : {}  , ip: {}  , port: {}", hostName, ip, port);
            result.setLocalServerInfo(new ServerInfoDto(hostName, ip, String.valueOf(port), applicationName));
        } catch (UnknownHostException e) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.SVR001, "Unable to retrieve local " +
                    "server data", "Unable to retrieve local server data");
        }

        try {
            ResponseEntity<ServerInfoDto> remoteResult = restTemplate.exchange(remoteUrl1, HttpMethod.GET, null,
                    ServerInfoDto.class);
            log.debug("Result from remote server: {}", remoteResult.getBody());
            result.setBackendServerInfo(remoteResult.getBody());
        } catch (RestClientException e) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.HTTP001, "Unable to retrieve " +
                    "backend server data", "Unable to retrieve backend server data");
        }

        try {
            ResponseEntity<AdvanceServerInfoDto> remoteResult = restTemplate.exchange(remoteUrl2, HttpMethod.GET, null,
                    AdvanceServerInfoDto.class);
            log.debug("Result from remote server: {}", remoteResult.getBody());
            result.setRemoteServerInfo(remoteResult.getBody().getLocalServerInfo());
        } catch (RestClientException e) {
            throw new ServiceException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.HTTP001, "Unable to retrieve " +
                    "remote server data", "Unable to retrieve remote server data");
        }

        return result;
    }
}
