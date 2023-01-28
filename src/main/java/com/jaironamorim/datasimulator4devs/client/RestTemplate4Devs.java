package com.jaironamorim.datasimulator4devs.client;

import com.jaironamorim.datasimulator4devs.model.dto.entry.EntryDto;
import com.jaironamorim.datasimulator4devs.model.dto.people.PeopleDto;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;


@Component
@RequiredArgsConstructor
public class RestTemplate4Devs {
    private final RestTemplate restTemplate;

    public List<PeopleDto> postData(EntryDto entryDto){
        final String url = "https://www.4devs.com.br/ferramentas_online.php";
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(getRequestBody(entryDto), getHeaders());
        ResponseEntity<List<PeopleDto>> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, new ParameterizedTypeReference<>() {});
        return response.getBody();
    }

    private HttpHeaders getHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        return headers;
    }

    private MultiValueMap<String, Object> getRequestBody(EntryDto entryDto){

        if(Objects.isNull(entryDto.getSexo())){
            entryDto.setSexo("I");
        }

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("acao", "gerar_pessoa");
        body.add("txt_qtde", 1);
        body.add("sexo", entryDto.getSexo());
        return body;
    }
}
