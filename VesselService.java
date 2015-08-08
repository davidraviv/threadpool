import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ExecutionException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

class VesselService {
    
    /**
     * Returns all vessel names related to requested mmsi.
     */ 
    public List<String> getNames(String mmsi) {
        
        //TODO get the following values from configuration file (poolSize doesn't 
        //     have to match file count)
        int poolSize = 3;
        List<String> dataFiles = Arrays.asList("./resources/vesselsData1.txt",
                                                "./resources/vesselsData2.txt",
                                                "./resources/vesselsData3.txt");
        
        // creating a thread pool
        ExecutorService threadPool = Executors.newFixedThreadPool(poolSize);
        CompletionService<List> pool = new ExecutorCompletionService<List>(threadPool);
        
        // submit each data file to a thread
        for (String filePath : dataFiles) {
            pool.submit(new DataFileReader(filePath, mmsi));
        }

        // concatenate the results from each thread
        List<String> result = new ArrayList<>();
        for(int i = 0; i < dataFiles.size() ; i++){
            try {
                result.addAll(pool.take().get());
            } catch(InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        
        // close thread pool
        threadPool.shutdown();
        
        return result;
   }
}