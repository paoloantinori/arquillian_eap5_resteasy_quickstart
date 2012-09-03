package learning.apis.arquillian.rest.sample;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SampleBean {
	int myInteger;
	String myString;

	public int getMyInteger() {
		return myInteger;
	}

	public void setMyInteger(int myInteger) {
		this.myInteger = myInteger;
	}

	public String getMyString() {
		return myString;
	}

	public void setMyString(String myString) {
		this.myString = myString;
	}

}
