package cl.desafio.previred.controller.docs;


import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

@Controller
public class SwaggerController {

    @RequestMapping(value = "/swagger", method = RequestMethod.GET)
    public void swagger(HttpServletResponse response) throws Exception {
        response.setContentType("text/plain; charset=UTF-8");
        InputStream is = getClass().getClassLoader().getResourceAsStream("desafio.yaml");
        IOUtils.copy(is, response.getOutputStream());
        response.flushBuffer();
        is.close();
    }


}