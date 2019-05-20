package com.epam.final_task.tag;

import com.epam.final_task.model.entity.Currency;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class CurrencyStampTag extends TagSupport {
    private BigDecimal value;
    private static final BigDecimal BYN_EXCHANGE_RATE = new BigDecimal("2.11");

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public int doStartTag() throws JspException {
        HttpSession session = pageContext.getSession();
        Currency currency = (Currency) session.getAttribute("currency");
        String message = new String();
        try {
            switch (currency) {
                case USD:
                    message = value.toString() + " $";
                    break;
                case BYN:
                    message = value.multiply(BYN_EXCHANGE_RATE).setScale(2, RoundingMode.HALF_UP).toString() + " Br";
                    break;
            }
            pageContext.getOut().write(message);
        } catch (IOException e) {
            throw new JspException(e.getMessage(),e);
        }
        return SKIP_BODY;
    }
}
