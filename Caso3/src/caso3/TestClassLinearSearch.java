/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caso3;

import java.util.Arrays;
import java.util.Random;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

/**
 *
 * @author esteb
 */
public class TestClassLinearSearch extends AbstractJavaSamplerClient{
    int size;
    int[] listaNumeros;
    
    /*
        El setupTest solo se ejecuta una vez.
        Cada vez que se ejecuta el runTest se utiliza el mismo arreglo.
    */
    
    @Override
    public void setupTest(JavaSamplerContext context){
        size = Variables.size;
        listaNumeros = new int[size];
        Random r = new Random();
        for (int i=0; i<size; i++) {
            listaNumeros[i] = r.nextInt(Variables.maxRand) + 20;
        }
        Arrays.sort(listaNumeros);
        super.setupTest(context);
    }
    
    public int linearSearch(int[] arr, int x) {
        int lastPos = arr.length;
        
        for (int i=0; i < lastPos; i++) {
            if (arr[i] == x) {
                return i;
            }
        }
        
        return -1;
    }
    
    @Override
    public SampleResult runTest(JavaSamplerContext jsc) {
        SampleResult result = new SampleResult();
        result.sampleStart(); // start stopwatch
        
        int busqueda = linearSearch(listaNumeros, 0);
        
        result.sampleEnd();
        result.setSuccessful( true );
        result.setResponseMessage( "Successfully performed action: " +  Integer.toString(size));
        result.setResponseCodeOK(); // 200 code
        
        return result;
    }
    
}
