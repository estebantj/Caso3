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
public class TestClassInterpolarSearch extends AbstractJavaSamplerClient{
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
    
    int interpolationSearch(int[] arr, int x) { 
        // Find indexes of two corners 
        int lo = 0, hi = (arr.length - 1); 

        // Since array is sorted, an element present 
        // in array must be in range defined by corner 
        while (lo <= hi && x >= arr[lo] && x <= arr[hi]) {
            if (lo == hi) { 
                if (arr[lo] == x) return lo; 
                return -1; 
            }
            // Probing the position with keeping 
            // uniform distribution in mind. 
            int pos = lo + (((hi-lo) / (arr[hi]-arr[lo]))*(x - arr[lo])); 
            // Condition of target found 
            if (arr[pos] == x) 
                return pos; 
            // If x is larger, x is in upper part 
            if (arr[pos] < x) lo = pos + 1; 
            // If x is smaller, x is in the lower part 
            else
                hi = pos - 1; 
        }
        return -1; 
    } 
    
    @Override
    public SampleResult runTest(JavaSamplerContext jsc) {
        SampleResult result = new SampleResult();
        result.sampleStart(); // start stopwatch
        
        int busqueda = interpolationSearch(listaNumeros, 0);
        
        result.sampleEnd();
        result.setSuccessful( true );
        result.setResponseMessage( "Successfully performed action: " +  Integer.toString(size));
        result.setResponseCodeOK(); // 200 code
        
        return result;
    }
}
