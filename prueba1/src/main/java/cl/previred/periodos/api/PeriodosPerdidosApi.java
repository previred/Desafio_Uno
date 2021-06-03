package cl.previred.periodos.api;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.ObjectMapper;

import cl.previred.periodos.dto.PeriodosPerdidosResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "api", description = "the api API")
public interface PeriodosPerdidosApi {

	
	Logger log = LoggerFactory.getLogger(PeriodosPerdidosApi.class);

    default Optional<ObjectMapper> getObjectMapper() {
        return Optional.empty();
    }

    default Optional<HttpServletRequest> getRequest() {
        return Optional.empty();
    }
    
    default Optional<String> getAcceptHeader() {
        return getRequest().map(r -> r.getHeader("Accept"));
    }
	
	@ApiOperation(value = "Listas de periodos a procesar", nickname = "periodos", notes = "", response = PeriodosPerdidosResponseDTO.class, tags={ "periodosPerdidos", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "Periodo y listas de fechas", response = PeriodosPerdidosResponseDTO.class) })
    @RequestMapping(value = "/fechasAleatorias",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
	default ResponseEntity<PeriodosPerdidosResponseDTO> getFechasAleatorias(){
		try {
            return new ResponseEntity<>(getObjectMapper().get().readValue("{\r\n" + 
            		"   \"id\":1,\r\n" + 
            		"   \"fechaCreacion\":\"1997-02-01\",\r\n" + 
            		"   \"fechaFin\":\"2013-03-01\",\r\n" + 
            		"   \"fechas\":[\r\n" + 
            		"      \"1997-04-01\",\r\n" + 
            		"      \"1997-06-01\",\r\n" + 
            		"      \"1997-07-01\",\r\n" + 
            		"      \"1997-08-01\",\r\n" + 
            		"      \"1997-09-01\",\r\n" + 
            		"      \"1997-10-01\",\r\n" + 
            		"      \"1997-11-01\",\r\n" + 
            		"      \"1997-12-01\",\r\n" + 
            		"      \"1998-01-01\",\r\n" + 
            		"      \"1998-02-01\",\r\n" + 
            		"      \"1998-03-01\",\r\n" + 
            		"      \"1998-04-01\",\r\n" + 
            		"      \"1998-05-01\",\r\n" + 
            		"      \"1998-06-01\",\r\n" + 
            		"      \"1998-08-01\",\r\n" + 
            		"      \"1998-12-01\",\r\n" + 
            		"      \"1999-01-01\",\r\n" + 
            		"      \"1999-03-01\",\r\n" + 
            		"      \"1999-06-01\",\r\n" + 
            		"      \"1999-08-01\",\r\n" + 
            		"      \"1999-10-01\",\r\n" + 
            		"      \"1999-11-01\",\r\n" + 
            		"      \"1999-12-01\",\r\n" + 
            		"      \"2000-03-01\",\r\n" + 
            		"      \"2000-06-01\",\r\n" + 
            		"      \"2000-12-01\",\r\n" + 
            		"      \"2001-01-01\",\r\n" + 
            		"      \"2001-03-01\",\r\n" + 
            		"      \"2001-04-01\",\r\n" + 
            		"      \"2001-05-01\",\r\n" + 
            		"      \"2001-08-01\",\r\n" + 
            		"      \"2001-10-01\",\r\n" + 
            		"      \"2001-11-01\",\r\n" + 
            		"      \"2001-12-01\",\r\n" + 
            		"      \"2002-01-01\",\r\n" + 
            		"      \"2002-02-01\",\r\n" + 
            		"      \"2002-03-01\",\r\n" + 
            		"      \"2002-04-01\",\r\n" + 
            		"      \"2002-06-01\",\r\n" + 
            		"      \"2002-08-01\",\r\n" + 
            		"      \"2002-12-01\",\r\n" + 
            		"      \"2003-04-01\",\r\n" + 
            		"      \"2003-05-01\",\r\n" + 
            		"      \"2003-08-01\",\r\n" + 
            		"      \"2003-09-01\",\r\n" + 
            		"      \"2003-11-01\",\r\n" + 
            		"      \"2003-12-01\",\r\n" + 
            		"      \"2004-02-01\",\r\n" + 
            		"      \"2004-05-01\",\r\n" + 
            		"      \"2004-06-01\",\r\n" + 
            		"      \"2004-07-01\",\r\n" + 
            		"      \"2004-10-01\",\r\n" + 
            		"      \"2005-04-01\",\r\n" + 
            		"      \"2005-05-01\",\r\n" + 
            		"      \"2005-07-01\",\r\n" + 
            		"      \"2005-08-01\",\r\n" + 
            		"      \"2005-09-01\",\r\n" + 
            		"      \"2005-10-01\",\r\n" + 
            		"      \"2006-02-01\",\r\n" + 
            		"      \"2006-04-01\",\r\n" + 
            		"      \"2006-06-01\",\r\n" + 
            		"      \"2006-07-01\",\r\n" + 
            		"      \"2006-08-01\",\r\n" + 
            		"      \"2006-09-01\",\r\n" + 
            		"      \"2006-12-01\",\r\n" + 
            		"      \"2007-01-01\",\r\n" + 
            		"      \"2007-02-01\",\r\n" + 
            		"      \"2007-03-01\",\r\n" + 
            		"      \"2007-05-01\",\r\n" + 
            		"      \"2007-06-01\",\r\n" + 
            		"      \"2007-08-01\",\r\n" + 
            		"      \"2007-10-01\",\r\n" + 
            		"      \"2007-12-01\",\r\n" + 
            		"      \"2008-03-01\",\r\n" + 
            		"      \"2008-07-01\",\r\n" + 
            		"      \"2008-08-01\",\r\n" + 
            		"      \"2008-09-01\",\r\n" + 
            		"      \"2008-12-01\",\r\n" + 
            		"      \"2009-01-01\",\r\n" + 
            		"      \"2009-04-01\",\r\n" + 
            		"      \"2009-05-01\",\r\n" + 
            		"      \"2009-07-01\",\r\n" + 
            		"      \"2009-09-01\",\r\n" + 
            		"      \"2009-11-01\",\r\n" + 
            		"      \"2010-01-01\",\r\n" + 
            		"      \"2010-05-01\",\r\n" + 
            		"      \"2010-06-01\",\r\n" + 
            		"      \"2010-09-01\",\r\n" + 
            		"      \"2010-11-01\",\r\n" + 
            		"      \"2010-12-01\",\r\n" + 
            		"      \"2011-03-01\",\r\n" + 
            		"      \"2011-06-01\",\r\n" + 
            		"      \"2011-07-01\",\r\n" + 
            		"      \"2011-08-01\",\r\n" + 
            		"      \"2011-10-01\",\r\n" + 
            		"      \"2011-12-01\",\r\n" + 
            		"      \"2012-02-01\",\r\n" + 
            		"      \"2012-04-01\",\r\n" + 
            		"      \"2012-06-01\",\r\n" + 
            		"      \"2012-07-01\",\r\n" + 
            		"      \"2012-10-01\"\r\n" + 
            		"   ],\r\n" + 
            		"   \"fechasFaltantes\":[\r\n" + 
            		"      \"1997-02-01\",\r\n" + 
            		"      \"1997-03-01\",\r\n" + 
            		"      \"1997-05-01\",\r\n" + 
            		"      \"1998-07-01\",\r\n" + 
            		"      \"1998-09-01\",\r\n" + 
            		"      \"1998-10-01\",\r\n" + 
            		"      \"1998-11-01\",\r\n" + 
            		"      \"1999-02-01\",\r\n" + 
            		"      \"1999-04-01\",\r\n" + 
            		"      \"1999-05-01\",\r\n" + 
            		"      \"1999-07-01\",\r\n" + 
            		"      \"1999-09-01\",\r\n" + 
            		"      \"2000-01-01\",\r\n" + 
            		"      \"2000-02-01\",\r\n" + 
            		"      \"2000-04-01\",\r\n" + 
            		"      \"2000-05-01\",\r\n" + 
            		"      \"2000-07-01\",\r\n" + 
            		"      \"2000-08-01\",\r\n" + 
            		"      \"2000-09-01\",\r\n" + 
            		"      \"2000-10-01\",\r\n" + 
            		"      \"2000-11-01\",\r\n" + 
            		"      \"2001-02-01\",\r\n" + 
            		"      \"2001-06-01\",\r\n" + 
            		"      \"2001-07-01\",\r\n" + 
            		"      \"2001-09-01\",\r\n" + 
            		"      \"2002-05-01\",\r\n" + 
            		"      \"2002-07-01\",\r\n" + 
            		"      \"2002-09-01\",\r\n" + 
            		"      \"2002-10-01\",\r\n" + 
            		"      \"2002-11-01\",\r\n" + 
            		"      \"2003-01-01\",\r\n" + 
            		"      \"2003-02-01\",\r\n" + 
            		"      \"2003-03-01\",\r\n" + 
            		"      \"2003-06-01\",\r\n" + 
            		"      \"2003-07-01\",\r\n" + 
            		"      \"2003-10-01\",\r\n" + 
            		"      \"2004-01-01\",\r\n" + 
            		"      \"2004-03-01\",\r\n" + 
            		"      \"2004-04-01\",\r\n" + 
            		"      \"2004-08-01\",\r\n" + 
            		"      \"2004-09-01\",\r\n" + 
            		"      \"2004-11-01\",\r\n" + 
            		"      \"2004-12-01\",\r\n" + 
            		"      \"2005-01-01\",\r\n" + 
            		"      \"2005-02-01\",\r\n" + 
            		"      \"2005-03-01\",\r\n" + 
            		"      \"2005-06-01\",\r\n" + 
            		"      \"2005-11-01\",\r\n" + 
            		"      \"2005-12-01\",\r\n" + 
            		"      \"2006-01-01\",\r\n" + 
            		"      \"2006-03-01\",\r\n" + 
            		"      \"2006-05-01\",\r\n" + 
            		"      \"2006-10-01\",\r\n" + 
            		"      \"2006-11-01\",\r\n" + 
            		"      \"2007-04-01\",\r\n" + 
            		"      \"2007-07-01\",\r\n" + 
            		"      \"2007-09-01\",\r\n" + 
            		"      \"2007-11-01\",\r\n" + 
            		"      \"2008-01-01\",\r\n" + 
            		"      \"2008-02-01\",\r\n" + 
            		"      \"2008-04-01\",\r\n" + 
            		"      \"2008-05-01\",\r\n" + 
            		"      \"2008-06-01\",\r\n" + 
            		"      \"2008-10-01\",\r\n" + 
            		"      \"2008-11-01\",\r\n" + 
            		"      \"2009-02-01\",\r\n" + 
            		"      \"2009-03-01\",\r\n" + 
            		"      \"2009-06-01\",\r\n" + 
            		"      \"2009-08-01\",\r\n" + 
            		"      \"2009-10-01\",\r\n" + 
            		"      \"2009-12-01\",\r\n" + 
            		"      \"2010-02-01\",\r\n" + 
            		"      \"2010-03-01\",\r\n" + 
            		"      \"2010-04-01\",\r\n" + 
            		"      \"2010-07-01\",\r\n" + 
            		"      \"2010-08-01\",\r\n" + 
            		"      \"2010-10-01\",\r\n" + 
            		"      \"2011-01-01\",\r\n" + 
            		"      \"2011-02-01\",\r\n" + 
            		"      \"2011-04-01\",\r\n" + 
            		"      \"2011-05-01\",\r\n" + 
            		"      \"2011-09-01\",\r\n" + 
            		"      \"2011-11-01\",\r\n" + 
            		"      \"2012-01-01\",\r\n" + 
            		"      \"2012-03-01\",\r\n" + 
            		"      \"2012-05-01\",\r\n" + 
            		"      \"2012-08-01\",\r\n" + 
            		"      \"2012-09-01\",\r\n" + 
            		"      \"2012-11-01\",\r\n" + 
            		"      \"2012-12-01\",\r\n" + 
            		"      \"2013-01-01\",\r\n" + 
            		"      \"2013-02-01\",\r\n" + 
            		"      \"2013-03-01\"\r\n" + 
            		"   ]\r\n" + 
            		"}", PeriodosPerdidosResponseDTO.class), HttpStatus.OK);
        } catch (IOException e) {
            log.error("Couldn't serialize response for content type application/json", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
		
	};
	
}
