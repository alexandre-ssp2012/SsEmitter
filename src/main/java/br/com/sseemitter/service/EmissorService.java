package br.com.sseemitter.service;

import br.com.sseemitter.model.Emissor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmissorService {

   private List emissores;

   @PostConstruct
   public void init() {
       emissores = new ArrayList<>();
       emissores.add(new Emissor("li_1"));
       emissores.add(new Emissor("li_2"));
   }

   public List getEmissores(){
       return emissores;
   }

}
