package br.com.andreas.math;

import br.com.andreas.exception.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

public class SimpleMath {



    public Double sum(Double first, Double second ){
        return first + second;
    }

    public Double sub(Double first, Double second ){
        return first - second;
    }

    public Double div(Double first, Double second ){
        return first / second;
    }

    public Double mult(Double first, Double second ){
        return first * second;
    }

    public Double med(Double first, Double second ){
        return (first + second)/2;
    }

    public Double sqr(Double first){
        return Math.sqrt(first);
    }

}
