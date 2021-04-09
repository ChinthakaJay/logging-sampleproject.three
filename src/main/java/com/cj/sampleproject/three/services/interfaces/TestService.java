package com.cj.sampleproject.three.services.interfaces;

import com.cj.sampleproject.three.dtos.AdvanceServerInfoDto;
import com.cj.sampleproject.three.dtos.CompleteServerInfoDto;
import com.cj.sampleproject.three.dtos.ServerInfoDto;
import com.cj.sampleproject.three.exception.ServiceException;

public interface TestService {
    ServerInfoDto getServerInfo() throws ServiceException;

    AdvanceServerInfoDto getAdvanceServerInfo() throws ServiceException;

    CompleteServerInfoDto getCompleteServerInfo() throws ServiceException;
}
