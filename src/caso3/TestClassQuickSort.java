/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caso3;

import java.util.Random;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

/**
 *
 * @author esteb
 */
public class TestClassQuickSort extends AbstractJavaSamplerClient {
    
    int size;
    int[] listaNumeros;

    int partition(int arr[], int low, int high) { 
        int pivot = arr[high];  
        int i = (low-1); // index of smaller element 
        for (int j=low; j<high; j++) 
        { 
            // If current element is smaller than the pivot 
            if (arr[j] < pivot) 
            { 
                i++; 
  
                // swap arr[i] and arr[j] 
                int temp = arr[i]; 
                arr[i] = arr[j]; 
                arr[j] = temp; 
            } 
        } 
  
        // swap arr[i+1] and arr[high] (or pivot) 
        int temp = arr[i+1]; 
        arr[i+1] = arr[high]; 
        arr[high] = temp; 
  
        return i+1; 
    } 

    void quickSort(int arr[], int low, int high) { 
        if (low < high) 
        { 
            /* pi is partitioning index, arr[pi] is  
              now at right place */
            int pi = partition(arr, low, high); 
  
            // Recursively sort elements before 
            // partition and after partition 
            quickSort(arr, low, pi-1); 
            quickSort(arr, pi+1, high); 
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
        result.sampleStart(); // start stopwatch
        
        quickSort(this.listaNumeros, 0, this.listaNumeros.length-1);
        
        result.sampleEnd();
        result.setSuccessful( true );
        result.setResponseMessage( "Successfully performed action: " +  Integer.toString(size));
        result.setResponseCodeOK(); // 200 code
        
        return result;
    }    
}
