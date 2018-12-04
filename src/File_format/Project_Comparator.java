package File_format;

import java.io.IOException;
import java.util.Comparator;

public class Project_Comparator implements Comparator<Csv_layer>{

	@Override
	public int compare(Csv_layer layer1, Csv_layer layer2) {
		 try {
			if(layer1.get_Meta_data().getUTC()-layer2.get_Meta_data().getUTC() < 0)
				return -1;
			 else if(layer1.get_Meta_data().getUTC()-layer2.get_Meta_data().getUTC() > 0)
				 return 1;
			 else
				 return 0;
		} catch (IOException e) {
			return 0;
		}
	}

}
