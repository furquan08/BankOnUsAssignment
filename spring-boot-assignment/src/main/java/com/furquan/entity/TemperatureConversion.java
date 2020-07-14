package com.furquan.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * @author furquan
 *
 */
@JacksonXmlRootElement(namespace = "urn:TemperatureConversion", localName = "TemperatureConversion")
@XmlAccessorType(XmlAccessType.FIELD)
public class TemperatureConversion {
	private static final long serialVersionUID = 1L;

	@JacksonXmlProperty(localName = "Celsius")
	private String celsius;

	@JacksonXmlProperty(localName = "Fahrenheit")
	private String fahrenheit;

	public TemperatureConversion() {

	}

	public String getCelsius() {
		return celsius;
	}

	public void setCelsius(String celsius) {
		this.celsius = celsius;
	}

	public String getFahrenheit() {
		return fahrenheit;
	}

	public void setFahrenheit(String fahrenheit) {
		this.fahrenheit = fahrenheit;
	}

}
