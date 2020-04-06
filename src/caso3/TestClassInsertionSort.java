/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caso3;

import java.util.Arrays;
import java.util.Random;
import javax.swing.JOptionPane;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

/**
 *
 * @author esteb
 */
public class TestClassInsertionSort extends AbstractJavaSamplerClient {
    
    int size;
    int[] listaNumeros;

    void insertionSort(int arr[]) { 
        int n = arr.length; 
        for (int i = 1; i < n; ++i) { 
            int key = arr[i]; 
            int j = i - 1; 
  
            /* Move elements of arr[0..i-1], that are 
               greater than key, to one position ahead 
               of their current position */
            while (j >= 0 && arr[j] > key) { 
                arr[j + 1] = arr[j]; 
                j = j - 1; 
            } 
            arr[j + 1] = key; 
        } 
    } 
    
    /*
        El setupTest solo se ejecuta una vez.
    */
    
    @Override
    public void setupTest(JavaSamplerContext context){
        size = Variables.size;
        super.setupTest(context);
    }
    
    // El arreglo se crea en el runTest porque sino solo se ordena en la primera iteracion
    @Override
    public SampleResult runTest(JavaSamplerContext jsc) {
        
        listaNumeros = new int[size];
        Random r = new Random();
        for (int i=0; i<size; i++) {
            listaNumeros[i] = r.nextInt(Variables.maxRand) + 20;
        }
        
        SampleResult result = new SampleResult();
        //result.setResponseMessage( "Successfully performed action: " + Arrays.toString(listaNumeros));
        result.sampleStart(); // start stopwatch
        
        insertionSort(this.listaNumeros);
        
        result.sampleEnd();
        result.setSuccessful( true );
        result.setResponseMessage( "Successfully performed action");
        result.setResponseCodeOK(); // 200 code
        
        return result;
    }
    
}
