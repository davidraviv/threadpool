import java.util.concurrent.Callable;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Reads single data file and returns list of names that matches a requested mmsi 
 * In case there was an error reading the files, an empty list is returnd
 */
 public class DataFileReader implements Callable<List> {
    String filePath;
    String mmsi;
    
    public DataFileReader(String filePath, String mmsi) { 
        this.filePath = filePath;
        this.mmsi = mmsi;
    }

    public List call(){
        List<String> result = new ArrayList<>();
        BufferedReader br = null;
	    String line = "";
        try {
            // read the file line by line
		    br = new BufferedReader(new FileReader(filePath));
	    	while ((line = br.readLine()) != null) {

	            // split a line, use comma as separator
		    	String[] vessel = line.split(",");
		    	
		    	// if the mmsi matches, keep the vessel name
		    	if (vessel[0].equals(mmsi)) {
		    	    result.add(vessel[1]);
		    	}
		    }
	    } catch (IOException e) {
	        //TODO handle exceptions
		    e.printStackTrace();
        } finally {
	        // close buffer
		    if (br != null) {
			    try {
				    br.close();
			    } catch (IOException e) {
			    	e.printStackTrace();
			    }
		    }
	    }
	    
        return result;
   }
}