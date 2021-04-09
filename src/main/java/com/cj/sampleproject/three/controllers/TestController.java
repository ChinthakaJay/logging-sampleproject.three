package com.cj.sampleproject.three.controllers;

import com.cj.sampleproject.three.dtos.AdvanceServerInfoDto;
import com.cj.sampleproject.three.dtos.CompleteServerInfoDto;
import com.cj.sampleproject.three.dtos.ServerInfoDto;
import com.cj.sampleproject.three.exception.ServiceException;
import com.cj.sampleproject.three.services.interfaces.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping(value = "/api/v1/info")
    public ResponseEntity<ServerInfoDto> test(HttpServletRequest request) throws ServiceException {
        log.info("Get server info api called");
        return new ResponseEntity<>(testService.getServerInfo(), HttpStatus.OK);
    }

    @GetMapping(value = "/api/v1/advance-info")
    public ResponseEntity<AdvanceServerInfoDto> test2(HttpServletRequest request) throws ServiceException {
        log.info("get advance server info api called");
        return new ResponseEntity<>(testService.getAdvanceServerInfo(), HttpStatus.OK);
    }

    @GetMapping(value = "/api/v1/complete-info")
    public ResponseEntity<CompleteServerInfoDto> test3(HttpServletRequest request) throws ServiceException {
        log.info("get complete server info api called");
        return new ResponseEntity<>(testService.getCompleteServerInfo(), HttpStatus.OK);
    }
}
