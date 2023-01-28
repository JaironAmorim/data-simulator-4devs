package com.jaironamorim.datasimulator4devs.service.impl;

import com.jaironamorim.datasimulator4devs.model.dto.creditcard.CreditCardDto;
import com.jaironamorim.datasimulator4devs.model.enums.LabelEnum;
import com.jaironamorim.datasimulator4devs.service.CreditCardGenaratorService;
import com.jaironamorim.datasimulator4devs.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreditCardGenaratorServiceImpl implements CreditCardGenaratorService {

    @Override
    public CreditCardDto generateCreditCard(String label, String checkdigit) {

        log.info("Gerando numero de Cartão");
        String number;

        LabelEnum labelEnum = Objects.isNull(label) ? LabelEnum.VISA : LabelEnum.getLabel(label);
        List<String> prefixList = LabelEnum.getPrefixList(labelEnum);

        if(Objects.isNull(checkdigit)){
            number = generateCardNumber(prefixList);

        } else{
            do {
                number = generateCardNumber(prefixList);
            } while (!(number.substring(number.length()-1)).equals(checkdigit));

        }

        String expires = DateUtil.randonGenerateDate(LocalDate.now(), LocalDate.of(2030, 12, 30))
                .format(DateTimeFormatter.ofPattern("MM/yyyy"));

        Random random = new Random();
        String cvv = String.valueOf(random.nextInt(1000, 10000)).substring(0, 3);

        return  CreditCardDto.builder()
                .number(number)
                .cvv(cvv)
                .expires(expires)
                .flag(labelEnum.getLabel())
                .owner("Jane Doe")
                .isValid(isValidCreditCardNumber(number))
                .build();

    }

    private String generateCardNumber(List<String> prefixList){

        int randomListIndex = (int) Math.floor(Math.random() * prefixList.size());
        String ccnumber = prefixList.get(randomListIndex);
        return generateCCNumber(ccnumber);

    }

    private String generateCCNumber(String prefix) {

        final int length = 16;
        StringBuilder ccnumber = new StringBuilder(prefix);

        while (ccnumber.length() < (length - 1)) {
            ccnumber.append(Double.valueOf(Math.floor(Math.random() * 10)).intValue());
        }

        String reversedCCnumberString = strrev(ccnumber.toString());

        List<Integer> reversedCCnumberList = new ArrayList<>();

        for (int i = 0; i < reversedCCnumberString.length(); i++) {
            reversedCCnumberList.add(Integer.valueOf(String.valueOf(reversedCCnumberString.charAt(i))));
        }

        int sum = 0;
        int pos = 0;

        while (pos < length - 1) {

            int odd = reversedCCnumberList.get(pos) * 2;
            if (odd > 9) {
                odd -= 9;
            }

            sum += odd;

            if (pos != (length - 2)) {
                sum += reversedCCnumberList.get(pos + 1);
            }

            pos += 2;

        }

        int checkdigit = Double.valueOf(((Math.floor(sum / 10) + 1) * 10 - sum) % 10).intValue();
        ccnumber.append(checkdigit);
        return ccnumber.toString();

    }

    private String strrev(String str) {

        if(Objects.isNull(str)) return "";

        StringBuilder revstr = new StringBuilder();
        for (int i = str.length() - 1; i >= 0; i--) {
            revstr.append(str.charAt(i));
        }
        return revstr.toString();

    }


    public boolean isValidCreditCardNumber(String creditCardNumber) {
        boolean isValid = false;

        try {
            String reversedNumber = new StringBuffer(creditCardNumber).reverse().toString();
            int mod10Count = 0;
            for (int i = 0; i < reversedNumber.length(); i++) {
                int augend = Integer.parseInt(String.valueOf(reversedNumber.charAt(i)));
                if (((i + 1) % 2) == 0) {
                    String productString = String.valueOf(augend * 2);
                    augend = 0;
                    for (int j = 0; j < productString.length(); j++) {
                        augend += Integer.parseInt(String.valueOf(productString.charAt(j)));
                    }
                }

                mod10Count += augend;
            }

            if ((mod10Count % 10) == 0) {
                isValid = true;
            }
        } catch (NumberFormatException e) {
            log.error(e.getMessage());
        }

        return isValid;
    }

}
