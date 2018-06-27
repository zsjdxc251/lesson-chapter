package com.chapter.distributed.rmi.custom.dubbo;

public interface ExchangeHandler {

    /**
     * reply.
     *

     * @param request
     * @return response

     */
    Object reply(Object request) throws Exception;

}
