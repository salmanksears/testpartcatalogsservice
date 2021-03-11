package com.searshc.hspartcatalog.interceptor;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.searshc.hspartcatalog.services.service.impl.PartsCatalogServiceImpl;

/**
* Title			:   PartsCatalogServiceExceptionMapper
* 
* Description	:	ExceptionMapper for any runtime exception  
*
* @author		:	Abhishek Jain
*/

@Provider
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
public class PartsCatalogServiceExceptionMapper implements ExceptionMapper<RuntimeException> {

		private static final Logger logger = LoggerFactory.getLogger(PartsCatalogServiceImpl.class);
        
        @Override
        public Response toResponse(RuntimeException e) {
        	logger.error("RuntimeException occurred : {}, StackTrace : {}",e, e.getStackTrace());
        	return Response.status(Status.BAD_REQUEST).build();
        }

    }
