package cabanas.garcia.orienteering;

import java.io.InputStream;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ReplacementDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.springframework.core.io.Resource;

import com.github.springtestdbunit.dataset.AbstractDataSetLoader;

/**
 * @author Petri Kainulainen
 */
public class ColumnSensingFlatXMLDataSetLoader extends AbstractDataSetLoader {
    @Override
    protected IDataSet createDataSet(Resource resource) throws Exception {
        FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
        builder.setColumnSensing(true);
        InputStream inputStream = resource.getInputStream();
        try {
            return createReplacementDataSet(builder.build(inputStream));
        } finally {
            inputStream.close();
        }
    }
    
    private ReplacementDataSet createReplacementDataSet(FlatXmlDataSet dataSet) {
        ReplacementDataSet replacementDataSet = new ReplacementDataSet(dataSet);
		
		//Configure the replacement dataset to replace '[null]' strings with null.
        replacementDataSet.addReplacementObject("[null]", null);
        
		return replacementDataSet;
    }
}
