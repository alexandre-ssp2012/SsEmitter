package br.com.sseemitter.controller;

import br.com.sseemitter.model.Emissor;
import br.com.sseemitter.service.EmissorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Controller
public class EmitterController {

    @Autowired
    EmissorService emissorService;

    @RequestMapping("/sseTest")
    public SseEmitter handleRequest () {

        final SseEmitter emitter = new SseEmitter();
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(() -> {
            Random gerador = new Random();
            List<Emissor> emissores = emissorService.getEmissores();

            for (int i = 0; i < 500; i++) {
                try {
                    for(Emissor emissor : emissores){
                        Integer valor = gerador.nextInt(10000);
                        emissor.setValorTag(valor.toString());

                        emitter.send(emissor , MediaType.APPLICATION_JSON_UTF8);

                        Thread.sleep(5000);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    emitter.completeWithError(e);
                    return;
                }
            }
            emitter.complete();
        });

        return emitter;
    }
}