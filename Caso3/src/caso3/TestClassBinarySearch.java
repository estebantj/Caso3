package caso3;

import java.util.Random;
import java.util.Arrays; 
import javax.swing.JOptionPane;
import org.apache.jmeter.config.Arguments;
//import junit.framework.TestCase;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;


public class TestClassBinarySearch extends AbstractJavaSamplerClient{
    int[] listaNumeros;
    int size;

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

    int binarySearch(int arr[], int x) { 
        int l = 0, r = arr.length - 1; 
        while (l <= r) { 
            int m = l + (r - l) / 2; 
  
            // Check if x is present at mid 
            if (arr[m] == x) 
                return m; 
  
            // If x greater, ignore left half 
            if (arr[m] < x) 
                l = m + 1; 
  
            // If x is smaller, ignore right half 
            else
                r = m - 1; 
        } 
  
        // if we reach here, then element was not present 
        return -1; 
    } 
    
    @Override
    public SampleResult runTest(JavaSamplerContext context) {
        //JOptionPane.showMessageDialog(null, "Prueba " + Arrays.toString(listaNumeros));
        
        SampleResult result = new SampleResult();
        result.sampleStart(); // start stopwatch
        
        int busqueda = binarySearch(listaNumeros, 0);
        
        result.sampleEnd();
        result.setSuccessful( true );
        //result.setResponseMessage(Arrays.toString(listaNumeros));
        result.setResponseMessage( "Successfully performed action");
        result.setResponseCodeOK(); // 200 code
        
        System.err.println(Thread.currentThread().getName() + " " + Arrays.toString(listaNumeros));
        
        return result;
    }
}
