package br.com.andreas.controllers;

import br.com.andreas.math.SimpleMath;
import br.com.andreas.exception.UnsupportedMathOperationException;
import br.com.andreas.request.converters.NumberConverter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math")
public class MathController {

    private SimpleMath math = new SimpleMath();

    //localhost:8080/math/sum/3/5
    @RequestMapping("/sum/{first}/{second}")
    public Double sum(
            @PathVariable("first") String first,
            @PathVariable("second") String second
    ) throws Exception {
        if(!NumberConverter.isNumeric(first) || !NumberConverter.isNumeric(second)) throw new UnsupportedMathOperationException("Please enter a numeric value");
        return math.sum(NumberConverter.convertToDouble(first) , NumberConverter.convertToDouble(second));
    }

    //localhost:8080/math/sub/3/5
    @RequestMapping("/sub/{first}/{second}")
    public Double sub(
            @PathVariable("first") String first,
            @PathVariable("second") String second
    ) throws Exception {
        if(!NumberConverter.isNumeric(first) || !NumberConverter.isNumeric(second)) throw new UnsupportedMathOperationException("Please enter a numeric value");
        return math.sub(NumberConverter.convertToDouble(first) ,  NumberConverter.convertToDouble(second));
    }

    //localhost:8080/math/div/3/5
    @RequestMapping("/div/{first}/{second}")
    public Double div(
            @PathVariable("first") String first,
            @PathVariable("second") String second
    ) throws Exception {
        if(!NumberConverter.isNumeric(first) || !NumberConverter.isNumeric(second)) throw new UnsupportedMathOperationException("Please enter a numeric value");
        return math.div(NumberConverter.convertToDouble(first) , NumberConverter.convertToDouble(second));
    }

    //localhost:8080/math/mult/3/5
    @RequestMapping("/mult/{first}/{second}")
    public Double mult(
            @PathVariable("first") String first,
            @PathVariable("second") String second
    ) throws Exception {
        if(!NumberConverter.isNumeric(first) || !NumberConverter.isNumeric(second)) throw new UnsupportedMathOperationException("Please enter a numeric value");
        return math.mult(NumberConverter.convertToDouble(first) , NumberConverter.convertToDouble(second));
    }

    //localhost:8080/math/med/3/5
    @RequestMapping("/med/{first}/{second}")
    public Double med(
            @PathVariable("first") String first,
            @PathVariable("second") String second
    ) throws Exception {
        if(!NumberConverter.isNumeric(first) || !NumberConverter.isNumeric(second)) throw new UnsupportedMathOperationException("Please enter a numeric value");
        return math.med(NumberConverter.convertToDouble(first) , NumberConverter.convertToDouble(second));
    }

    //localhost:8080/math/sqr/3/5
    @RequestMapping("/sqr/{first}")
    public Double sqr(
            @PathVariable("first") String first
    ) throws Exception {
        if(!NumberConverter.isNumeric(first))
            throw new UnsupportedMathOperationException("Please enter a numeric value");
        return math.sqr(NumberConverter.convertToDouble(first));

    }


}
