package support.managers;

import org.json.JSONException;

import support.descriptors.json.JSONToolDescriptor;
import support.descriptors.xml.XMLToolDescriptor;
import support.descriptors.xml.xmlObject.XMLException;
import descriptors.IToolDescriptor;
import exceptions.DescriptorException;

public class ToolDescriptorManager {
	
	public static IToolDescriptor xmlFactory(String content) throws DescriptorException {
		try{
			return new XMLToolDescriptor(content);
		} catch (XMLException e) {
			throw new DescriptorException("Error instanciating XMLToolDescriptor!", e);
		}
	}
	
	public static IToolDescriptor jsonFactory(String content) throws DescriptorException {
		try{
			return new JSONToolDescriptor(content);
		} catch (JSONException e) {
			throw new DescriptorException("Error instanciating JSONToolDescriptor!", e);
		}
	}

}
